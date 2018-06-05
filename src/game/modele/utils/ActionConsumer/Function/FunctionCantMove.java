package game.modele.utils.ActionConsumer.Function;

import game.modele.entity.Entity;
import game.modele.entity.living.EntityLiving;

public class FunctionCantMove extends Function{

	@Override
	public void Action(Entity e) {
		if(e instanceof EntityLiving) {
			EntityLiving entity = (EntityLiving)e;
			entity.isMovementLock.set(true);
		}
		ce.act(e);
	}
	
	@Override
	public void finishAction(Entity e) {
		if(e instanceof EntityLiving) {
			EntityLiving entity = (EntityLiving)e;
			entity.isMovementLock.set(false);
		}
	}

}
