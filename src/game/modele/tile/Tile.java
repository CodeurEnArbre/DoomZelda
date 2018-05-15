package game.modele.tile;

import game.modele.entity.Entity;

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
	
	//quand on regarde de pres
	public abstract void Action(Entity e);
	
	//quand on marche dessus
	public abstract void onEntityOver(Entity e);
	
	
	
	
	
}
