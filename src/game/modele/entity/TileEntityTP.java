package game.modele.entity;

import game.modele.entity.tileEntity.TileEntity;
import game.modele.utils.Coordonnees;
import game.modele.world.World;

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
		return false;
	}

	@Override
	public void update() {
		
	}

	@Override
	public void active(Entity e) {
		World.loadWorld(this.mapNameTp,this.mapNameTp);
		World.player.forceTp(this.getTPCoordonnees());
	}
}
