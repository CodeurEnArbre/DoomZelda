package game.controleur.entity;

import java.util.ArrayList;

import game.controleur.item.Item;
import game.controleur.utils.Coordoner;
import game.controleur.utils.Orientation.Direction;

public class Player {
	
	private ArrayList<Item> inventory;
	
	private Coordoner position;//La position du joueur
	
	private Direction direction;//La direction auquel il regarde
	
	public Player(ArrayList<Item> inventory, Coordoner position) {
		this.inventory=inventory;
		this.position=position;
		this.direction=Direction.SOUTH;
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
	
	public Coordoner getCoordoner() {
		return this.position;
	}
	
	public void setCoordoner(Coordoner position) {
		this.position=position;
	}
	
	public Direction getDirection() {
		return this.direction;
	}
	
	public void setDirection(Direction direction) {
		this.direction=direction;
	}
	
}
