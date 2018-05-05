package game.controleur.tile;

import game.controleur.entity.Entity;
import game.controleur.utils.Coordoner;

public class TileEntity extends Entity {
	private int id;
	
	public TileEntity(int id, Coordoner coordoner) {
		super(coordoner);
		this.id=id;
	}
	
	public int getId() {
		return this.id;
	}
}
