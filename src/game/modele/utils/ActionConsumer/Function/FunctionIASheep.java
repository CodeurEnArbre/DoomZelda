package game.modele.utils.ActionConsumer.Function;

import java.awt.Point;
import game.modele.entity.Entity;
import game.modele.utils.ActionConsumer.ConsumerAction;
import game.modele.utils.ActionConsumer.CountActionConsumer;
import game.modele.utils.ActionConsumer.InfiniteActionConsumer;
import game.modele.utils.ActionConsumer.QueueCycleActionConsumer;
import game.modele.world.World;

public class FunctionIASheep extends Function {
	FunctionIA fIA = new FunctionIA();
	
	private QueueCycleActionConsumer q = new QueueCycleActionConsumer();
	private ConsumerAction simpleIA = new InfiniteActionConsumer(fIA);
	private CountActionConsumer charge = new CountActionConsumer(42,new FunctionCharge());
	private CountActionConsumer att = new CountActionConsumer(20, new Function() {
		
		@Override
		protected void Action(Entity e) {
			e.slow = 0;				
		}
		
		@Override
		public void Reset(Entity e) {
			e.slow = 1;
		}
		
	});
	
	public FunctionIASheep() {
		super();
		fIA.distance = 3;
		q.add(simpleIA);
		listActionConsumer.add(q);
	}

	@Override
	protected void Action(Entity e) {
		if(World.currentMap.g.ligneDroite
				.get(new Point((int)e.coordonnes.getY()
						,(int)e.coordonnes.getX()))!= null 
						&& (q.getFunction() != charge.getFunction())
						&& (q.getFunction() != att.getFunction())
						&& e.coordonnes.distance(World.player.coordonnes) <= 3)	{
			charge.renew();
			att.renew();
			q.add(charge);
			q.add(att);
			q.Next(e);
		}
		
		
	}

}
