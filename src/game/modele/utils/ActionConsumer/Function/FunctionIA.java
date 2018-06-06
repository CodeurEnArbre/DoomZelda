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
			double dx = e.coordonnes.getY();
			double dy = e.coordonnes.getX();
			if(dx > World.player.coordonnes.getY()) {
				dirigerNorth(e);
			}else {
				dirigerSouth(e);
			}

			if(dy > World.player.coordonnes.getX()) {
				dirigerEast(e);
			}else {
				dirigerWest(e);
			}

		}

		if(World.currentMap.g.direction[x][y].getValue() == Graph.left) {
			dirigerEast(e);
		}else if(World.currentMap.g.direction[x][y].getValue() == Graph.right) {
			dirigerWest(e);
		}else if(World.currentMap.g.direction[x][y].getValue() == Graph.bot) {
			dirigerNorth(e);
		}else if(World.currentMap.g.direction[x][y].getValue() == Graph.top) {
			dirigerSouth(e);
		}
	}

	@Override
	public void Reset(Entity e) {
		e.moveDown.active = false;
		e.moveUP.active = false;
		e.moveLeft.active = false;
		e.moveRight.active = false;
	}
	
	public static void dirigerSouth(Entity e) {
		e.moveDown.active = true;
		e.moveUP.active = false;
		e.moveLeft.active = false;
		e.moveRight.active = false;
		e.direction.getDirectionProperty().set(Direction.South);
	}
	public static void dirigerNorth(Entity e) {
		e.moveUP.active = true;
		e.moveDown.active = false;
		e.moveLeft.active = false;
		e.moveRight.active = false;
		e.direction.getDirectionProperty().set(Direction.North);
	}
	public static void dirigerEast(Entity e) {
		e.moveLeft.active = true;
		e.moveRight.active = false;
		e.moveUP.active = false;
		e.moveDown.active = false;
		e.direction.getDirectionProperty().set(Direction.East);
	}
	public static void dirigerWest(Entity e) {
		e.moveLeft.active = false;
		e.moveRight.active = true;
		e.moveUP.active = false;
		e.moveDown.active = false;
		e.direction.getDirectionProperty().set(Direction.West);
	}
}
