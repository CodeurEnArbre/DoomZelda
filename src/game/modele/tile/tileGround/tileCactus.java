package game.modele.tile.tileGround;

import game.modele.entity.Entity;
import game.modele.entity.living.EntityLiving;

public class tileCactus extends tileGround{

	public tileCactus() {
		super(27);
	}
	
	@Override
	public void Action(Entity e) {
		if(e instanceof EntityLiving) {
			((EntityLiving) e).perdrePV();
			System.out.println(((EntityLiving) e).getPV());	
		}
	}
}
