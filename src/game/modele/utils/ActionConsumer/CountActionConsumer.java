package game.modele.utils.ActionConsumer;

import game.modele.entity.Entity;
import game.modele.utils.ActionConsumer.Function.Function;

public class CountActionConsumer implements ConsumerAction {
	private int value;
	private int count;
	private Function ce;
	
	public CountActionConsumer(int nb,Function f) 
	{
		value = nb;
		count = nb;
		ce = f;
	}
	public void renew() {
		count = value;
	}
	public Function getFunction() 
	{
		return ce;
	}
	public boolean act(Entity e) 
	{
		ce.Start(e);
		count--;
		return count > 0;
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
