package game.modele.entity.living.friendly;

import game.modele.entity.living.EntityLiving;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;

public abstract class EntityFriendly extends EntityLiving{

	public EntityFriendly(String id,Coordonnees position, Direction direction) {
		super(id,position, direction);
	}

}
