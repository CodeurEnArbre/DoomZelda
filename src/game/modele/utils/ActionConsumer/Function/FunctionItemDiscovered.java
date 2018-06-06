package game.modele.utils.ActionConsumer.Function;

import game.modele.entity.Entity;
import game.modele.entity.tileEntity.Chest;

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
	}
}
