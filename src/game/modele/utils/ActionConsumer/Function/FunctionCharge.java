package game.modele.utils.ActionConsumer.Function;

import java.awt.Point;

import game.modele.entity.Entity;
import game.modele.utils.graph.Graph;
import game.modele.world.World;

public class FunctionCharge extends Function{
	int[] d = null;

	public FunctionCharge() {
		super();
	}


	@Override
	protected void Action(Entity e) {
		d = World.currentMap.g.ligneDroite
				.get(new Point((int)e.coordonnes.getY(),(int)e.coordonnes.getX()));
		if(d == null)
			return;

		e.slow = 4;

		if(d.equals(Graph.right)) {
			FunctionIA.dirigerWest(e);
		}else if(d.equals(Graph.left)) {
			FunctionIA.dirigerEast(e);
		}else if(d.equals(Graph.top)) {
			FunctionIA.dirigerSouth(e);
		}else if(d.equals(Graph.bot)) {
			FunctionIA.dirigerNorth(e);		
		}
	}

	@Override
	public void Reset(Entity e) {
		super.Reset(e);
		e.slow = 1;
	}



}
