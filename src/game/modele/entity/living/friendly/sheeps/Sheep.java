package game.modele.entity.living.friendly.sheeps;

import game.modele.entity.Entity;
import game.modele.entity.living.friendly.EntityFriendly;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;

public class Sheep extends EntityFriendly{

	public Sheep(String id, Coordonnees position, Direction direction) {
		super(id, position, direction);
		this.textureHeight = 48;
		this.textureWidth = 48;
	}

	@Override
	public void active(Entity e) {
		// TODO Auto-generated method stub
		
	}
}
