package game.modele.entity;

import game.modele.entity.living.monster.EntityMonster;
import game.modele.tile.Tile;
import game.modele.tile.tileGround.tileVoid;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import game.modele.utils.ActionConsumer.ConsumerAction;
import game.modele.utils.ActionConsumer.ListConsumerAction;
import game.modele.world.World;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Entity {

	ListConsumerAction ActionQueueEntity = new ListConsumerAction();
	public static int key=0;
	public int primaryKey;

	public float baseSpeed = 0.11f;
	public float maxSpeed = 0.16f;
	public float acce = 0.00025f;
	public float slow = 1f;
	public float speed;

	public float hitBoxX=0;//W.I.P16
	public float hitBoxY=0;//W.I.P

	public boolean isSolidEntity = false;
	//represente l'etat d'une direction
	public class infoDeplacement{
		//la touche est enfonce
		public boolean active = false;
		//la touche est enfonce mais une autre prend le dessus
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

	public Entity(String id,Coordonnees coordonnees) {
		primaryKey = key++; 
		this.id = id;
		this.coordonnes=coordonnees;
		//deplacement
		moveUP = new infoDeplacement();
		moveDown = new infoDeplacement();
		moveLeft = new infoDeplacement();
		moveRight = new infoDeplacement();	
	}
	
	public Entity(String id,Coordonnees coordonnees,Direction direction) {
		this(id,coordonnees);
		this.direction=direction;
	}

	public void addAction(ConsumerAction c) {
		ActionQueueEntity.add(c);
	}

	public void delAction(ConsumerAction c) {
		ActionQueueEntity.del(c,this);
	}

	public void clearAction() {
		ActionQueueEntity.clear();
	}
	public void addX(float x){
		if(this.canSetCoordinateHere( new Coordonnees(this.coordonnes.getX() + x,this.coordonnes.getY() )))
			this.coordonnes.setX(this.coordonnes.getX() + x);
	}

	public void addY(float y) {
		if(this.canSetCoordinateHere( new Coordonnees(this.coordonnes.getX(), this.coordonnes.getY() + y)))
			this.coordonnes.setY(this.coordonnes.getY() + y);
	}
	
	public void forceAddX(float x){
			this.coordonnes.setX(this.coordonnes.getX() + x);
	}

	public void forceAddY(float y) {
			this.coordonnes.setY(this.coordonnes.getY() + y);
	}
	
	public void setCoordinate(Coordonnees coordonnees) {
		if(canSetCoordinateHere(coordonnees))
			this.coordonnes.setCoordoner(coordonnees.getX(), coordonnees.getY());
	}

	//Teleporte l'entity meme si il y a une tile qui bloque
	public void forceTp(Coordonnees coordonnees) {
		this.coordonnes.setX(coordonnees.getX());
		this.coordonnes.setY(coordonnees.getY());
	}

	public boolean isOnTileCoord(Coordonnees coordonnees) {
		return((int)this.coordonnes.getX()==(int)coordonnees.getX() && (int)this.coordonnes.getY()==(int)coordonnees.getY());
	}

	public boolean canSetCoordinateHere(Coordonnees coordonnees) {
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

			Entity entity = World.currentMap.getEntity((int)coordonnees.getX(), (int)coordonnees.getY());

			if(entity!=null && entity.isSolidEntity && entity != this)
				return false;

			if(!World.currentMap.getTile((int)coordonnees.getY(), (int)coordonnees.getX()).solid() &&
					!World.currentMap.getTile((int)(coordonnees.getY()+ hitBoxY), (int)(coordonnees.getX()+ hitBoxX)).solid() &&
					coordonnees.getX() >= 0 && coordonnees.getY() >= 0 &&
					(coordonnees.getX() + speed + hitBoxX) < World.currentMap.getWidth() &&
					(coordonnees.getY() + speed + hitBoxY) < World.currentMap.getHeight())
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

	public String getId() {
		return this.id;
	}
	public final void update() {
		ActionQueueEntity.act(this);

		for(Entity e : World.currentMap.entityHere(this.coordonnes.getX(), this.coordonnes.getY())){

			if(e != this && e instanceof EntityMonster && this.id.equals("Player")) {
				this.active(e);
			}
		}

	}
	public abstract void active(Entity e);
	public abstract void incAnim();
	public void resetAnim() {
		this.etatDeplacement.set(0);
	}

	public String toString() {
		return id;
	}

	public void interact() {

	}

	public void dispose() { 
		ActionQueueEntity.dispose();

	}
	
	public void delete() {
		World.currentMap.deleteEntity(primaryKey);
		System.out.println(this+" "+primaryKey);
	}
}