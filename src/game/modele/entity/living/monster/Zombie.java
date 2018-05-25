package game.modele.entity.living.monster;

import game.modele.entity.Entity;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import game.modele.utils.ActionConsumer.FunctionBank;
import game.modele.utils.ActionConsumer.InfiniteActionConsumer;

public class Zombie extends EntityMonster{

	public Zombie(Coordonnees c,Direction d) {
		super("Zombie",c,d);
		this.speed = 0.02;
		this.acce = 0;
		this.baseSpeed = 0.05;
		this.maxSpeed = 0.05;		
		addAction(new InfiniteActionConsumer(FunctionBank.SimpleMove));
		addAction(new InfiniteActionConsumer(FunctionBank.IAMove));
	}


	@Override
	public void active(Entity e) {

	}

	@Override
	public void incAnim() {

	}
}
