package game.modele.tile.tileGround;

public class tileFloor extends TileGround{
	
	public enum Floor{
		Floor_other(109);
		
		private int id; 
		Floor(int id) {
			this.id = id;
		}
		public int get() {
			return id;
		}		
	}
	
	
	
	public tileFloor() {
		super(108);
	}
	
	public tileFloor(Floor f) {
		super(f.id);
	}
}
