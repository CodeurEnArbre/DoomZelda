package game.modele.tile;

import game.modele.entity.Entity;

public abstract class Tile {

	public static final int Max_Light = 4;
	public static final int Min_Light = 0;
	
	protected int id;

	public Tile(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	
	//Si la tile est solid
	public boolean solid() {
		return true;
	}
	
	public int speedIndice() {
		return 1;
	}
	
	//Quand on l'update
	public void onUpdate() {/*Nothing*/}

	//Quand on touche
	public void Action(Entity e){/*Nothing*/}
	public void distant(Entity e) {/*Nothing*/}
	
	//Quand on marche dessus
	public void onEntityOver(Entity e) {/*Nothing*/}
	public void leaveEntity(Entity e) {/*Nothing*/}

	//passe en dessous
	public void EntityUnder(Entity e) {/*Nothing*/}
	public void EntityLeaveUnder(Entity e) {/*Nothing*/}
	
}
