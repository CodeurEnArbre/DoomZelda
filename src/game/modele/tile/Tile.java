package game.modele.tile;

public abstract class Tile {
	
	private int id;
	
	public Tile(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	//solid
	public boolean solid() {
		return true;
	}
	
	//quand on marche dessus
	public abstract void Action();
	
	
	
	
	
}
