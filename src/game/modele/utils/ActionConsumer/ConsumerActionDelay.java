package game.modele.utils.ActionConsumer;

import game.modele.entity.Entity;
import game.modele.utils.ActionConsumer.Function.Function;

public class ConsumerActionDelay  implements ConsumerAction {
	private int initDelay;
	private int delay;
	private ConsumerAction c;

	public ConsumerActionDelay(int delay,ConsumerAction f) 
	{
		this.initDelay = delay;
		this.delay = delay;	
		c = f;
	}
	public void renew() {
		this.delay = initDelay;
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
