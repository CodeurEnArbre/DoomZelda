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

	//quand on touche
	public void Action(Entity e) {
		//nothing
	}
	
	public void distant(Entity e) {
		//Nothing
	}
	
	//quand on marche dessus
	public void onEntityOver(Entity e) {
		//nothing
	}

	public void leaveEntity(Entity e) {
		//Nothing
	}

	//passe en dessous
	public void EntityUnder(Entity e) {
		//Nothing
	}

	public void EntityLeaveUnder(Entity e) {
		//Nothing
	}
}
