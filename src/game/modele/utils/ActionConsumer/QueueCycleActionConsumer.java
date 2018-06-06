package game.modele.utils.ActionConsumer;

import game.modele.entity.Entity;
import game.modele.utils.ActionConsumer.Function.Function;

public class QueueCycleActionConsumer extends SimpleCycleActionConsumer implements ConsumerAction{

	@Override
	public boolean act(Entity e) {
		if(!(currentID < list.size()))currentID = 0;
		if(!list.get(currentID).act(e)) {
			list.get(currentID).getFunction().finishAction(e);
			list.remove(currentID);
		}
		return (list.size() > 0);
	}

	@Override
	public Function getFunction() {
		return list.get(currentID).getFunction();
	}

}
