package game.modele.tile.tileGround;

import game.modele.entity.Entity;

public class tileRock extends tileGround{

	public enum Rock{
		Rock_BottomRight(6),
		Rock_Bottom(7),
		Rock_BottomLeft(8),
		Rock_Right(22),
		Rock_Left(24),
		Rock_TopRight(38),
		Rock_Top(39),
		Rock_TopLeft(40),
		Rock_CornerBottomRight(25),
		Rock_CornerBottomLeft(26),
		Rock_CornerTopRight(41),
		Rock_CornerTopLeft(42),
		Rock_Stone(13);
		
		private int id; 
		Rock(int id) {
			this.id = id;
		}
		public int get() {
			return id;
		}
		
		
	}
	
	
	public tileRock() {
		super(23);
	}
	
	public tileRock(Rock r) {
		super(r.get());
	}
	

	@Override
	public void Action(Entity e) {
		//TODO 
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
