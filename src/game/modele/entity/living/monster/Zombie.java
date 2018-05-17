package game.modele.entity.living.monster;

import java.util.ArrayList;
import java.util.function.Consumer;

import game.modele.entity.Entity;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;

public class Zombie extends EntityMonster{

	public class ConsumerAction {
		private int count;
		private Consumer<Entity> ce;
		public ConsumerAction(int nb,Consumer<Entity> c) {
			count = nb;	
			ce = c;
		}
		public boolean act(Entity e) {
			ce.accept(e);
			count--;
			return count > 0;
		}
	}

	public class ListConsumerAction{
		private ArrayList<ConsumerAction> list;
		public ListConsumerAction() 
		{
			list = new ArrayList<>();
		}
		public void add(Consumer<Entity> ce,int nb) 
		{
			list.add(new ConsumerAction(nb,ce));
		}
		public void act(Entity e) {
			for(int i = 0; i < list.size(); i++)
			{
				if(!list.get(i).act(e))
					list.remove(i);
			}	
		}
	}




	ListConsumerAction ce = new ListConsumerAction();

	public Zombie(Coordonnees c,Direction d) {
		super("Zombie",c,d);
		this.speed = 0.03;
		this.acce = 0;
		this.baseSpeed = 0.03;
		this.maxSpeed = 0.03;
		
		ce.add(e -> {
			move();
		}, 3);
		
	}

	@Override
	public void update() {
		ce.act(this);

	}

	@Override
	public void active(Entity e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void incAnim() {

	}
}
