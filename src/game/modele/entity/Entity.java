package game.modele.entity;

import game.modele.utils.Coordonnees;
import game.modele.world.WorldLoader;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Entity {

	private Coordonnees coordonnes;
	protected IntegerProperty animationIndice = new SimpleIntegerProperty(0);
	
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
		return (tileId <= 1 &&
				coordonnees.getX() >= 0 &&
				coordonnees.getY() >= 0 &&
				(coordonnees.getX() + WorldLoader.player.speed)< WorldLoader.currentMap.getWidth()&&
				(coordonnees.getY() + WorldLoader.player.speed) < WorldLoader.currentMap.getHeight());
	}
	
	//animation
		public void incAnim() {
			this.animationIndice.set(
					this.animationIndice.get()
					+ (this.animationIndice.get() < 35 ? 1 : -35));
		}
		
		public IntegerProperty getAnimationProperty() {
			return this.animationIndice;
		}
		
		
		public void resetAnim() {
			this.animationIndice.set(0);
		}
}