package game.modele.tile.tileGround;

public class tileDirt extends TileGround{

	public enum Dirt{
		Dirt_TopLeft(17),
		Dirt_TopRight(18),
		Dirt_BotLeft(33),
		Dirt_BotRight(34);
		
		private int id; 
		Dirt(int id) {
			this.id = id;
		}
		public int get() {
			return id;
		}
		
	}
	
	public tileDirt() {
		super(20);
	}

	public tileDirt(Dirt d)
	{
		super(d.get());
	}
}
