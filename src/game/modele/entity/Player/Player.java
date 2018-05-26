package game.modele.entity.Player;

import java.util.ArrayList;

import game.modele.entity.Entity;
import game.modele.entity.living.EntityLiving;
import game.modele.item.loot.Loot;
import game.modele.item.special.Special;
import game.modele.item.usable.Usable;
import game.modele.item.weapon.Weapon;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import game.modele.utils.ActionConsumer.InfiniteActionConsumer;
import game.modele.utils.ActionConsumer.Function.FunctionMove;
import game.modele.utils.ActionConsumer.Function.FunctionMovement;
import javafx.beans.property.IntegerProperty;

public class Player extends EntityLiving{

	
	public ArrayList<Usable> usables;
	public ArrayList<Weapon> weapons;
	public ArrayList<Loot> loots;
	public ArrayList<Special> specials;
	
	private int maxRuby=100;
	private IntegerProperty ruby; //ARGENT!!!
	
	public Player(ArrayList<Loot> loots, Coordonnees position, Direction direction) {
		super("Player",position,direction);
		this.loots=loots;
		this.speed = baseSpeed;
		this.slow =	1;
		
		addAction(new InfiniteActionConsumer(new FunctionMove()));
		addAction(new InfiniteActionConsumer(new FunctionMovement()));
		
		usables = new ArrayList<Usable>();
		weapons = new ArrayList<Weapon>();
		specials = new ArrayList<Special>();
	}
	
	public int getRuby() {
		return this.ruby.get();
	}
	
	public boolean removeRuby(int quantity) {
		if(ruby.get()>=quantity) {
			this.ruby.set(this.ruby.get()-quantity);
			return true;
		}else
			return false;
	}
	
	public void addRuby(int quantity) {
		if((ruby.get()+quantity)>maxRuby) {
			this.ruby.set(ruby.get()+quantity);
		}else {
			this.ruby.set(maxRuby);
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

	}
	
	public void giveItemUsable(Usable i) {
		usables.add(i);
	}
	
	public void giveItemWeapon(Weapon i) {
		weapons.add(i);
	}
	
	public void giveItemLoot(Loot i) {
		loots.add(i);
	}
	
	public void giveItemSpecial(Special i) {
		specials.add(i);
	}
}
