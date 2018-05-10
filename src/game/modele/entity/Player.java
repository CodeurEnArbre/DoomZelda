package game.modele.entity;

import java.util.ArrayList;

import game.modele.item.Item;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;

public class Player extends EntityLiving{

	public final double baseSpeed = 0.12f;
	public final double maxSpeed = 0.25f;
	public final double acce = 0.00025f;
	
	private ArrayList<Item> inventory;

	public double speed = baseSpeed;

	public boolean moveUP;
	public boolean moveDown;
	public boolean moveRight;
	public boolean moveLeft;

	public Player(ArrayList<Item> inventory, Coordonnees position, Direction direction) {
		super(position,direction);
		this.inventory=inventory;
		this.hitBoxX = 32;
		this.hitBoxY = 32;
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
