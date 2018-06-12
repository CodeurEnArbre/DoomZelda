package game.modele.utils.ActionConsumer.Function;

import game.modele.entity.Entity;
import game.modele.entity.living.EntityLiving;

public class FunctionDamage extends Function{
	public FunctionDamage() {
		super();
	}
	@Override
	protected void Action(Entity e) {
		EntityLiving entity = (EntityLiving)e;
		if(!entity.isDamaged.get())
			entity.isDamaged.set(true);
		listActionConsumer.act(e);
	}

	@Override
	public void finishAction(Entity e) {
		EntityLiving entity = (EntityLiving)e;
		entity.isDamaged.set(false);
	}

}
