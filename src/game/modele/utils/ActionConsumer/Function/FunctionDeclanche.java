package game.modele.utils.ActionConsumer.Function;

import game.modele.entity.Entity;
import game.modele.utils.ActionConsumer.ConsumerAction;

public class FunctionDeclanche extends Function{

	
	private ConsumerAction[] action;
	public FunctionDeclanche(ConsumerAction... action) {
		this.action = action;
	}

	@Override
	protected void Action(Entity e) {
		for(ConsumerAction c : action)
			e.addAction(c);
	}
	
}
