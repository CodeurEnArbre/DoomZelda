package game.modele.entity;

import game.modele.entity.tileEntity.TileEntity;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import game.modele.world.World;

public class EntityTP extends TileEntity{
	private Coordonnees tpCoordonnees;
	private String mapNameTp;

	public EntityTP(Coordonnees coordonerPosition,Direction direction, boolean etat, String mapNameTp, Coordonnees tpCoordonnees) {
		super("EntityTP", coordonerPosition,direction, etat);
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
	public void active(Entity e) {
		World.loadWorld(this.mapNameTp,this.mapNameTp);
		World.player.forceTp(this.getTPCoordonnees());
		
	}

	@Override
	public void incAnim() {
		// NONE
	}
	
	
	
}
