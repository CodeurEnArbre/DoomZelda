package game.modele.utils.ActionConsumer.Function;

import game.modele.entity.Entity;
import game.modele.utils.graph.Graph;

public class FunctionKnockBack extends Function{

	int[] direction = Graph.top;
	public FunctionKnockBack(int[] direction) {
		this.direction = direction;
	}

	@Override
	protected void Action(Entity e) {
		if(direction.equals(Graph.top)) {
			e.addY(0.1);
		}else if(direction.equals(Graph.bot)) {
			e.addY(-0.1);
		}else if (direction.equals(Graph.left)) {
			e.addX(-0.1);
		}else if(direction.equals(Graph.right)) {
			e.addX(0.1);
		}
	}

}
