package game.modele.utils.ActionConsumer;

import game.modele.entity.Entity;
import game.modele.utils.ActionConsumer.Function.Function;

public class CountActionConsumer implements ConsumerAction {
	private int count;
	private Function ce;
	public CountActionConsumer(int nb,Function f) 
	{
		count = nb;	
		ce = f;
	}
	public Function getEnum() 
	{
		return ce;
	}
	public boolean act(Entity e) 
	{
		ce.Action(e);
		count--;
		return count > 0;
	}
}
