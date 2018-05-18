package game.modele.entity.Player;

import java.util.ArrayList;

import game.modele.entity.Entity;
import game.modele.entity.living.EntityLiving;
import game.modele.item.Item;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import game.modele.utils.ActionConsumer.FunctionBank;
import game.modele.utils.ActionConsumer.InfiniteActionConsumer;

public class Player extends EntityLiving{

	
	private ArrayList<Item> inventory;
	
	public Player(ArrayList<Item> inventory, Coordonnees position, Direction direction) {
		super("Player",position,direction);
		this.inventory=inventory;
		this.speed = baseSpeed;
		this.slow =	1;
		
		addAction(new InfiniteActionConsumer(FunctionBank.SimpleMove));
		addAction(new InfiniteActionConsumer(FunctionBank.SimpleMovement));
		
	}

	//Recuperer le nombre d'item dans l'inventaire
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
	public void incAnim() {
		this.etatDeplacement.set(
				this.etatDeplacement.get()
				+ (this.etatDeplacement.get() < 83 ? 1 : -83));
	}
	
	@Override
	public void active(Entity e) {

	}
}
