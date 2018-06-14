package game.modele.utils.ActionConsumer.Function;

import game.modele.entity.Entity;

public class FunctionRaise extends Function{

	public FunctionRaise() {
		super();
	}
	
	@Override
	public void Action(Entity e) {
		e.slow = 0;
		e.incAnim();
		ce.act(e);
	}
	
	@Override
	public void finishAction(Entity e) {
		e.slow = 1;
	}
}
