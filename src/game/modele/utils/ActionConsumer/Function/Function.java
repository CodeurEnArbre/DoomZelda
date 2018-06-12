package game.modele.utils.ActionConsumer.Function;

import game.modele.entity.Entity;
import game.modele.utils.ActionConsumer.ListConsumerAction;

public abstract class Function {
	ListConsumerAction ce;
	
	public Function() {
		ce = new ListConsumerAction();
	}
	
	public void Start(Entity e) {
		ce.act(e);
		Action(e);
	}
	
	protected abstract void Action(Entity e);
	
	public void Reset(Entity e) {
		ce.reset();
	}
	public void finishAction(Entity e) {}
}
