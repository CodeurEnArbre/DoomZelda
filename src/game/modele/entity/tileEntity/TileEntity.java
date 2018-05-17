package game.modele.entity.tileEntity;

import game.modele.entity.Entity;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;

public abstract class TileEntity extends Entity {
	private boolean etat;
	
	public TileEntity(String id, Coordonnees coordoner,Direction direction, boolean etat) {
		super(id,coordoner,direction);
	}	
	public boolean getEtat() {
		return this.etat;
	}
	
	public void setEtat(boolean etat) {
		this.etat=etat;
	}
}
