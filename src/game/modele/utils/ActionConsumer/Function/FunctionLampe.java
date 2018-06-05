package game.modele.utils.ActionConsumer.Function;

import game.modele.entity.Entity;
import game.modele.utils.Direction;
import game.modele.world.World;

public class FunctionLampe extends Function{
	@Override
	protected void Action(Entity e) {
		World.currentMap.MultiDirectionnalTorch(
				(int)e.coordonnes.getX(), (int)e.coordonnes.getY(),16,4,false);
		
		World.currentMap.MultiDirectionnalTorch(
				(int)e.coordonnes.getX(), (int)e.coordonnes.getY(),16,4,true);
	}

}
