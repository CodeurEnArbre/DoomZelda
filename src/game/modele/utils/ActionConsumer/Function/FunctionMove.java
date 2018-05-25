package game.modele.utils.ActionConsumer.Function;

import game.modele.entity.Entity;

public class FunctionMove extends Function {
	@Override
	public void Action(Entity e) {

		if(isNotWalking(e)) {
			e.speed = e.baseSpeed;
		}

		if(e.moveDown.active) {
			if(e.moveLeft.active ^ e.moveRight.active) {
				e.addY(currentDiagonalSpeed(e));
			}
			else 
			{
				speedUp(e);
				e.addY(currentSpeed(e));
			}
		}
		if(e.moveUP.active) {
			if(e.moveLeft.active ^ e.moveRight.active)
			{	
				e.addY(-currentDiagonalSpeed(e));	
			}
			else
			{
				speedUp(e);
				e.addY(-currentSpeed(e));	
			}
		}
		if(e.moveLeft.active) {
			if(e.moveUP.active ^ e.moveDown.active)
			{
				e.addX(-currentDiagonalSpeed(e));
			}
			else
			{	
				speedUp(e);
				e.addX(-currentSpeed(e));
			}
		}
		if(e.moveRight.active) {
			if(e.moveUP.active ^ e.moveDown.active)
			{
				e.addX(currentDiagonalSpeed(e));
			}
			else
			{
				speedUp(e);
				e.addX(currentSpeed(e)); 
			}
		}
	}

	public static boolean isNotWalking(Entity e) {
		return (!e.moveDown.active && !e.moveUP.active && !e.moveLeft.active && !e.moveRight.active);
	}
	public static void speedUp(Entity e) {
		if(e.speed < e.maxSpeed) {
			e.speed += e.acce;
		}
	}
	public static double currentSpeed(Entity e) {
		return e.speed * e.slow;
	}
	public static double currentDiagonalSpeed(Entity e) {
		return e.speed * 2/3 * e.slow;
	}
	

}
