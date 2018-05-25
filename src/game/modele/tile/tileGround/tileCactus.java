package game.modele.tile.tileGround;

import game.modele.entity.Entity;
import game.modele.entity.living.EntityLiving;

public class tileCactus extends TileGround{

	public tileCactus() {
		super(27);
	}
	
	@Override
	public void Action(Entity e) {
		if(e instanceof EntityLiving) {
			((EntityLiving) e).perdrePV(1);
			System.out.println(((EntityLiving) e).getPV().doubleValue());	
		}
	}
}
