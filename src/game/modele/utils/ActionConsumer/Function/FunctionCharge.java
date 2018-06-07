package game.modele.utils.ActionConsumer.Function;

import java.awt.Point;

import game.modele.entity.Entity;
import game.modele.utils.ActionConsumer.CountActionConsumer;
import game.modele.utils.graph.Graph;
import game.modele.world.World;

public class FunctionCharge extends Function{
	int[] d = null;
	boolean atteint = false;

	public FunctionCharge() {
		super();
	}

	@Override
	protected void Action(Entity e) {
		if(d == null)
			d = World.currentMap.g.ligneDroite
			.get(new Point((int)e.coordonnes.getY(),(int)e.coordonnes.getX()));
		if(d == null)
			return;

		if(atteint)
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
		
		if(e.coordonnes.equals(World.player.coordonnes)) {
			e.slow = 0;
			World.player.addAction(
					new CountActionConsumer(15,
							new FunctionKnockBack(d)));
			atteint = true;
		}

	}

	@Override
	public void Reset(Entity e) {
		super.Reset(e);
		d = null;
		atteint = false;
		e.slow = 1;
	}
}
