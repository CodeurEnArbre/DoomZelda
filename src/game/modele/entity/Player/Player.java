package game.modele.entity.Player;

import java.util.ArrayList;

import game.modele.entity.Entity;
import game.modele.entity.living.EntityLiving;
import game.modele.item.Item;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;

public class Player extends EntityLiving{

	private final double baseSpeed = 0.11f;
	private final double maxSpeed = 0.16f;
	private final double acce = 0.00025f;
	private ArrayList<Item> inventory;
	
	public Player(ArrayList<Item> inventory, Coordonnees position, Direction direction) {
		super(-1,position,direction);
		this.inventory=inventory;
		this.speed = baseSpeed;
		this.slow =	1;
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
				addY(speed * 2/3 * slow);
			}
			else {
				if(speed < maxSpeed) {
					speed += acce;
				}
				addY(speed * slow);
				incAnim();
			}
		}
		if(moveUP.active) {
			if(moveLeft.active ^ moveRight.active)
			{	
				addY(-speed * 2/3 * slow);	
			}else
			{
				if(speed < maxSpeed) {
					speed += acce;
				}
				addY(-speed * slow);	
				incAnim();
			}
		}
		if(moveLeft.active) {
			if(moveUP.active ^ moveDown.active)
			{
				addX(-speed * 2/3 * slow);
				incAnim();
			}		else
			{	
				if(speed < maxSpeed) {
					speed += acce;
				}
				addX(-speed * slow);
				incAnim();
			}
		}
		if(moveRight.active) {
			if(moveUP.active ^ moveDown.active)
			{
				addX(speed * 2/3 * slow);
				incAnim();
			}else
			{
				if(speed < maxSpeed) {
					speed += acce;
				}
				addX(speed * slow); 
				incAnim();
			}
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
