package game.modele.utils.ActionConsumer;

import game.modele.entity.Entity;
import game.modele.utils.ActionConsumer.Function.Function;

public class InfiniteActionConsumer implements ConsumerAction{
	private Function ce;
	public InfiniteActionConsumer(Function c) 
	{	
		ce = c;
	}
	
	public Function getEnum() {
		return ce;
	}
	
	@Override
	public boolean act(Entity e) {
		ce.Action(e);
		return true;
	}
}
