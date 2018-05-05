package game.controleur.entity;

import java.util.ArrayList;

import game.controleur.item.Item;
import game.controleur.utils.Coordoner;
import game.controleur.utils.Orientation.Direction;

public class Player extends EntityLiving{
	
	private ArrayList<Item> inventory;
	
	public Player(ArrayList<Item> inventory, Coordoner position, Direction direction) {
		super(position,direction);
		this.inventory=inventory;
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
	
}
