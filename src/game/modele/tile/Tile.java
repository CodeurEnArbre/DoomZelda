package game.modele.tile;

import game.modele.entity.Entity;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Tile {

	public static final int Max_Light = 4;
	public static final int Min_Light = 0;
	
	public IntegerProperty light = new SimpleIntegerProperty(4);
	protected int id;

	public Tile(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public int getLight() {
		if(this.light.get() < Min_Light)
			return Min_Light;
		else if(this.light.get() > Max_Light)
			return Max_Light;
		else
			return this.light.get();
	}
	
	public void modifLight(int i) {
		light.add(i);
	}	
	
	//solid
	public boolean solid() {
		return true;
	}

	//quand on touche
	public void Action(Entity e){/*Nothing*/}
	public void distant(Entity e) {/*Nothing*/}
	
	//quand on marche dessus
	public void onEntityOver(Entity e) {/*Nothing*/}
	public void leaveEntity(Entity e) {/*Nothing*/}

	//passe en dessous
	public void EntityUnder(Entity e) {/*Nothing*/}
	public void EntityLeaveUnder(Entity e) {/*Nothing*/}
}
