package game.modele.entity.living;

import java.util.ArrayList;

import game.modele.entity.Entity;
import game.modele.entity.living.monster.EntityMonster;
import game.modele.entity.tileEntity.carriable.CarriableEntity;
import game.modele.entity.tileEntity.chest.Chest;
import game.modele.item.Item;
import game.modele.item.loot.Loot;
import game.modele.item.special.Special;
import game.modele.item.usable.Usable;
import game.modele.item.weapon.Bow;
import game.modele.item.weapon.Weapon;
import game.modele.menu.InventoryMenu;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import game.modele.utils.ActionConsumer.ConsumerAction;
import game.modele.utils.ActionConsumer.CountActionConsumer;
import game.modele.utils.ActionConsumer.InfiniteActionConsumer;
import game.modele.utils.ActionConsumer.Function.FunctionCantMove;
import game.modele.utils.ActionConsumer.Function.FunctionLampe;
import game.modele.utils.ActionConsumer.Function.FunctionMove;
import game.modele.utils.ActionConsumer.Function.FunctionMovement;
import game.modele.world.World;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Player extends EntityLiving{

	ConsumerAction deplacement = new InfiniteActionConsumer(new FunctionMove());
	ConsumerAction mouvement = new InfiniteActionConsumer(new FunctionMovement());
	ConsumerAction lampe = new InfiniteActionConsumer(new FunctionLampe());
	
	public ArrayList<Loot> loots;
	public ArrayList<Usable> usables;
	public ArrayList<Weapon> weapons;
	public ArrayList<Special> specials;
	
	public static int maxRupees=100;
	public static IntegerProperty rupees; //ARGENT!!!	
	public ObjectProperty pickupItem;
	public ObjectProperty leftItemEquip;
	public ObjectProperty rightItemEquip;
	public CarriableEntity carriedEntity;
	public BooleanProperty isCarriedSomething;
	
	public Player(Coordonnees position, Direction direction, int maxPv, int pv, int ruby, ArrayList<Loot> loots, ArrayList<Usable> usables, ArrayList<Weapon> weapons, ArrayList<Special> specials, Item leftEquip, Item rightEquip) {
		super("Player",position,direction);
		this.speed = baseSpeed;
		this.slow =	1;
		super.maxPv.set(maxPv);
		super.PV.set(pv);
		Player.rupees = new SimpleIntegerProperty(ruby);
		isCarriedSomething = new SimpleBooleanProperty();
		pickupItem = new SimpleObjectProperty<>();
		leftItemEquip = new SimpleObjectProperty<>();
		rightItemEquip = new SimpleObjectProperty<>();
		this.usables = usables;
		this.weapons = weapons;
		this.loots=loots;
		this.specials = specials;
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
		Item returnItem = null;
		if(item instanceof Usable) {
			if(usables.size() >= 24)
				returnItem = item;
			else {
				usables.add((Usable)item);
				InventoryMenu.lastItemAdded.set(1);
				InventoryMenu.newItem.set(true);
			}
			
		}else if(item instanceof Weapon) {
			if(weapons.size() >= 24)
				returnItem = item;
			else {
				weapons.add((Weapon)item);
				InventoryMenu.lastItemAdded.set(2);
				InventoryMenu.newItem.set(true);
			}
		}else if(item instanceof Bow) {
			System.out.println("Truc");
			if(weapons.size() >= 24)
				returnItem = item;
			else {
				weapons.add((Weapon)item);
				InventoryMenu.lastItemAdded.set(2);
				InventoryMenu.newItem.set(true);
			}
		}else if(item instanceof Loot) {
			if(loots.size() >= 24)
				returnItem = item;
			else {
				loots.add((Loot)item);
				InventoryMenu.lastItemAdded.set(3);
				InventoryMenu.newItem.set(true);
			}
		}else if(item instanceof Special) {//nombre predefinit donc pas besoin de verifier
			specials.add((Special)item);
			InventoryMenu.lastItemAdded.set(4);
			InventoryMenu.newItem.set(true);
			
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
				System.out.println("C'est quoi ca ???? : "+item);
			}
		}
		
		return returnItem;
	}
	
	public void useLeftItem() {
		if(leftItemEquip.get()!=null) {
			addAction(new CountActionConsumer(30,new FunctionCantMove()));
			this.action.set(Actions.useLeftItem.get());
			if(leftItemEquip.get() instanceof Weapon) {
				Weapon weapon = (Weapon)leftItemEquip.get();
				weapon.attaque();
			}else if(leftItemEquip.get() instanceof Usable) {
				Usable usable = (Usable)leftItemEquip.get();
				usable.use();
			}
		}
	}
	
	public void useRightItem() {
		if(rightItemEquip.get()!=null) {
			addAction(new CountActionConsumer(30,new FunctionCantMove()));
			this.action.set(Actions.useRightItem.get());
			if(rightItemEquip.get() instanceof Weapon) {
				Weapon weapon = (Weapon)rightItemEquip.get();
				weapon.attaque();
			}else if(rightItemEquip.get() instanceof Usable) {
				Usable usable = (Usable)rightItemEquip.get();
				usable.use();
			}
		}
	}
	
	public void interact() {
		if(carriedEntity == null) {
			int dir = super.direction.getDirection();
			int x = (dir==Direction.West?(int)super.coordonnes.getX()-1:dir==Direction.East?(int)super.coordonnes.getX()+1:(int)super.coordonnes.getX());
			int y = (dir==Direction.South?(int)super.coordonnes.getY()+1:dir==Direction.North?(int)super.coordonnes.getY()-1:(int)super.coordonnes.getY());
			Entity e = World.currentMap.getEntity(x,y);
			if(e != null) {
				if(e instanceof CarriableEntity) {	
					((CarriableEntity)e).pickupEntity(this);
					this.action.set(Actions.raise.get());
					addAction(new CountActionConsumer(30,new FunctionCantMove()));
				}else if(e instanceof Chest) {
					e.interact();
				}
			}
		}else {
			this.carriedEntity.placeEntity(this);
		}
	}
}
