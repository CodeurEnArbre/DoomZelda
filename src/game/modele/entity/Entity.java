package game.modele.entity;

import game.modele.tile.Tile;
import game.modele.tile.tileGround.tileVoid;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import game.modele.world.World;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Entity {

	public static int key = 0;
	public int primaryKey;
	
	protected double baseSpeed = 0.11f;
	protected double maxSpeed = 0.16f;
	protected double acce = 0.00025f;
	
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
	
	protected String id;
	public Direction direction;
	
	protected Tile currentTile = new tileVoid();
	protected Tile currentTerrain = new tileVoid();
	protected Tile currentTop = new tileVoid();
	protected Entity currentE = null;
	
	public Coordonnees coordonnes;
	public IntegerProperty etatDeplacement = new SimpleIntegerProperty(0);

	protected double speed;
	public double slow = 1;


	public Entity(String id,Coordonnees coordonnees,Direction direction) {
		primaryKey = key++; 
		this.direction=direction;
		this.id = id;
		this.coordonnes=coordonnees;
		//deplacement
		moveUP = new infoDeplacement();
		moveDown = new infoDeplacement();
		moveLeft = new infoDeplacement();
		moveRight = new infoDeplacement();		
	}
	
	public void addX(double x){
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
	
	//Teleporte l'entity meme si il y a une tile qui bloque
	public void forceTp(Coordonnees coordonnees) {
		this.coordonnes.setX(coordonnees.getX());
		this.coordonnes.setY(coordonnees.getY());
	}
	
	public boolean isOnTileCoord(Coordonnees coordonnees) {
		return((int)this.coordonnes.getX()==(int)coordonnees.getX() && (int)this.coordonnes.getY()==(int)coordonnees.getY());
	}
	
	public boolean setCoordoner(Coordonnees coordonnees) {
		for(Entity e : World.currentMap.entityHere(this.coordonnes.getX(), this.coordonnes.getY())){
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
		}catch(ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
	
	public void move() {
		if(!moveDown.active && !moveUP.active && !moveLeft.active && !moveRight.active) {
			resetAnim();
			speed = baseSpeed;
		}
		if(this.moveDown.active) {
			if(this.moveLeft.active ^ this.moveRight.active) {
				this.addY(this.speed * 2/3 * this.slow);
			}
			else {
				if(this.speed < this.maxSpeed) {
					this.speed += this.acce;
				}
				this.addY(this.speed * this.slow);
				this.incAnim();
			}
		}
		if(this.moveUP.active) {
			if(this.moveLeft.active ^ this.moveRight.active)
			{	
				this.addY(-this.speed * 2/3 * this.slow);	
			}else
			{
				if(this.speed < this.maxSpeed) {
					this.speed += this.acce;
				}
				this.addY(-this.speed * this.slow);	
				incAnim();
			}
		}
		if(this.moveLeft.active) {
			if(this.moveUP.active ^ this.moveDown.active)
			{
				this.addX(-this.speed * 2/3 * this.slow);
				this.incAnim();
			}		else
			{	
				if(this.speed < this.maxSpeed) {
					this.speed += this.acce;
				}
				this.addX(-speed * this.slow);
				this.incAnim();
			}
		}
		if(this.moveRight.active) {
			if(this.moveUP.active ^ this.moveDown.active)
			{
				this.addX(speed * 2/3 * slow);
				this.incAnim();
			}else
			{
				if(this.speed < this.maxSpeed) {
					this.speed += this.acce;
				}
				this.addX(this.speed * this.slow); 
				this.incAnim();
			}
		}
	}
	
	//ID
	public String getId() {
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