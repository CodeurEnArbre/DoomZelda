package game.modele.entity;

import java.util.ArrayList;

import game.modele.item.Item;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Player extends EntityLiving{
	
	private ArrayList<Item> inventory;
	
	private IntegerProperty maxPv;
	
	public Player(ArrayList<Item> inventory, Coordonnees position, Direction direction) {
		super(position,direction);
		this.inventory=inventory;
		maxPv=new SimpleIntegerProperty(12);
	}
	
	public int getInventorySize() {
		return this.inventory.size();
	}
	
	public Item getInventoryItem(int index){
		return this.inventory.get(index);
	}
	
	public void addInventoryItem(Item item) {
		this.inventory.add(item);
	}
	
	public void addHeart() {
		this.maxPv.add(4);
	}
	
	public IntegerProperty getMaxPv() {
		return this.maxPv;
	}
}
