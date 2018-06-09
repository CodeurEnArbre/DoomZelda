package game.modele.utils.ActionConsumer;
import game.modele.entity.Entity;
import game.modele.utils.ActionConsumer.Function.Function;
import game.modele.utils.ActionConsumer.Function.SimpleListActionConsumer;

public class ListConsumerAction extends SimpleListActionConsumer implements ConsumerAction{

	public boolean act(Entity e) {
		for(int i = 0; i < list.size(); i++)
		{
			if(!list.get(i).act(e))
			{
				list.get(i).getFunction().finishAction(e);
				list.remove(i);
			}
		}
		return list.size() > 0;
	}
	public void reset() {
		list.clear();
	}

	public Function getFunction() {
		return list.get(0).getFunction();
	}
	@Override
	public void dispose() {
		this.clear();
	}
}