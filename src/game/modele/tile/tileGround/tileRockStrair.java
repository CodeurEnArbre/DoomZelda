package game.modele.tile.tileGround;

public class tileRockStrair extends tileGround {

	public enum RockStair{
		RockStair_Top(9),
		RockStair_Bottom(10),
		RockStair_Left(11),
		RockStair_Right(12);
		
		
		private int id; 
		RockStair(int id) {
			this.id = id;
		}
		public int get() {
			return id;
		}
		
		
	}
	
	public tileRockStrair(RockStair r) {
		super(r.id);
	}

	@Override
	public void Action() {

	}
}
