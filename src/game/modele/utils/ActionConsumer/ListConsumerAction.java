package game.modele.utils.ActionConsumer;

import java.util.ArrayList;
import java.util.function.Consumer;

import game.modele.entity.Entity;

public class ListConsumerAction{
	private ArrayList<ConsumerAction> list;
	public ListConsumerAction() 
	{
		list = new ArrayList<>();
	}
	public void add(ConsumerAction ce) 
	{
		list.add(ce);
	}
	public void del(FunctionBank ce) {
		for(int i = 0; i < list.size();i++) {
			if(list.get(i).getEnum().index() == ce.index()) {
				list.remove(i);
				i--;
			}
			
		}
	}
	public void act(Entity e) {
		for(int i = 0; i < list.size(); i++)
		{
			if(!list.get(i).act(e))
				list.remove(i);
		}
	}
}