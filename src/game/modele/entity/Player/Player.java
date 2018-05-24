package game.modele.entity.Player;

import java.util.ArrayList;

import game.modele.entity.Entity;
import game.modele.entity.living.EntityLiving;
import game.modele.item.Item;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import game.modele.utils.ActionConsumer.FunctionBank;
import game.modele.utils.ActionConsumer.InfiniteActionConsumer;
import javafx.beans.property.IntegerProperty;

public class Player extends EntityLiving{

	
	public ArrayList<Item> usables;
	public ArrayList<Item> weapons;
	public ArrayList<Item> loots;
	public ArrayList<Item> specials;
	
	private int maxRuby=100;
	private IntegerProperty ruby; //ARGENT!!!
	
	public Player(ArrayList<Item> loots, Coordonnees position, Direction direction) {
		super("Player",position,direction);
		this.loots=loots;
		this.speed = baseSpeed;
		this.slow =	1;
		
		addAction(new InfiniteActionConsumer(FunctionBank.SimpleMove));
		addAction(new InfiniteActionConsumer(FunctionBank.SimpleMovement));
		
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
}
