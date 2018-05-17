package game.modele.entity.living.monster;

import game.modele.entity.Entity;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;

public class Zombie extends EntityMonster{

	public Zombie(Coordonnees c,Direction d) {
		super("Zombie",c,d);
	}

	@Override
	public void update() {

	}

	@Override
	public void active(Entity e) {
		System.out.println("Bonjour je suis " + primaryKey);
		
	}

	@Override
	public void incAnim() {
		// TODO Auto-generated method stub
		
	}

}
