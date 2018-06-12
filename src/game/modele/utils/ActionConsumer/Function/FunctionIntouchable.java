package game.modele.utils.ActionConsumer.Function;

import game.modele.entity.Entity;
import game.modele.entity.living.EntityLiving;

public class FunctionIntouchable extends Function{

	@Override
	protected void Action(Entity e) {
		if(e instanceof EntityLiving) {
			((EntityLiving) e).isInvulnerable.set(true);
		}
		listActionConsumer.act(e);
	}
	
	@Override
	public void finishAction(Entity e) {
		if(e instanceof EntityLiving) {
			((EntityLiving) e).isInvulnerable.set(false);
		}
	}

}
