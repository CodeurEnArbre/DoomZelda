package game.modele.utils.ActionConsumer.Function;

import java.awt.Point;
import game.modele.entity.Entity;
import game.modele.utils.ActionConsumer.ConsumerAction;
import game.modele.utils.ActionConsumer.CountActionConsumer;
import game.modele.utils.ActionConsumer.InfiniteActionConsumer;
import game.modele.utils.ActionConsumer.QueueCycleActionConsumer;
import game.modele.world.World;

public class FunctionIASheep extends Function {
	private QueueCycleActionConsumer q = new QueueCycleActionConsumer();
	private ConsumerAction simpleIA = new InfiniteActionConsumer(new FunctionIA());
	private CountActionConsumer charge = new CountActionConsumer(5,new FunctionCharge());

	public FunctionIASheep() {
		super();
		q.add(simpleIA);
		ce.add(q);
	}

	@Override
	protected void Action(Entity e) {
		if(World.currentMap.g.ligneDroite
				.get(new Point((int)e.coordonnes.getY()
						,(int)e.coordonnes.getX()))!= null 
						&& (q.getFunction() != charge.getFunction()))
		{
			charge.renew();
			q.add(charge);
			q.add(new CountActionConsumer(600, new Function() {
				
				@Override
				protected void Action(Entity e) {
					e.slow = 0;				
				}
				
				@Override
				public void Reset(Entity e) {
					e.slow = 1;
				}
				
			}));
			q.Next(e);
		}
		
		
	}

}
