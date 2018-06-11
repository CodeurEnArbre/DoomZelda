package game.modele.entity.tileEntity.chest;

import game.modele.entity.Entity;
import game.modele.entity.tileEntity.TileEntity;
import game.modele.item.Item;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import game.modele.utils.ActionConsumer.CountActionConsumer;
import game.modele.utils.ActionConsumer.Function.FunctionCantMove;
import game.modele.utils.ActionConsumer.Function.FunctionItemDiscovered;
import game.modele.world.World;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Chest extends TileEntity{
	
	public Item itemInside;
	public IntegerProperty etatAnim;
	
	public Chest(String id, Coordonnees coordoner, Direction direction, Item insideItem) {
		super(id, coordoner, direction, true);
		this.itemInside = insideItem;
		super.isSolidEntity = true;
		etatAnim = new SimpleIntegerProperty(0);
	}

	@Override
	public void onHit(Entity entity) {}

	@Override
	public void active(Entity e) {}

	@Override
	public void incAnim() {}
	
	@Override
	public void interact() {
		if(etat.get()) {
			addAction(new CountActionConsumer(30,new FunctionItemDiscovered()));
			World.player.addAction(new CountActionConsumer(35,new FunctionCantMove()));
		}
	}
	
}
