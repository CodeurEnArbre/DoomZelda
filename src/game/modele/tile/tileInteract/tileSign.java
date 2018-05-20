package game.modele.tile.tileInteract;

public class tileSign extends TileInteract{

	public enum Sign{
		Sign_Direction(43);
	
		
		private int id; 
		Sign(int id) {
			this.id = id;
		}
		public int get() {
			return id;
		}
	}
	
	
	public tileSign() {
		super(44);
	}

	public tileSign(Sign s) {
		super(s.id);
	}
	
	
	@Override
	protected void Show() {
		
		
	}
}
