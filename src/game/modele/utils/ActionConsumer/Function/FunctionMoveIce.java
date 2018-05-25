package game.modele.utils.ActionConsumer.Function;

import game.modele.entity.Entity;
import game.modele.utils.ActionConsumer.CountActionConsumer;

public class FunctionMoveIce extends Function {
	@Override
	public void Action(Entity e) {
		if(!e.moveDown.active && !e.moveUP.active && !e.moveLeft.active && !e.moveRight.active) {
			e.speed = e.baseSpeed;
		}
		if(e.moveDown.active) {
			if(e.moveLeft.active ^ e.moveRight.active) {
				e.addAction(new CountActionConsumer(60, new MoveIceDownAND()));
			}
			else {
				e.addAction(new CountActionConsumer(60,new MoveIceDown()));
			}
		}
		if(e.moveUP.active) {
			if(e.moveLeft.active ^ e.moveRight.active)
			{	
				e.addAction(new CountActionConsumer(60,new MoveIceUpAND()));
			}else
			{
				e.addAction(new CountActionConsumer(60,new MoveIceUp()));
			}
		}
		if(e.moveLeft.active) {
			if(e.moveUP.active ^ e.moveDown.active)
			{
				e.addAction(new CountActionConsumer(60,new MoveIceLeftAND()));
			}		else
			{	
				e.addAction(new CountActionConsumer(60,new MoveIceLeft()));
			}
		}
		if(e.moveRight.active) {
			if(e.moveUP.active ^ e.moveDown.active)
			{
				e.addAction(new CountActionConsumer(60,new MoveIceRightAND()));
			}else
			{
				e.addAction(new CountActionConsumer(60,new MoveIceRight()));
			}
		}
	}

	@Override
	public void Reset(Entity e) {
		
		
		
		
		
	}
	
	private class MoveIceDownAND extends Function{
		@Override
		public void Action(Entity e) {
			e.addY(e.speed * 2/30 * e.slow);
		}
	}
	private class MoveIceDown extends Function{
		@Override
		public void Action(Entity e) {
			e.addY(e.speed * e.slow / 15);
		}
	}
	private class MoveIceUpAND extends Function{
		@Override
		public void Action(Entity e) {
			e.addY(-e.speed * 2/30 * e.slow);
		}
	}
	private class MoveIceUp extends Function{
		@Override
		public void Action(Entity e) {
			e.addY(-e.speed * e.slow / 15);
		}
	}
	private class MoveIceRightAND extends Function{
		@Override
		public void Action(Entity e) {
			e.addX(e.speed * 2/30 * e.slow);
		}
	}
	private class MoveIceRight extends Function{
		@Override
		public void Action(Entity e) {
			e.addX(e.speed * e.slow / 15);
		}
	}
	private class MoveIceLeftAND extends Function{
		@Override
		public void Action(Entity e) {
			e.addX(-e.speed * 2/30 * e.slow);
		}
	}
	private class MoveIceLeft extends Function{
		@Override
		public void Action(Entity e) {
			e.addX(-e.speed * e.slow / 15);
		}
	}
}
