package game.modele.utils.ActionConsumer;

import game.modele.entity.Entity;
import game.modele.utils.ActionConsumer.Function.Function;

public class SimpleActionConsumer implements ConsumerAction {

	private Function f;
	public SimpleActionConsumer(Function f) {
		this.f = f;
	}

	@Override
	public boolean act(Entity e) {
		f.Start(e);
		return false;
	}

	@Override
	public Function getFunction() {
		return f;
	}

	@Override
	public void dispose() {}

}
