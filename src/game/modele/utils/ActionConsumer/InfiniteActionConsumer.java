package game.modele.utils.ActionConsumer;

import game.modele.entity.Entity;
import game.modele.utils.ActionConsumer.Function.Function;

public class InfiniteActionConsumer implements ConsumerAction{
	private Function ce;
	public InfiniteActionConsumer(Function c) 
	{	
		ce = c;
	}
	
	public Function getFunction() {
		return ce;
	}
	
	@Override
	public boolean act(Entity e) {
		ce.Start(e);
		return true;
	}

	@Override
	public void dispose() {}
}
