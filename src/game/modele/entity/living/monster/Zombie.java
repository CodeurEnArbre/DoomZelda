package game.modele.entity.living.monster;

import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;

public class Zombie extends EntityMonster{

	public Zombie(Coordonnees c,Direction d) {
		super(c,d);
	}

	@Override
	public void update() {
		//TODO
		
	}

	@Override
	public boolean setCoordoner(Coordonnees coordonnees) {
		// TODO Auto-generated method stub
		return false;
	}

}
