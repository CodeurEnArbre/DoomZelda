package game.modele.entity;

import game.modele.utils.Coordonnees;
import game.vue.TexturesParametres;

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

	@Override
	public boolean setCoordoner(Coordonnees coordonnees) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
