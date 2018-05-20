package game.modele.tile.tileGround;


public class tileLadder extends TileGround{

	public enum Ladder{
		Ladder_Top(107),
		Ladder_Bottom(139);
		
		private int id; 
		Ladder(int id) {
			this.id = id;
		}
		public int get() {
			return id;
		}
		
	}
	
	public tileLadder() {
		super(123);
	}
	
	public tileLadder(Ladder l) {
		super(l.id);
	}
}
