package game.modele.entity;

import game.modele.utils.Coordonnees;

public class TileEntityTP extends TileEntity{
	private Coordonnees tpCoordonnees;
	private String mapNameTp;

	public TileEntityTP(int id, Coordonnees coordonerPosition, boolean etat, String mapNameTp, Coordonnees tpCoordonnees) {
		super(id, coordonerPosition, etat);
		this.tpCoordonnees=tpCoordonnees;
		this.mapNameTp=mapNameTp;
	}
	
	public String getTPmapName() {
		return this.mapNameTp;
	}
	
	public Coordonnees getTPCoordonnees() {
		return this.tpCoordonnees;
	}

}
