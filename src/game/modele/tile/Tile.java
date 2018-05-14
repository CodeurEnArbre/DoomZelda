package game.modele.tile;

public class Tile {
	
	private int id;
	
	public Tile(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}

	public boolean solid() {
		return false;
	}
}
