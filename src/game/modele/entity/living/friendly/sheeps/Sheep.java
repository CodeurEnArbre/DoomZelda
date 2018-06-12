package game.modele.entity.living.friendly.sheeps;

import game.modele.entity.Entity;
import game.modele.entity.living.friendly.EntityFriendly;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import game.modele.utils.ActionConsumer.ConsumerAction;
import game.modele.utils.ActionConsumer.InfiniteActionConsumer;
import game.modele.utils.ActionConsumer.Function.FunctionIASheep;
import game.modele.utils.ActionConsumer.Function.FunctionMove;
import game.modele.utils.ActionConsumer.Function.FunctionMovement;

public class Sheep extends EntityFriendly{
	
	public ConsumerAction move = new InfiniteActionConsumer(new FunctionMove());
	public ConsumerAction IA = new InfiniteActionConsumer(new FunctionIASheep());
	public ConsumerAction mouvement = new InfiniteActionConsumer(new FunctionMovement());
	public Sheep(String id, Coordonnees position, Direction direction) {
		super(id, position, direction);
		this.speed = 0.04;
		this.baseSpeed = 0.04;
		this.acce = 0.0;
		addAction(move);
		addAction(IA);
		addAction(mouvement);

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
