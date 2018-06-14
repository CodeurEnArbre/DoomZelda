package game.modele.entity.living;


import game.modele.entity.Entity;
import game.modele.entity.living.monster.EntityMonster;
import game.modele.entity.tileEntity.carriable.CarriableEntity;
import game.modele.entity.tileEntity.chest.Chest;
import game.modele.item.Item;
import game.modele.item.weapon.Weapon;
import game.modele.menu.InventoryMenu;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import game.modele.utils.ActionConsumer.ConsumerAction;
import game.modele.utils.ActionConsumer.ConsumerActionDelay;
import game.modele.utils.ActionConsumer.CountActionConsumer;
import game.modele.utils.ActionConsumer.InfiniteActionConsumer;
import game.modele.utils.ActionConsumer.SimpleActionConsumer;
import game.modele.utils.ActionConsumer.Function.FunctionRaise;
import game.modele.utils.ActionConsumer.Function.Function;
import game.modele.utils.ActionConsumer.Function.FunctionDeclanche;
import game.modele.utils.ActionConsumer.Function.FunctionLampe;
import game.modele.utils.ActionConsumer.Function.FunctionMove;
import game.modele.utils.ActionConsumer.Function.FunctionMovement;
import game.modele.world.World;
import javafx.beans.property.*;

public class Player extends EntityLiving{

	ConsumerAction deplacement = new InfiniteActionConsumer(new FunctionMove());
	ConsumerAction mouvement = new InfiniteActionConsumer(new FunctionMovement());

	CountActionConsumer soulever = new CountActionConsumer(60,new FunctionRaise());
	SimpleActionConsumer reactiveWalk = new SimpleActionConsumer(new Function() {
		@Override
		protected void Action(Entity e) {
			((Player)e).action.set(Actions.walk.get());
		}
	});
	SimpleActionConsumer reactiveWalkAndRaise = new SimpleActionConsumer(new Function() {
		@Override
		protected void Action(Entity e) {
			((Player)e).action.set(Actions.walkAndRaise.get());
		}
	});
	
	
	
	ConsumerActionDelay Walk = new ConsumerActionDelay(30,new SimpleActionConsumer(new FunctionDeclanche(mouvement,reactiveWalk)));
	ConsumerActionDelay WalkAndRaise = new ConsumerActionDelay(30,new SimpleActionConsumer(new FunctionDeclanche(mouvement,reactiveWalkAndRaise)));
	
	ConsumerAction lampe = new InfiniteActionConsumer(new FunctionLampe());

	public int nbWeapon = 0;
	public Weapon[] weapons = new Weapon[24];

	public static int maxRupees=100;
	public static IntegerProperty rupees; //ARGENT!!!	
	public BooleanProperty havePickupItem;
	public Item pickupItem;

	public BooleanProperty haveLeftItemEquip;
	public Weapon LeftItemEquip;

	public BooleanProperty haveRightItemEquip;
	public Weapon RightItemEquip;

	public CarriableEntity carriedEntity;
	public BooleanProperty isCarriedSomething;

	public Player(Coordonnees position, Direction direction, int maxPv, int pv, int ruby, Weapon[] weapons, Item leftEquip, Item rightEquip) {
		super("Player",position,direction);
		this.speed = baseSpeed;
		this.slow =	1;
		super.maxPv.set(maxPv);
		super.PV.set(pv);
		Player.rupees = new SimpleIntegerProperty(ruby);
		isCarriedSomething = new SimpleBooleanProperty();
		havePickupItem = new SimpleBooleanProperty(false);
		haveLeftItemEquip = new SimpleBooleanProperty(false);
		haveRightItemEquip = new SimpleBooleanProperty(false);
		this.weapons = weapons;
		this.carriedEntity=null;

		addAction(deplacement);
		addAction(mouvement);
		
		
	}

	@Override
	public void dispose() {
		delAction(deplacement);
		delAction(mouvement);
	}

	public void addRuby(int quantity) {
		if((rupees.get()+quantity)<maxRupees) {
			Player.rupees.set(rupees.get()+quantity);
		}else {
			Player.rupees.set(maxRupees);
		}
	}

	public void removeRuby(int quantity) {
		if(rupees.get()-quantity < 0) {
			Player.rupees.set(0);
		}else {
			Player.rupees.set(rupees.get()-quantity);
		}
	}

	public boolean canRemoveRuby(int quantity) {
		if(rupees.get()>=quantity) {
			Player.rupees.set(Player.rupees.get()-quantity);
			return true;
		}else
			return false;
	}


	@Override
	public void incAnim() {
		this.etatDeplacement.set(
				this.etatDeplacement.get()
				+ (this.etatDeplacement.get() < 83 ? 1 : -83));
	}

	@Override
	public void active(Entity e) {
		if(e instanceof EntityMonster) {
			perdrePV(1);
		}
	}

	public Item takeItem(Item item) {
		if(item instanceof Weapon) {
			if(nbWeapon >= weapons.length)
				return item;
			else {
				weapons[nbWeapon] = (Weapon)item;
				InventoryMenu.lastItemAdded.set(2);
				InventoryMenu.newItem.set(true);
				nbWeapon++;
			}
		}else{
			switch(item.getItemName()) {

			case "Heart":
				gagnerPV(4); break;
			case "GreenRupee":
				addRuby(1); break;
			case "BlueRupee":
				addRuby(5); break;
			case "RedRupee":
				addRuby(20); break;
			case "PurpleRupee":
				addRuby(50); break;
			case "SilverRupee":
				addRuby(100); break;
			case "GoldRupee":
				addRuby(300); break;
			case "Rupoor":
				removeRuby(10); break;

			default:
				//FAUDRAI PEUT ETRE UTILISER UN THROW
				System.out.println("C'est quoi ca ???? : "+item);
			}
		}

		return null;
	}

	public void useWeapon(boolean have,Weapon weapon) {
		if(have) {
			this.action.set(Actions.useWeapon.get());
			weapon.attaque();	
		}
	}


	public void useLeftItem() {
		useWeapon(haveLeftItemEquip.get(), LeftItemEquip);
	}

	public void useRightItem() {
		useWeapon(haveRightItemEquip.get(), RightItemEquip);
	}

	public void interact() {
		if(carriedEntity == null) {
			int dir = super.direction.getDirection();
			int current_x = (int)super.coordonnes.getX();
			int current_y = (int)super.coordonnes.getY();

			int x = (dir==Direction.West?current_x-1:dir==Direction.East?current_x+1:current_x);
			int y = (dir==Direction.South?current_y+1:dir==Direction.North?current_y-1:current_y);
			Entity e = World.currentMap.getEntity(x,y);
			
			if(e != null) {
				if(e instanceof CarriableEntity) {	
					((CarriableEntity)e).pickupEntity(this);
					soulever.renew();
					WalkAndRaise.renew();
					delAction(mouvement);
					addAction(soulever);
					this.action.set(Actions.raise.get());
					this.addAction(WalkAndRaise);					
					
					
				}else if(e instanceof Chest) {
					e.interact();
				}
			}
		}else {
			if(this.carriedEntity.placeEntity(this)) {
				soulever.renew();
				Walk.renew();
				delAction(mouvement);
				addAction(soulever);
				this.action.set(Actions.place.get());
				this.addAction(Walk);	
				this.addAction(new ConsumerActionDelay(30,new SimpleActionConsumer(new Function() {
					
					@Override
					protected void Action(Entity e) {
						World.currentMap.entity.add(carriedEntity);
						World.player.isCarriedSomething.set(false);
						World.player.carriedEntity=null;
					}
				})));	
				
				
			}
		}
	}
}
