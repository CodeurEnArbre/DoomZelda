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

	public void setX(double x) {
		this.coordonnes.setX(x);
	}
	public double getX() {
		return this.coordonnes.getX();
	}

	public void addX(double x)
	{
		if(this.setCoordoner(
				new Coordonnees(
						this.getX() + x,
						this.getY()
						))) {
			this.coordonnes.setX(this.getX() + x);
		}
	}

	public void setY(double y) {
		this.coordonnes.setY(y);
	}
	public double getY() {
		return this.getCoordoner().getY();
	}

	public void addY(double y) {
		if(this.setCoordoner(
				new Coordonnees(
						this.getX(),
						this.getY() + y))) {
			this.coordonnes.setY(this.getY() + y);
		}
	}

	public boolean setCoordoner(Coordonnees coordonnees) {
		int tileId = WorldLoader.currentMap.getTile((int)coordonnees.getY(), (int)coordonnees.getX()).getId() ;
		return (tileId <= 1 && coordonnees.getX() >= 0 && coordonnees.getY() >= 0);
	}
}
