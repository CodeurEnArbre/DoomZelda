package game.modele.utils.ActionConsumer.Function;

import game.modele.entity.Entity;
import game.modele.utils.ActionConsumer.CountActionConsumer;

public class FunctionMoveIce extends Function {
	public FunctionMoveIce() {
		super();
	}
	@Override
	public void Action(Entity e) {

		if(e.moveDown.active) {
			if(e.moveLeft.active ^ e.moveRight.active) {
				ce.add(new CountActionConsumer(60, new MoveIceDownAND()));
			}
			else {
				ce.add(new CountActionConsumer(60,new MoveIceDown()));
			}
		}
		else if(e.moveUP.active) {
			if(e.moveLeft.active ^ e.moveRight.active)
			{	
				ce.add(new CountActionConsumer(60,new MoveIceUpAND()));
			}else
			{
				ce.add(new CountActionConsumer(60,new MoveIceUp()));
			}
		}
		else if(e.moveLeft.active) {
			if(e.moveUP.active ^ e.moveDown.active)
			{
				ce.add(new CountActionConsumer(60,new MoveIceLeftAND()));
			}		else
			{	
				ce.add(new CountActionConsumer(60,new MoveIceLeft()));
			}
		}
		else if(e.moveRight.active) {
			if(e.moveUP.active ^ e.moveDown.active)
			{
				ce.add(new CountActionConsumer(60,new MoveIceRightAND()));
			}else
			{
				ce.add(new CountActionConsumer(60,new MoveIceRight()));
			}
		}
	}

	public void Reset(Entity e) {
		ce.reset();
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
