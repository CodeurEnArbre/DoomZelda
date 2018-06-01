package game.modele.utils.ActionConsumer.Function;

import game.modele.entity.Entity;
import game.modele.utils.Direction;
import game.modele.world.World;

public class FunctionLampe extends Function{
	@Override
	protected void Action(Entity e) {
		if(e.moveDown.active) {
			World.currentMap.DirectionnalTorch((int)e.coordonnes.getX(), (int)e.coordonnes.getY(),8,1,Direction.North,true);
		}else {
			World.currentMap.DirectionnalTorch((int)e.coordonnes.getX(), (int)e.coordonnes.getY(),8,1,Direction.North,false);
		}



		if(e.moveLeft.active) {
			World.currentMap.DirectionnalTorch((int)e.coordonnes.getX(), (int)e.coordonnes.getY(),8,1,Direction.East,true);
		}else {
			World.currentMap.DirectionnalTorch((int)e.coordonnes.getX(), (int)e.coordonnes.getY(),8,1,Direction.East,false);


		}
		if(e.moveRight.active) {
			World.currentMap.DirectionnalTorch((int)e.coordonnes.getX(), (int)e.coordonnes.getY(),8,1,Direction.West,true);
		}else {
			World.currentMap.DirectionnalTorch((int)e.coordonnes.getX(), (int)e.coordonnes.getY(),8,1,Direction.West,false);
				
		}
		if(e.moveUP.active) {
			World.currentMap.DirectionnalTorch((int)e.coordonnes.getX(), (int)e.coordonnes.getY(),8,1,Direction.South,true);
		}else {
			World.currentMap.DirectionnalTorch((int)e.coordonnes.getX(), (int)e.coordonnes.getY(),8,1,Direction.South,false);
		}
	}

}
