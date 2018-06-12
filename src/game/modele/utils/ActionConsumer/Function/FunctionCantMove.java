package game.modele.utils.ActionConsumer.Function;

import game.modele.entity.Entity;
import game.modele.entity.living.EntityLiving;

public class FunctionCantMove extends Function{

	public FunctionCantMove() {
		super();
	}
	
	@Override
	public void Action(Entity e) {
		if(e instanceof EntityLiving) {
			EntityLiving entity = (EntityLiving)e;
			entity.isMovementLock.set(true);
		}
		listActionConsumer.act(e);
	}
	
	@Override
	public void finishAction(Entity e) {
		if(e instanceof EntityLiving) {
			EntityLiving entity = (EntityLiving)e;
			entity.isMovementLock.set(false);
		}
	}

}
