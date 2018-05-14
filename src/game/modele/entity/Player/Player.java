package game.modele.entity.Player;

import java.util.ArrayList;

import game.modele.entity.living.EntityLiving;
import game.modele.item.Item;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import game.modele.world.World;
import game.vue.TexturesParametres;

public class Player extends EntityLiving{

	public final double baseSpeed = 0.12f;
	public final double maxSpeed = 0.20f;
	public final double acce = 0.00025f;
	
	private ArrayList<Item> inventory;

	private double speed = baseSpeed;

	//repr�sente l'�tat d'une direction
	public class infoDeplacement{
		//la touche est enfonc�
		public boolean active = false;
		//la touche est enfonc� mais une autre prend le dessus
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
		
		//d�placement
		moveUP = new infoDeplacement();
		moveDown = new infoDeplacement();
		moveLeft = new infoDeplacement();
		moveRight = new infoDeplacement();		
	}

	//R�cup�rer le nombre d'item dans l'inventaire
	public int getInventorySize() {
		return this.inventory.size();
	}

	public Item getInventoryItem(int index){
		return this.inventory.get(index);
	}

	public void addInventoryItem(Item item) {
		this.inventory.add(item);
	}

	@Override
	public void update() {
		if(!moveDown.active && !moveUP.active && !moveLeft.active && !moveRight.active) {
			resetAnim();
			speed = baseSpeed;
		}
		if(moveDown.active) {
			if(moveLeft.active ^ moveRight.active) {
				addY(speed * 2/3);
			}
			else {
				if(speed < maxSpeed) {
					speed += acce;
				}
				addY(speed);
				incAnim();
			}
		}
		if(moveUP.active) {
			if(moveLeft.active ^ moveRight.active)
			{	
				addY(-speed * 2/3);	
			}else
			{
				if(speed < maxSpeed) {
					speed += acce;
				}
				addY(-speed);	
				incAnim();
			}
		}
		if(moveLeft.active) {
			if(moveUP.active ^ moveDown.active)
			{
				addX(-speed * 2/3);
				incAnim();
			}		else
			{	
				if(speed < maxSpeed) {
					speed += acce;
				}
				addX(-speed);
				incAnim();
			}
		}
		if(moveRight.active) {
			if(moveUP.active ^ moveDown.active)
			{
				addX(speed * 2/3);
				incAnim();
			}else
			{
				if(speed < maxSpeed) {
					speed += acce;
				}
				addX(speed); 
				incAnim();
			}
		}
		
	}

	@Override
	public boolean setCoordoner(Coordonnees coordonnees) {
		int tileId = World.currentMap.getTile((int)coordonnees.getY(), (int)coordonnees.getX()).getId() ;
		return (tileId <= 1 &&
				coordonnees.getX() >= 0 &&
				coordonnees.getY() >= 0 &&
				(coordonnees.getX() + speed)< World.currentMap.getWidth()&&
				(coordonnees.getY() + speed) < World.currentMap.getHeight());
	
	}
	
	
}
