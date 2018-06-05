package game.modele.entity.Player;

import java.util.ArrayList;

import game.modele.entity.Entity;
import game.modele.entity.living.EntityLiving;
import game.modele.entity.living.monster.EntityMonster;
import game.modele.entity.tileEntity.CarriableEntity;
import game.modele.item.loot.Loot;
import game.modele.item.special.Special;
import game.modele.item.usable.Usable;
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
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Player extends EntityLiving{

	ConsumerAction deplacement = new InfiniteActionConsumer(new FunctionMove());
	ConsumerAction mouvement = new InfiniteActionConsumer(new FunctionMovement());
	ConsumerAction lampe = new InfiniteActionConsumer(new FunctionLampe());
	
	public ArrayList<Loot> loots;
	public ArrayList<Usable> usables;
	public ArrayList<Weapon> weapons;
	public ArrayList<Special> specials;
	
	private int maxRuby=100;
	public static IntegerProperty ruby; //ARGENT!!!	
	public CarriableEntity carriedEntity;
	public BooleanProperty isCarriedSomething;
	
	public Player(Coordonnees position, Direction direction, int maxPv, int pv, int ruby, ArrayList<Loot> loots, ArrayList<Usable> usables, ArrayList<Weapon> weapons, ArrayList<Special> specials) {
		super("Player",position,direction);
		this.speed = baseSpeed;
		this.slow =	1;
		super.maxPv.set(maxPv);
		super.PV.set(pv);
		Player.ruby = new SimpleIntegerProperty(ruby);
		isCarriedSomething = new SimpleBooleanProperty();
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
	
	
	public int getRuby() {
		return Player.ruby.get();
	}
	
	public boolean removeRuby(int quantity) {
		if(ruby.get()>=quantity) {
			Player.ruby.set(Player.ruby.get()-quantity);
			return true;
		}else
			return false;
	}
	
	public void addRuby(int quantity) {
		if((ruby.get()+quantity)>maxRuby) {
			Player.ruby.set(ruby.get()+quantity);
		}else {
			Player.ruby.set(maxRuby);
		}
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
	
	public void giveItemUsable(Usable i) {
		usables.add(i);
		InventoryMenu.lastItemAdded.set(1);
		InventoryMenu.newItem.set(true);
	}
	
	public void giveItemWeapon(Weapon i) {
		weapons.add(i);
		InventoryMenu.lastItemAdded.set(2);
		InventoryMenu.newItem.set(true);
	}
	
	public void giveItemLoot(Loot i) {
		loots.add(i);
		InventoryMenu.lastItemAdded.set(3);
		InventoryMenu.newItem.set(true);
	}
	
	public void giveItemSpecial(Special i) {
		specials.add(i);
		InventoryMenu.lastItemAdded.set(4);
		InventoryMenu.newItem.set(true);
	}
	
	public void useLeftItem() {
	
	}
	
	public void useRightItem() {
		
	}
	
	public boolean grabEntity(CarriableEntity entity) {
		return entity.pickupEntity(this);
	}
	
	public boolean placeEntity(CarriableEntity entity) {
		return entity.placeEntity(this);
	}
	
	public void interact() {
		if(carriedEntity == null) {
			
			int dir = super.direction.getDirection();
			int x = (dir==Direction.West?(int)super.coordonnes.getX()-1:dir==Direction.East?(int)super.coordonnes.getX()+1:(int)super.coordonnes.getX());
			int y = (dir==Direction.South?(int)super.coordonnes.getY()+1:dir==Direction.North?(int)super.coordonnes.getY()-1:(int)super.coordonnes.getY());
			Entity e = World.currentMap.getEntity(x,y);
			if(e != null && e instanceof CarriableEntity) {	
				grabEntity((CarriableEntity)e);
				addAction(new CountActionConsumer(30,new FunctionCantMove()));
			}
		}else {
			placeEntity(this.carriedEntity);
		}
	}
}
