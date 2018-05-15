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
	public void Action(Entity e) {
		//nothing
	}
	
	//quand on marche dessus
	public void onEntityOver(Entity e) {
		//nothing
	}
	
	public void leaveEntity(Entity e) {
		//Nothing
	}
	
	
	
}
