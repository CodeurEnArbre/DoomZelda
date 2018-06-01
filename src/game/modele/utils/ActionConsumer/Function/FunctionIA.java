package game.modele.utils.ActionConsumer.Function;

import game.modele.entity.Entity;
import game.modele.utils.Direction;
import game.modele.utils.graph.Graph;
import game.modele.world.World;

public class FunctionIA  extends Function{
	@Override
	public void Action(Entity e) {
		int x = (int)(e.coordonnes.getY());
		int y = (int)(e.coordonnes.getX());

		if(x  == (int)World.player.coordonnes.getY()
				&& y == (int)World.player.coordonnes.getX()) {
			e.moveLeft.active = false;
			e.moveRight.active = false;
			e.moveDown.active = false;
			e.moveUP.active = false;
		}

		try {
			if(World.currentMap.g.direction[x][y].getValue() == Graph.left) {
				e.moveLeft.active = true;
				e.moveRight.active = false;
				e.direction.getDirectionProperty().set(Direction.East);
			}else if(World.currentMap.g.direction[x][y].getValue() == Graph.right) {
				e.moveLeft.active = false;
				e.moveRight.active = true;
				e.direction.getDirectionProperty().set(Direction.West);
			}else if(World.currentMap.g.direction[x][y].getValue() == Graph.bot) {
				e.moveUP.active = true;
				e.moveDown.active = false;
				e.direction.getDirectionProperty().set(Direction.North);
			}else if(World.currentMap.g.direction[x][y].getValue() == Graph.top) {
				e.moveDown.active = true;
				e.moveUP.active = false;
				e.direction.getDirectionProperty().set(Direction.South);
			}
		}catch(NullPointerException n) {}	
	}
	
	
}
