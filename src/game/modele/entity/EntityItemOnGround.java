package game.modele.entity;

import game.modele.item.Item;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import game.modele.world.World;

public class EntityItemOnGround extends Entity{

	public Item item;
	
	public EntityItemOnGround(Coordonnees coordonnees, Direction direction, Item item) {
		super("ItemOnGround", coordonnees, direction);
		this.item=item;
	}

	@Override
	public void active(Entity e) {
		if(e.getId().equals("Player")) {
			World.player.pickupItem =  item;
			World.player.takeItem(item);
			World.currentMap.entity.remove(this);
		}
	}

	@Override
	public void incAnim() {}

}
