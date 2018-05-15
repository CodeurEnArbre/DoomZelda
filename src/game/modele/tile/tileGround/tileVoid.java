package game.modele.tile.tileGround;

import game.modele.entity.Entity;

public class tileVoid  extends tileGround{

	public tileVoid() {
		super(0);
	}

	@Override
	public void Action(Entity e) {

	}
	
	@Override
	public boolean solid() {
		return false;
	}
}
