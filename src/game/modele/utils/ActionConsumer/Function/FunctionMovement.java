package game.modele.utils.ActionConsumer.Function;

import game.modele.entity.Entity;

public class FunctionMovement extends Function {
	public FunctionMovement() {
		super();
	}
	
	@Override
	public void Action(Entity e) {
		if(FunctionMove.isNotWalking(e)) {
			e.resetAnim();
		}else {
			e.incAnim();
		}
	}
}
