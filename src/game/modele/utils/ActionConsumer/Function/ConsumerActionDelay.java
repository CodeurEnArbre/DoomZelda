package game.modele.utils.ActionConsumer.Function;

import game.modele.entity.Entity;
import game.modele.utils.ActionConsumer.ConsumerAction;

public class ConsumerActionDelay  implements ConsumerAction {
	private int delay;
	private ConsumerAction c;

	public ConsumerActionDelay(int delay,ConsumerAction f) 
	{
		this.delay = delay;	
		c = f;
	}
	public Function getFunction() 
	{
		return c.getFunction();
	}
	public boolean act(Entity e) 
	{
		if(delay-- > 0) {
			return true;
		}else {
			return c.act(e);
		}
	}
	@Override
	public void dispose() {}
}
