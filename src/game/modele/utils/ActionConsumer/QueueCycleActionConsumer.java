package game.modele.utils.ActionConsumer;

import game.modele.entity.Entity;
import game.modele.utils.ActionConsumer.Function.Function;

/* Cette classe représente une liste d'actionConsumer
 * Les actionsConsumers sont éxécuté a la suite
 * On attend la fin de l'un pour commencer l'autre
 */
public class QueueCycleActionConsumer extends SimpleCycleActionConsumer implements ConsumerAction{

	@Override
	public boolean act(Entity e) {
		if(!(currentID < list.size()))
			currentID = 0;
		
		if(!list.get(currentID).act(e)) {
			list.get(currentID).getFunction().finishAction(e);
			list.get(currentID).getFunction().Reset(e);
			list.remove(currentID);
		}
		return (list.size() > 0);
	}

	@Override
	public Function getFunction() {
		if(currentID < list.size())
			return list.get(currentID).getFunction();
		else
			return null;
	}
	@Override
	public void dispose() {
		this.clear();
	}
}
