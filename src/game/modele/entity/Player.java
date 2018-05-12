package game.modele.entity;

import java.util.ArrayList;

import game.modele.item.Item;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import game.vue.TexturesParametres;

public class Player extends EntityLiving{

	public final double baseSpeed = 0.12f;
	public final double maxSpeed = 0.25f;
	public final double acce = 0.00025f;
	
	private ArrayList<Item> inventory;

	public double speed = baseSpeed;

	//représente l'état d'une direction
	public class infoDeplacement{
		//la touche est enfoncé
		public boolean active = false;
		//la touche est enfoncé mais une autre prend le dessus
		public boolean attente = false;
	}
	public infoDeplacement moveUP;
	public infoDeplacement moveDown;
	public infoDeplacement moveRight;
	public infoDeplacement moveLeft;

	public Player(ArrayList<Item> inventory, Coordonnees position, Direction direction) {
		super(position,direction);
		this.inventory=inventory;
		this.hitBoxX = 32;
		this.hitBoxY = 32;
		this.textureParametres = new TexturesParametres("player", 24, 32, 0, 2);
		
		//déplacement
		moveUP = new infoDeplacement();
		moveDown = new infoDeplacement();
		moveLeft = new infoDeplacement();
		moveRight = new infoDeplacement();		
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
