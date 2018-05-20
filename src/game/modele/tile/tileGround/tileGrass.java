package game.modele.tile.tileGround;

public class tileGrass extends TileGround{

	public enum Grass{
		Grass_BottomRight(3),
		Grass_Bottom(4),
		Grass_BottomLeft(5),
		Grass_Right(19),
		Grass_Left(21),
		Grass_TopRight(35),
		Grass_Top(36),
		Grass_TopLeft(37);
		
		private int id; 
		Grass(int id) {
			this.id = id;
		}
		public int get() {
			return id;
		}
	}
	
	
	public tileGrass() {
		super(2);
	}
	
	public tileGrass(Grass g) {
		super(g.get());
	}
}
