package game.modele.tile.tileGround;

import game.modele.entity.Entity;

public class tileRockStairs extends tileGround {

	public enum RockStairs{
		RockStair_Top(9),
		RockStair_Bottom(10),
		RockStair_Left(11),
		RockStair_Right(12);
		
		
		private int id; 
		RockStairs(int id) {
			this.id = id;
		}
		public int get() {
			return id;
		}
		
		
	}
	
	public tileRockStairs(RockStairs r) {
		super(r.id);
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
