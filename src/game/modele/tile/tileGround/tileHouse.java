package game.modele.tile.tileGround;


public class tileHouse extends TileGround{

	public enum House{
		House_TopLeft(58),
		House_TopCenter1(59),
		House_TopCenter2(60),
		House_TopRight(61),
		House_CenterLeft(74),
		House_CenterDoor(75),
		House_Center(76),
		House_CenterRight(77),
		House_BotLeft(90),
		House_Door(91),
		House_Bot(92),
		House_BotRight(93);
	
		private int id; 
		House(int id) {
			this.id = id;
		}
		public int get() {
			return id;
		}
	}
	
	public tileHouse() {
		super(93);
	}

	public tileHouse(House h) {
		super(h.id);
	}
	
	@Override
	public boolean solid() {
		return super.id==91?false:true;
	}
}
