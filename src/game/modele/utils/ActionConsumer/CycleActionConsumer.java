package game.modele.utils.ActionConsumer;
import game.modele.entity.Entity;
import game.modele.utils.ActionConsumer.Function.Function;

public class CycleActionConsumer extends SimpleCycleActionConsumer implements ConsumerAction{
	
	public boolean act(Entity e) {
		if(!list.get(currentID).act(e)) {
			list.get(currentID).getFunction().finishAction(e);
			list.remove(currentID);
		}else {
			Next(e);
		}
		return (list.size() > 0);
	}

	public Function getFunction() {
		return list.get(currentID).getFunction();
	}
	@Override
	public void dispose() {
		this.clear();
	}
}
