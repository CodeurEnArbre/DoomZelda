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

	public final double baseSpeed = 0.15f;
	public final double maxSpeed = 0.3f;
	public final double acce = 0.0025f;


	public final double diaSpeed = 0.10f;
	private ArrayList<Item> inventory;

	public double speed = 0.15f;

	public boolean moveUP;
	public boolean moveDown;
	public boolean moveRight;
	public boolean moveLeft;

	public Player(ArrayList<Item> inventory, Coordonnees position, Direction direction) {
		super(position,direction);
		this.inventory=inventory;
	}

	//Récupérer la vitess de déplacement

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
