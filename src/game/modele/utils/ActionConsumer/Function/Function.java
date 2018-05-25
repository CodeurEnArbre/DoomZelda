package game.modele.utils.ActionConsumer.Function;

import game.modele.entity.Entity;

public abstract class Function {
	public abstract void Action(Entity e);
	public void Reset(Entity e) {
		
	}
	public String toString() {
		return this.getClass().getName();
	}
}
