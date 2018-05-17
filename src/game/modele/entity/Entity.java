package game.modele.entity;

import game.modele.tile.Tile;
import game.modele.tile.tileGround.tileVoid;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import game.modele.world.World;
import javafx.scene.image.ImageView;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Entity {

	private static int key = 0;
	public int primaryKey; 
	
	//repr�sente l'�tat d'une direction
	public class infoDeplacement{
		//la touche est enfonc�
		public boolean active = false;
		//la touche est enfonc� mais une autre prend le dessus
		public boolean attente = false;
	}
	public infoDeplacement moveUP;
	public infoDeplacement moveDown;
	public infoDeplacement moveRight;
	public infoDeplacement moveLeft;
	
	protected int id;
	public Direction direction;
	
	protected Tile currentTile = new tileVoid();
	protected Tile currentTerrain = new tileVoid();
	protected Tile currentTop = new tileVoid();
	protected Entity currentE = null;
	
	public Coordonnees coordonnes;
	public IntegerProperty etatDeplacement = new SimpleIntegerProperty(0);

	protected ImageView imageView;

	protected double speed;
	public double slow;


	public Entity(int id,Coordonnees coordonnees,Direction direction) {
		primaryKey = key++; 
		this.direction=direction;
		this.id = id;
		this.coordonnes=coordonnees;
		this.imageView = new ImageView();
		//d�placement
		moveUP = new infoDeplacement();
		moveDown = new infoDeplacement();
		moveLeft = new infoDeplacement();
		moveRight = new infoDeplacement();		
	}
	public void addX(double x)
	{
		if(this.setCoordoner(// TODO Auto-generated method stub
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
			if(e != this && currentE != e) {
				currentE = e;
				e.active(this);
			}else if(currentE == null){
				currentE = e;
			}
		}
		
		try {
			Tile tile = World.currentMap.getTileTerrain((int)coordonnees.getY(), (int)coordonnees.getX());
			Tile terrain = World.currentMap.getTile((int)coordonnees.getY(), (int)coordonnees.getX());
			Tile top =  World.currentMap.getTileTop((int)coordonnees.getY(), (int)coordonnees.getX());
			
			if(currentTerrain != terrain) {
				if(currentTerrain != null)
					currentTerrain.distant(this);
				terrain.Action(this);
				currentTerrain = terrain;
			}
			
			if(currentTop != top) {
				if(currentTop != null)
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
					if(currentTile != null)
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

	public int getId() {
		return this.id;
	}

	//UpdateIA

	public abstract void update();

	//Active (Marche dessus)

	public abstract void active(Entity e);

	//deplacement
	public abstract void incAnim();
	public void resetAnim() {
		this.etatDeplacement.set(0);
	}

}