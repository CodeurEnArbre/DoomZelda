package game.modele.utils.ActionConsumer.Function;

import java.awt.Point;

import game.modele.entity.Entity;
import game.modele.utils.ActionConsumer.ConsumerAction;
import game.modele.utils.ActionConsumer.InfiniteActionConsumer;
import game.modele.world.World;

public class FunctionIASheep extends Function {

	private ConsumerAction simpleIA = new InfiniteActionConsumer(new FunctionIA());

	public FunctionIASheep() {
		super();
		ce.add(simpleIA);
	}
	
	@Override
	protected void Action(Entity e) {
		if(World.currentMap.g.ligneDroite
				.get(new Point((int)e.coordonnes.getX()
						,(int)e.coordonnes.getY()))!= null) {
			ce.del(simpleIA,e);
			
			
			
			
		}
		
	}

}
