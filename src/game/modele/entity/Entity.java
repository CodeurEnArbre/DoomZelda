package game.modele.entity;

import game.modele.utils.Coordonnees;

import javafx.scene.image.ImageView;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Entity {
	
	public Coordonnees coordonnes;
	public IntegerProperty etatDeplacement = new SimpleIntegerProperty(0);

	protected int hitBoxX, hitBoxY;
	protected ImageView imageView;

	public Entity(Coordonnees coordonnees) {
		this.coordonnes=coordonnees;
		this.imageView = new ImageView();
	}
	public void addX(double x)
	{
		if(this.setCoordoner(
				new Coordonnees(
						this.coordonnes.getX() + x,
						this.coordonnes.getY()
						))) {
			this.coordonnes.setX(this.coordonnes.getX() + x);
		}
	}
	public void addY(double y) {
		if(this.setCoordoner(
				new Coordonnees(
						this.coordonnes.getX(),
						this.coordonnes.getY() + y))) {
			this.coordonnes.setY(this.coordonnes.getY() + y);
		}
	}
	public abstract boolean setCoordoner(Coordonnees coordonnees);
	public void forceTp(Coordonnees coordonnees) {
		this.coordonnes.setX(coordonnees.getX());
		this.coordonnes.setY(coordonnees.getY());
	}
	public boolean isOnTileCoord(Coordonnees coordonnees) {
		return((int)this.coordonnes.getX()==(int)coordonnees.getX() && (int)this.coordonnes.getY()==(int)coordonnees.getY());
	}

	//ID
	
	public abstract int getId();
	
	//UpdateIA
	
	public abstract void update();
	
	//Active (Marche dessus)
	
	public abstract void active(Entity e);
	
	//deplacement
	public void incAnim() {
		this.etatDeplacement.set(
				this.etatDeplacement.get()
				+ (this.etatDeplacement.get() < 35 ? 1 : -35));
	}
	public void resetAnim() {
		this.etatDeplacement.set(0);
	}

}