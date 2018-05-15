package game.modele.tile.tileGround;


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
		Rock_CornerTopRight(31),
		Rock_CornerTopLeft(32),
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
	public void Action() {
		System.out.println("ROCK");
	}
}
