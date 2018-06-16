package game.modele.tile.tileGround;

import java.util.HashMap;
import java.util.Map;

import game.modele.entity.Entity;
import game.modele.utils.ActionConsumer.ConsumerAction;
import game.modele.utils.ActionConsumer.InfiniteActionConsumer;
import game.modele.utils.ActionConsumer.Function.FunctionMoveIce;

public class tileIce extends TileGround{
	Map<Entity,ConsumerAction> actionCreer = new HashMap<>();
	public tileIce() {
		super(16);
	}
	@Override
	public void onEntityOver(Entity e) {
		e.slow = 0.4f;
		if(!actionCreer.containsKey(e)) {
			actionCreer.put(e, new InfiniteActionConsumer(new FunctionMoveIce()));
			e.addAction(actionCreer.get(e));
		}
	}
	@Override
	public void leaveEntity(Entity e) {
		e.delAction(actionCreer.get(e));
		actionCreer.remove(e);		
		e.slow = 1;
	}
	@Override
	public int speedIndice() {
		return 100;
	}
}
