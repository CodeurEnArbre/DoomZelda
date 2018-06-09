package game.modele.utils.ActionConsumer.Function;

import game.modele.entity.Entity;
import game.modele.entity.tileEntity.chest.Chest;
import game.modele.world.World;

public class FunctionItemDiscovered extends Function{

	public FunctionItemDiscovered() {
		super();
	}
	
	
	
	@Override
	protected void Action(Entity e) {
		Chest c = (Chest)e;
		c.etatAnim.set(c.etatAnim.get()+1);
	}

	 @Override
	public void finishAction(Entity e) {
		Chest c = (Chest)e;
		c.setEtat(false);
		System.out.println(c.itemInside);
		c.itemInside = World.player.takeItem(c.itemInside);
		System.out.println(c.itemInside);
		if(c.itemInside != null) {
			System.out.println("refermage");
			c.etatAnim.set(0);
			c.setEtat(true);
		}
	}
}
