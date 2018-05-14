package game.modele.world;

import java.util.ArrayList;

import game.modele.entity.Entity;
import game.modele.tile.Tile;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WorldData {
	
	private StringProperty zoneName;
	private Tile tileGround[][];
	private Tile tiles[][];
	private Tile tilesTop[][];
	public ArrayList<Entity> entity;
	private int width,height;
	
	public WorldData(String zoneName, int width, int height, Tile ground[][], Tile tiles[][], Tile tilesTop[][], ArrayList<Entity> entity) {
		this.zoneName=new SimpleStringProperty(zoneName);
		this.width=width;
		this.height=height;
		this.tileGround=ground;
		this.tiles=tiles;
		this.tilesTop=tilesTop;
		this.entity=entity;
	}
	
	public void newWorld(String zoneName, int width, int height, Tile ground[][], Tile tiles[][], Tile tilesTop[][], ArrayList<Entity> entity) {
		this.width=width;
		this.height=height;
		this.tileGround=ground;
		this.tiles=tiles;
		this.tilesTop=tilesTop;
		this.entity=entity;
		this.zoneName.setValue(zoneName);
	}
	
	public String getName() {
		return this.zoneName.get();
	}
	
	public StringProperty getNameProperty() {
		return this.zoneName;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public Tile getTileTerrain(int x, int y) {
			return tileGround[x][y];
	}
	
	public Tile getTile(int x, int y) {
			return tiles[x][y];
	}
	
	public Tile getTileTop(int x, int y) {
			return tilesTop[x][y];
	}
	
	public ArrayList<Entity> getEntity(){
		return this.entity;
	}
	
	public Entity[] entityHere(double x,double y) {
		return this.entity.stream().filter(a -> a.coordonnes.isSame(x, y)).toArray(Entity[]::new);
	}
	
}
