package game.modele.tile.tileGround;

import game.modele.entity.Entity;
import game.modele.utils.ActionConsumer.FunctionBank;
import game.modele.utils.ActionConsumer.InfiniteActionConsumer;

public class tileIce extends TileGround{

	public tileIce() {
		super(16);
	}

	@Override
	public void onEntityOver(Entity e) {
		e.slow = 0.5;
		e.addAction(new InfiniteActionConsumer(FunctionBank.IceMove));
	}

	@Override
	public void leaveEntity(Entity e) {
		e.delAction(FunctionBank.IceMove);
		e.delAction(FunctionBank.MoveIceDown);
		e.delAction(FunctionBank.MoveIceDownAND);
		e.delAction(FunctionBank.MoveIceUp);
		e.delAction(FunctionBank.MoveIceUpAND);
		e.delAction(FunctionBank.MoveIceLeft);
		e.delAction(FunctionBank.MoveIceLeftAND);
		e.delAction(FunctionBank.MoveIceRight);
		e.delAction(FunctionBank.MoveIceRightAND);
		e.slow = 1;
	}

}
