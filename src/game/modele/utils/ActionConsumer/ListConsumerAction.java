package game.modele.utils.ActionConsumer;
import game.modele.entity.Entity;
import game.modele.utils.ActionConsumer.Function.Function;

public class ListConsumerAction extends SimpleListActionConsumer implements ConsumerAction{

	public boolean act(Entity e) {
		for(int i = 0; i < list.size(); i++)
		{
			ConsumerAction c = list.get(i);
			if(!c.act(e))
			{
				c.getFunction().finishAction(e);
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