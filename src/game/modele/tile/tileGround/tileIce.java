package game.modele.tile.tileGround;

import game.modele.entity.Entity;

public class tileIce extends tileGround{

	public tileIce() {
		super(16);
	}

	@Override
	public void Action(Entity e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEntityOver(Entity e) {
		e.slow = 2;
	}

	@Override
	public void leaveEntity(Entity e) {
		e.slow = 1;		
	}

}
