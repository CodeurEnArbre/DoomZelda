package game.modele.entity.tileEntity;

import game.modele.entity.Entity;
import game.modele.utils.Coordonnees;
import game.modele.world.Save;
import game.modele.world.World;

public class EntityTP extends TileEntity{
	public Coordonnees tpCoordonnees;
	public String mapNameTp;

	public EntityTP(Coordonnees coordonerPosition, boolean etat, String mapNameTp, Coordonnees tpCoordonnees) {
		super("EntityTP", coordonerPosition, etat);
		this.tpCoordonnees=tpCoordonnees;
		this.mapNameTp=mapNameTp;
	}	

	@Override
	public void active(Entity e) {
		if(e.equals(World.player)) {
			Save.saveSave();
			World.loadWorld(this.mapNameTp,this.mapNameTp);
			World.player.forceTp(this.tpCoordonnees);
		}
	}

	@Override
	public void incAnim() {
		// NONE
	}

	@Override
	public void onHit(Entity entity) {/*Nothing*/}
	
	
	
}
