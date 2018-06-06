package game.modele.utils.ActionConsumer.Function;

import java.awt.Point;

import game.modele.entity.Entity;
import game.modele.utils.Direction;
import game.modele.world.World;

public class FunctionCharge extends Function{
	int[] d = null;
	
	public FunctionCharge() {
		super();
	}
	
	
	@Override
	protected void Action(Entity e) {
		if(d == null)
			d = World.currentMap.g.ligneDroite
			.get(new Point((int)e.coordonnes.getX(),(int)e.coordonnes.getY()));
		
		e.slow = 2;
		if(d.equals(Direction.East)) {
			FunctionIA.dirigerEast(e);
		}else if(d.equals(Direction.West)) {
			FunctionIA.dirigerWest(e);
		}else if(d.equals(Direction.North)) {
			FunctionIA.dirigerNorth(e);
		}else if(d.equals(Direction.South)) {
			FunctionIA.dirigerSouth(e);		
		}
	}
	
	@Override
	public void Reset(Entity e) {
		super.Reset(e);
		e.slow = 1;
	}
	
	
	
}
