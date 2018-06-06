package game.modele.utils.ActionConsumer.Function;

import java.awt.Point;
import java.util.stream.Stream;

import game.modele.entity.Entity;
import game.modele.utils.ActionConsumer.ConsumerAction;
import game.modele.utils.ActionConsumer.CountActionConsumer;
import game.modele.utils.ActionConsumer.InfiniteActionConsumer;
import game.modele.utils.ActionConsumer.QueueCycleActionConsumer;
import game.modele.world.World;

public class FunctionIASheep extends Function {
	private QueueCycleActionConsumer q = new QueueCycleActionConsumer();
	private ConsumerAction simpleIA = new InfiniteActionConsumer(new FunctionIA());
	private ConsumerAction charge = new InfiniteActionConsumer(new FunctionCharge());
	
	
	public FunctionIASheep() {
		super();
		q.add(simpleIA);
		q.add(charge);
		ce.add(q);
	}
	
	@Override
	protected void Action(Entity e) {
		if(World.currentMap.g.ligneDroite
				.get(new Point((int)e.coordonnes.getX()
						,(int)e.coordonnes.getY()))!= null) {
			q.Next(e);
		}
		
	}

}
