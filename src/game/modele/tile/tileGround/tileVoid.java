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

	@Override
	public void onEntityOver(Entity e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void leaveEntity(Entity e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
