package game.modele.utils.ActionConsumer.Function;

import java.util.AbstractMap.SimpleEntry;

import game.modele.entity.Entity;
import game.modele.utils.Direction;
import game.modele.utils.graph.Graph;
import game.modele.world.World;

public class FunctionIA  extends Function{
	public int distance = 0;

	@Override
	public void Action(Entity e) {
		int x = (int)(e.coordonnes.getY());
		int y = (int)(e.coordonnes.getX());

		int xPlayer = (int)World.player.coordonnes.getX();
		int yPlayer = (int)World.player.coordonnes.getY();
		
		if(e.coordonnes.distance(World.player.coordonnes) > this.distance) {

			if(x  == xPlayer && y == yPlayer) {
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

			}else {
				if(World.currentMap.g.direction == null)return;
				SimpleEntry<Integer, int[]> d;
				try {
				d = World.currentMap.g.direction[x][y];
				}catch(IndexOutOfBoundsException p) {
					return;
				}
				if(d == null)
					return;
				
				if(d.getValue() == Graph.left) {
					dirigerEast(e);
				}else if(d.getValue() == Graph.right) {
					dirigerWest(e);
				}else if(d.getValue() == Graph.bot) {
					dirigerNorth(e);
				}else if(d.getValue() == Graph.top) {
					dirigerSouth(e);
				}
			}
		}else {
			stop(e);
		}
	}

	@Override
	public void Reset(Entity e) {
		stop(e);
	}

	public static void stop(Entity e) {
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
