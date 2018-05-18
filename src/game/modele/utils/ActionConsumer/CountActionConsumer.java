package game.modele.utils.ActionConsumer;
import game.modele.entity.Entity;

public class CountActionConsumer implements ConsumerAction {
	private int count;
	private FunctionBank ce;
	public CountActionConsumer(int nb,FunctionBank c) 
	{
		count = nb;	
		ce = c;
	}
	public FunctionBank getEnum() 
	{
		return ce;
	}
	public boolean act(Entity e) 
	{
		ce.element.accept(e);
		count--;
		return count > 0;
	}
}
