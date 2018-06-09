package game.modele.utils.ActionConsumer.Function;

import java.util.ArrayList;

import game.modele.entity.Entity;
import game.modele.utils.ActionConsumer.ConsumerAction;

public class SimpleListActionConsumer {

	protected ArrayList<ConsumerAction> list = new ArrayList<>();
	public void add(ConsumerAction ce) 
	{
		list.add(ce);
	}
	public void del(ConsumerAction c,Entity e) {
		for(int i = 0; i < list.size();i++) {
			if(list.get(i) == c) {
				list.get(i).getFunction().Reset(e);
				list.remove(i);
				i--;
			}
		}
	}
	public void clear() {
		while(list.size() > 0) {
			list.get(0).dispose();
			list.remove(0);
		}
	}
}
