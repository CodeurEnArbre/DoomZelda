package game.modele.utils.ActionConsumer.Function;


import game.modele.entity.Entity;
import game.modele.entity.living.EntityLiving;
import game.modele.utils.Direction;
import game.modele.utils.ActionConsumer.CountActionConsumer;
import game.modele.utils.graph.Graph;
import game.modele.world.World;

public class FunctionRigidbody extends Function{
	@Override
	protected void Action(Entity e) {
		for(Entity i : World.currentMap.entityHere(e.coordonnes.getX(),e.coordonnes.getY()))
		{
			if(i != e && i instanceof EntityLiving) {
				if(e.direction.getDirection() ==(Direction.North))
					i.addAction(new CountActionConsumer(1, new FunctionKnockBack(Graph.top)));
				else if(e.direction.getDirection() ==(Direction.South))
					i.addAction(new CountActionConsumer(1, new FunctionKnockBack(Graph.bot)));
				else if(e.direction.getDirection() ==(Direction.East))
					i.addAction(new CountActionConsumer(1, new FunctionKnockBack(Graph.left)));
				else if(e.direction.getDirection() ==(Direction.West))
					i.addAction(new CountActionConsumer(1, new FunctionKnockBack(Graph.right)));

			}
		}
	}	
}
