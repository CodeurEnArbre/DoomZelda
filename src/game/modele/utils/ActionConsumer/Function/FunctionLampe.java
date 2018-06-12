package game.modele.utils.ActionConsumer.Function;

import game.modele.entity.Entity;
import game.modele.entity.tileEntity.light.EntityLight;
import game.modele.world.World;

public class FunctionLampe extends Function{
	
	public FunctionLampe() {
		super();
	}
	
	//Work for EntityLight ONLY
	@Override
	protected void Action(Entity e) {		
		EntityLight l = (EntityLight)e;
		
		World.currentMap.MultiDirectionnalTorch(
				(int)l.coordonnes.getX(), (int)l.coordonnes.getY()
				,l.lightLvl + 1,1,false);
		
		World.currentMap.MultiDirectionnalTorch(
				(int)l.coordonnes.getX(), (int)l.coordonnes.getY()
				,l.lightLvl,1,true);
		
	}
}
