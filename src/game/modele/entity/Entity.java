package game.modele.entity;

import game.modele.utils.Coordonnees;
import game.modele.world.WorldLoader;

public class Entity {
	
	private Coordonnees coordonnes;
	
	public Entity(Coordonnees coordonnees) {
		this.coordonnes=coordonnees;
	}
	
	public Coordonnees getCoordoner() {
		return this.coordonnes;
	}

	public void setCoordoner(Coordonnees coordonnees) {
		int tileId = WorldLoader.currentMap.getTile((int)coordonnees.getX(), (int)coordonnees.getY()).getId() ;
		System.out.println("id:"+tileId);
		if(tileId <= 1 && coordonnees.getX() >= 0 && coordonnees.getY() >= 0)
			this.coordonnes=coordonnees;
	}
}
