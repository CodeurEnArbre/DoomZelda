package game.modele.tile.tileGround;

public class tileRockStairs extends TileGround {

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
	public boolean solid() {
		return false;
	}	
}
