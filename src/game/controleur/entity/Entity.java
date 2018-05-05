package game.controleur.entity;

import game.controleur.utils.Coordoner;

public class Entity {
	
	private Coordoner coordoner;
	
	public Entity(Coordoner coordoner) {
		this.coordoner=coordoner;
	}
	
	public Coordoner getCoordoner() {
		return this.coordoner;
	}
	
	public void setCoordoner(Coordoner coordoner) {
		this.coordoner=coordoner;
	}
	
}
