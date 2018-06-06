package game.modele.utils.ActionConsumer;

import game.modele.entity.Entity;
import game.modele.utils.ActionConsumer.Function.SimpleListActionConsumer;

public class SimpleCycleActionConsumer extends SimpleListActionConsumer{

	protected int currentID = 0;
	public void Next(Entity e) {
		list.get(currentID).getFunction().Reset(e);
		currentID++;
		if(!Valid()) currentID = 0;
	}
	public boolean Valid() {
		return currentID < list.size();
	}
}
