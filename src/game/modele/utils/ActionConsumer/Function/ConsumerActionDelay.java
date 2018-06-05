package game.modele.utils.ActionConsumer.Function;

import game.modele.entity.Entity;
import game.modele.utils.ActionConsumer.ConsumerAction;

public class ConsumerActionDelay  implements ConsumerAction {
	private int count;
	private Function ce;
	
	public ConsumerActionDelay(int nb,Function f) 
	{
		count = nb;	
		ce = f;
	}
	public Function getFunction() 
	{
		return ce;
	}
	public boolean act(Entity e) 
	{
		ce.Start(e);
		count--;
		if(count <= 0)
			ce.finishAction(e);
		return count > 0;
	}
}
