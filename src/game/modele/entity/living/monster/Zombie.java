package game.modele.entity.living.monster;

import game.modele.entity.Entity;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import game.modele.utils.ActionConsumer.InfiniteActionConsumer;
import game.modele.utils.ActionConsumer.Function.FunctionIA;
import game.modele.utils.ActionConsumer.Function.FunctionMove;
import game.modele.utils.ActionConsumer.Function.FunctionMovement;

public class Zombie extends EntityMonster{

	public Zombie(Coordonnees c,Direction d) {
		super("Zombie",c,d);
		this.speed = 0.02;
		this.acce = 0;
		this.baseSpeed = 0.05;
		this.maxSpeed = 0.05;		
		addAction(new InfiniteActionConsumer(new FunctionMove()));
		addAction(new InfiniteActionConsumer(new FunctionIA()));
		addAction(new InfiniteActionConsumer(new FunctionMovement()));
	}

	@Override
	public void dispose() {
		delAction(FunctionMove.class.getName());
		delAction(FunctionIA.class.getName());
	}
	@Override
	public void active(Entity e) {

	}

	@Override
	public void incAnim() {
		this.etatDeplacement.set(
				this.etatDeplacement.get()
				+ (this.etatDeplacement.get() < 83 ? 1 : -83));
	}
}
