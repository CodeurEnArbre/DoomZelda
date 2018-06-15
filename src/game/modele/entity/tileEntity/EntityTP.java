package game.modele.entity.tileEntity;

import game.modele.entity.Entity;
import game.modele.utils.Coordonnees;
import game.modele.world.Save;
import game.modele.world.World;

public class EntityTP extends TileEntity{
	private Coordonnees tpCoordonnees;
	private String mapNameTp;

	public EntityTP(Coordonnees coordonerPosition, boolean etat, String mapNameTp, Coordonnees tpCoordonnees) {
		super("EntityTP", coordonerPosition, etat);
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
		if(e.equals(World.player)) {
			Save.saveSave();
			World.loadWorld(this.mapNameTp,this.mapNameTp);
			World.player.forceTp(this.getTPCoordonnees());
		}
	}

	@Override
	public void incAnim() {
		// NONE
	}

	@Override
	public void onHit(Entity entity) {/*Nothing*/}
	
	
	
}
