package game.modele.utils.ActionConsumer;

import game.modele.entity.Entity;
import game.modele.utils.ActionConsumer.Function.Function;

public class OptimizedActionConsumer implements ConsumerAction{

	private int reduction;
	private int current;
	private ConsumerAction c;
	
	public OptimizedActionConsumer(ConsumerAction c,int reduction) {
		this.reduction = reduction;
		this.current = reduction;
		this.c = c;
	}

	@Override
	public boolean act(Entity e) {
		if(current-- > 0) {
			return true;
		}else {
			current = reduction;
			return c.act(e);
		}
	}

	@Override
	public Function getFunction() {
		return c.getFunction();
	}

	@Override
	public void dispose() {
		c.dispose();
	}
}
