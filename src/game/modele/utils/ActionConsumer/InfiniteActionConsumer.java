package game.modele.utils.ActionConsumer;

import game.modele.entity.Entity;

public class InfiniteActionConsumer implements ConsumerAction{
	private FunctionBank ce;
	public InfiniteActionConsumer(FunctionBank c) 
	{	
		ce = c;
	}
	
	public FunctionBank getEnum() {
		return ce;
	}
	
	@Override
	public boolean act(Entity e) {
		ce.element.accept(e);
		return true;
	}
}
