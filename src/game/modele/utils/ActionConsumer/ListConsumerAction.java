package game.modele.utils.ActionConsumer;

import java.util.ArrayList;

import game.modele.entity.Entity;
import game.modele.utils.ActionConsumer.Function.Function;

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
	public void del(Function ce) {
		for(int i = 0; i < list.size();i++) {
			if(list.get(i).toString().equals(ce.toString())) {
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