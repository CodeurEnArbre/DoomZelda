package game.modele.utils.ActionConsumer;

import game.modele.entity.Entity;
import game.modele.utils.ActionConsumer.Function.Function;

public class InfiniteActionConsumer implements ConsumerAction{
	private Function function;
	public InfiniteActionConsumer(Function c) 
	{	
		function = c;
	}
	
	public Function getFunction() {
		return function;
	}
	
	@Override
	public boolean act(Entity e) {
		function.Start(e);
		return true;
	}

	@Override
	public void dispose() {}
}
