package game.modele.entity.tileEntity;

import game.modele.entity.Entity;
import game.modele.item.Item;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import game.modele.utils.ActionConsumer.CountActionConsumer;
//import game.modele.utils.ActionConsumer.Function.FunctionItemDiscovered;

public class Chest extends TileEntity{
	
	Item insideItem;

	public Chest(String id, Coordonnees coordoner, Direction direction, Item insideItem) {
		super(id, coordoner, direction, false);
		this.insideItem = insideItem;
		super.isSolidEntity = true;
	}

	@Override
	public void onHit(Entity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void active(Entity e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void incAnim() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void interact() {
	//	addAction(new CountActionConsumer(30,new FunctionItemDiscovered()));		
	}
	
}
