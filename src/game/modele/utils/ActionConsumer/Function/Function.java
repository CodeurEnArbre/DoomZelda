package game.modele.utils.ActionConsumer.Function;

import game.modele.entity.Entity;
import game.modele.utils.ActionConsumer.ListConsumerAction;

public abstract class Function {
	ListConsumerAction listActionConsumer;
	
	public Function() {
		listActionConsumer = new ListConsumerAction();
	}
	
	public void Start(Entity e) {
		listActionConsumer.act(e);
		Action(e);
	}
	
	protected abstract void Action(Entity e);
	
	public void Reset(Entity e) {
		listActionConsumer.reset();
	}
	public void finishAction(Entity e) {}
}
