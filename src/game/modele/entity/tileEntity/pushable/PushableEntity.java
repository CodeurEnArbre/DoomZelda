package game.modele.entity.tileEntity.pushable;

import game.modele.entity.Entity;
import game.modele.entity.tileEntity.TileEntity;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;

public class PushableEntity extends TileEntity {

	public PushableEntity(String id, Coordonnees coordoner, Direction direction) {
		super(id,coordoner,direction,false);
	}

	@Override
	public void onHit(Entity entity) {}

	@Override
	public void active(Entity e) {
	}

	@Override
	public void incAnim() {}

}
