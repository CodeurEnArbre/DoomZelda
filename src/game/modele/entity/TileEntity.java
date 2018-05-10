package game.modele.entity;

import game.modele.utils.Coordonnees;

public class TileEntity extends Entity {
	private int id;
	private boolean etat;
	
	public TileEntity(int id, Coordonnees coordoner, boolean etat) {
		super(coordoner);
		this.id=id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public boolean getEtat() {
		return this.etat;
	}
	
	public void setEtat(boolean etat) {
		this.etat=etat;
	}
}
