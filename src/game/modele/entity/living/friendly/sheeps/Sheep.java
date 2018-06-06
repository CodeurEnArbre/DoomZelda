package game.modele.entity.living.friendly.sheeps;

import game.modele.entity.Entity;
import game.modele.entity.living.friendly.EntityFriendly;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import game.modele.utils.ActionConsumer.ConsumerAction;
import game.modele.utils.ActionConsumer.InfiniteActionConsumer;
import game.modele.utils.ActionConsumer.Function.FunctionIA;
import game.modele.utils.ActionConsumer.Function.FunctionIASheep;
import game.modele.utils.ActionConsumer.Function.FunctionMove;

public class Sheep extends EntityFriendly{

	public ConsumerAction move = new InfiniteActionConsumer(new FunctionMove());
	public ConsumerAction IA = new InfiniteActionConsumer(new FunctionIASheep());
	
	public Sheep(String id, Coordonnees position, Direction direction) {
		super(id, position, direction);
		addAction(move);
		addAction(IA);

	}

	@Override
	public void active(Entity e) {


	}
}