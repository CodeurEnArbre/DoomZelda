package game.modele.entity;

import java.util.ArrayList;

import game.modele.item.Item;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Player extends EntityLiving{
	
	private ArrayList<Item> inventory;
	private FloatProperty speed;
	
	public boolean moveUP;
	public boolean moveDown;
	public boolean moveRight;
	public boolean moveLeft;
	
	public IntegerProperty animationIndice = new SimpleIntegerProperty(0);
	
	public Player(ArrayList<Item> inventory, Coordonnees position, Direction direction) {
		super(position,direction);
		this.inventory=inventory;
		this.speed = new SimpleFloatProperty();
	}
	
	//Récupérer la vitess de déplacement
	public FloatProperty getSpeed() {
		return this.speed;
	}
	
	public void incAnim() {
		this.animationIndice.set(
				this.animationIndice.get()
				+ (this.animationIndice.get() < 11 ? 1 : -11));
	}
	
	public IntegerProperty getAnimationProperty() {
		return this.animationIndice;
	}
	
	
	public void resetAnim() {
		this.animationIndice.set(0);
	}
	
	
	//Récupérer le nombre d'item dans l'inventaire
	public int getInventorySize() {
		return this.inventory.size();
	}
	
	public Item getInventoryItem(int index){
		return this.inventory.get(index);
	}
	
	public void addInventoryItem(Item item) {
		this.inventory.add(item);
	}

}
