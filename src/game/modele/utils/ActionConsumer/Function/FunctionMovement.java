package game.modele.utils.ActionConsumer.Function;

import game.modele.entity.Entity;

public class FunctionMovement extends Function {
	@Override
	public void Action(Entity e) {
		if(e.moveDown.active || e.moveUP.active || e.moveLeft.active || e.moveRight.active) {
			e.incAnim();
		}
		if(!e.moveDown.active && !e.moveUP.active && !e.moveLeft.active && !e.moveRight.active) {
			e.resetAnim();
		}
	}
}
