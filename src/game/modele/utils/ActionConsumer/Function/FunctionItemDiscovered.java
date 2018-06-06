package game.modele.utils.ActionConsumer.Function;

import game.modele.entity.Entity;

public class FunctionItemDiscovered extends Function{

	@Override
	protected void Action(Entity e) {
		e.incAnim();
		System.out.println("ANimInc");
	}

}
