package game.modele.entity.living.monster;

import game.modele.entity.Entity;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;

public class Zombie extends EntityMonster{

	public Zombie(Coordonnees c,Direction d) {
		super(0,c,d);
	}

	@Override
	public void update() {
		//TODO
		
	}

	@Override
	public boolean setCoordoner(Coordonnees coordonnees) {
		return false;
	}

	@Override
	public void active(Entity e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void incAnim() {
		// TODO Auto-generated method stub
		
	}

}
