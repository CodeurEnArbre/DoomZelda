package game.modele.entity;

import java.util.ArrayList;

import game.modele.item.Item;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;

public class Player extends EntityLiving{
	
	private ArrayList<Item> inventory;
	
	public Player(ArrayList<Item> inventory, Coordonnees position, Direction direction) {
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
