package game.modele.utils.ActionConsumer;

import java.util.ArrayList;

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
	public void del(String name,Entity e) {
		for(int i = 0; i < list.size();i++) {
			if(list.get(i).getFunction().toString().equals(name)) {
				list.get(i).getFunction().Reset(e);
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