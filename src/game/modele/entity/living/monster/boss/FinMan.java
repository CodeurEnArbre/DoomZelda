package game.modele.entity.living.monster.boss;

import game.modele.entity.Entity;
import game.modele.entity.living.EntityLiving;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;

public class FinMan extends EntityLiving{

	public FinMan(Coordonnees c,Direction d) {
		super("FinMan",c,d);
	}

	@Override
	public void active(Entity e) {
		
	}

}
