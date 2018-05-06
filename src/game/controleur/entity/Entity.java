package game.controleur.entity;

import game.controleur.utils.Coordoner;
import game.controleur.world.WorldLoader;

public class Entity {
	
	private Coordoner coordoner;
	
	public Entity(Coordoner coordoner) {
		this.coordoner=coordoner;
	}
	
	public Coordoner getCoordoner() {
		return this.coordoner;
	}
	
	public void setCoordoner(Coordoner coordoner) {
		int tileId = WorldLoader.currentMap.getTile( (int)coordoner.getY(), (int)coordoner.getX() ).getId() ;
		System.out.println("id:"+tileId);
		if(tileId <= 1 && coordoner.getX()>=0 && coordoner.getY()>=0)
			this.coordoner=coordoner;
	}
}
