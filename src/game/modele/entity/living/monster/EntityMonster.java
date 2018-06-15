package game.modele.entity.living.monster;


import game.modele.entity.Entity;
import game.modele.entity.living.EntityLiving;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;

public abstract class EntityMonster extends EntityLiving {

	public EntityMonster(String id,Coordonnees position, Direction direction, int pv) {
		super(id,position, direction, pv);
	}

	@Override
	public void active(Entity e) {
		
	}
	
}
