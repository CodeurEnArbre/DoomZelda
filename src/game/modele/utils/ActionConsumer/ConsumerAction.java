package game.modele.utils.ActionConsumer;

import game.modele.entity.Entity;
import game.modele.utils.ActionConsumer.Function.Function;

public interface ConsumerAction {
	public boolean act(Entity e) ;
	public Function getFunction();
	public void dispose();
}