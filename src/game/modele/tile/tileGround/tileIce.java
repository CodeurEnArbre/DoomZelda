package game.modele.tile.tileGround;

import game.modele.entity.Entity;
import game.modele.utils.ActionConsumer.InfiniteActionConsumer;
import game.modele.utils.ActionConsumer.Function.FunctionMoveIce;

public class tileIce extends TileGround{

	public tileIce() {
		super(16);
	}

	@Override
	public void onEntityOver(Entity e) {
		e.slow = 0.4;
		e.addAction(new InfiniteActionConsumer(new FunctionMoveIce()));
	}

	@Override
	public void leaveEntity(Entity e) {
		e.delAction(new FunctionMoveIce());
		e.slow = 1;
	}
	
	@Override
	public int speedIndice() {
		return 100;
	}
	
}
