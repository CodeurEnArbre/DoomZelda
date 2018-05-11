package game.modele.entity;

import game.modele.utils.Coordonnees;

import javafx.scene.image.ImageView;
import game.modele.world.WorldLoader;
import game.vue.TexturesParametres;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Entity {

	private Coordonnees coordonnes;
	protected IntegerProperty animationIndice = new SimpleIntegerProperty(0);
	
	protected int hitBoxX, hitBoxY;
	protected TexturesParametres textureParametres;
	protected ImageView imageView;
	
	public Entity(Coordonnees coordonnees) {
		this.coordonnes=coordonnees;
		this.imageView = new ImageView();
		//textureParametres = new TexturesParametres("player", 24, 32, 0, 2); test à supprimer
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
	
	public void forceTp(Coordonnees coordonnees) {
		this.coordonnes.setX(coordonnees.getX());
		this.coordonnes.setY(coordonnees.getY());
	}
	
	public boolean isOnTileCoord(Coordonnees coordonnees) {
		if((int)this.coordonnes.getX()==(int)coordonnees.getX() && (int)this.coordonnes.getY()==(int)coordonnees.getY())
			return true;
		else
			return false;
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
		
		public BooleanProperty touche(Entity e) {
			BooleanProperty result = new SimpleBooleanProperty();
			if(this.getX() - e.getX() <= this.hitBoxX && this.getY() - e.getY() <= this.hitBoxY)
				result.setValue(true);
			else
				result.setValue(false);
			
			return result;
		}
		
		public ImageView getimageView() {
			return this.imageView;
		}
		
		public TexturesParametres getTexturesParametres() {
			return this.textureParametres;
		}
		
		public String getTextureFile() {
			return this.getTexturesParametres().getTextureFile();
		}
		
		public int getTextureWidth() {
			return this.getTexturesParametres().getWidth();
		}
		
		public int getTextureHeight() {
			return this.getTexturesParametres().getheight();
		}
		
		public int getTextureX() {
			return this.getTexturesParametres().getX();
		}
		
		public int getTextureY() {
			return this.getTexturesParametres().getY();
		}
}