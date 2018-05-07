package game.modele.tile;

import game.modele.entity.Entity;
import game.modele.utils.Coordonnees;

public class TileEntity extends Entity {
	private int id;
	
	public TileEntity(int id, Coordonnees coordoner) {
		super(coordoner);
		this.id=id;
	}
	
	public int getId() {
		return this.id;
	}
}
