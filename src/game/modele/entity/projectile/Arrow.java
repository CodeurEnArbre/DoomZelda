package game.modele.entity.projectile;

import game.modele.entity.Entity;
import game.modele.entity.living.EntityLiving;
import game.modele.entity.tileEntity.TileEntity;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;

public class Arrow extends Projectile{

	public Arrow(Coordonnees coordonnees, Direction direction, Entity shooter) {
		super("Arrow", coordonnees, direction, shooter, 1);
		super.speed = 0.6;
		super.maxSpeed = 0.6;
	}

	@Override
	public void active(Entity e) {
		if(e instanceof EntityLiving && e != shooter) {
			EntityLiving entityLiving = (EntityLiving)e;
			entityLiving.perdrePV(damage);
			this.delete();
		}else if(e instanceof TileEntity && e.isSolidEntity){
			((TileEntity) e).onHit(shooter);
			this.delete();
		}
	}

	@Override
	public void incAnim() {}

}
