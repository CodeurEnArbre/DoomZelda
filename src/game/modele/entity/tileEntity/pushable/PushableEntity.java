package game.modele.entity.tileEntity.pushable;

import game.modele.entity.Entity;
import game.modele.entity.tileEntity.TileEntity;
import game.modele.utils.Coordonnees;

public class PushableEntity extends TileEntity {

	public PushableEntity(String id, Coordonnees coordoner) {
		super(id,coordoner,false);
		super.isSolidEntity = true;
	}

	@Override
	public void onHit(Entity entity) {}

	@Override
	public void active(Entity e) {}

	@Override
	public void incAnim() {}

}
