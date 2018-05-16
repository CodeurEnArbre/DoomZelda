package game.modele.entity;

import game.modele.tile.Tile;
import game.modele.tile.tileGround.tileVoid;
import game.modele.utils.Coordonnees;
import game.modele.world.World;
import javafx.scene.image.ImageView;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Entity {

	protected Tile currentTile = new tileVoid();
	protected Tile currentTerrain = new tileVoid();
	protected Tile currentTop = new tileVoid();
	
	public Coordonnees coordonnes;
	public IntegerProperty etatDeplacement = new SimpleIntegerProperty(0);

	protected ImageView imageView;

	protected double speed;
	public double slow;


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
	public void forceTp(Coordonnees coordonnees) {
		this.coordonnes.setX(coordonnees.getX());
		this.coordonnes.setY(coordonnees.getY());
	}
	public boolean isOnTileCoord(Coordonnees coordonnees) {
		return((int)this.coordonnes.getX()==(int)coordonnees.getX() && (int)this.coordonnes.getY()==(int)coordonnees.getY());
	}
	public boolean setCoordoner(Coordonnees coordonnees) {
		for(Entity e :World.currentMap.entityHere(this.coordonnes.getX(), this.coordonnes.getY())){
			e.active(this);
		}

		try {
			Tile tile = World.currentMap.getTileTerrain((int)coordonnees.getY(), (int)coordonnees.getX());
			Tile terrain = World.currentMap.getTile((int)coordonnees.getY(), (int)coordonnees.getX());
			Tile top =  World.currentMap.getTileTop((int)coordonnees.getY(), (int)coordonnees.getX());
			
			if(currentTerrain != terrain) {
				currentTerrain.distant(this);
				terrain.Action(this);
				currentTerrain = terrain;
			}
			
			if(currentTop != top) {
				currentTop.EntityLeaveUnder(this);
				top.EntityUnder(this);
				currentTop = top;
			}
			
			
			if(!World.currentMap.getTile((int)coordonnees.getY(), (int)coordonnees.getX()).solid() &&
					coordonnees.getX() >= 0 && coordonnees.getY() >= 0 &&
					(coordonnees.getX() + speed)< World.currentMap.getWidth()&&
					(coordonnees.getY() + speed) < World.currentMap.getHeight())
			{
				if(this.currentTile != tile) {
					currentTile.leaveEntity(this);
					tile.onEntityOver(this);
					currentTile = tile;

				}
				return true;
			}else 
				return false;
		}catch(ArrayIndexOutOfBoundsException e) 
		{
			return false;
		}
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
				+ (this.etatDeplacement.get() < 83 ? 1 : -83));
	}
	public void resetAnim() {
		this.etatDeplacement.set(0);
	}

}