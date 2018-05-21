package game.modele.world;

import java.util.ArrayList;

import game.modele.entity.Entity;
import game.modele.entity.tileEntity.TileEntity;
import game.modele.tile.Tile;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WorldData {
	
	private StringProperty zoneName=new SimpleStringProperty("null");
	private Tile tileGround[][];
	private Tile tiles[][];
	private Tile tilesTop[][];
	public ObservableList<Entity> entity;
	private int width,height;
	private boolean outSide;
	
	public WorldData(String zoneName, int width, int height, boolean outside, Tile ground[][], Tile tiles[][], Tile tilesTop[][], ArrayList<Entity> entitys) {
		this.zoneName.setValue(zoneName);
		this.width=width;
		this.height=height;
		this.outSide=outside;
		this.tileGround=ground;
		this.tiles=tiles;
		this.tilesTop=tilesTop;
		this.entity= FXCollections.observableArrayList();
		for(Entity e : entitys) {
			this.entity.add(e);
			World.addKeyGameLoop(y -> e.update());
		}
	
	}
	
	public void newWorld(String zoneName, int width, int height, boolean outside, Tile ground[][], Tile tiles[][], Tile tilesTop[][], ArrayList<Entity> entitys) {
		Entity.key=0;
		this.width=width;
		this.height=height;
		this.outSide=outside;
		this.tileGround=ground;
		this.tiles=tiles;
		this.tilesTop=tilesTop;
		this.entity.clear();
		for(Entity e : entitys) {
			this.entity.add(e);
			World.addKeyGameLoop(y -> e.update());
		}
		
		this.zoneName.setValue(zoneName);
	}
	
	public String getName() {
		return this.zoneName.get();
	}
	
	public boolean isOutside() {
		return this.outSide;
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
	
	public ObservableList<Entity> getEntity(){
		return this.entity;
	}
	
	public Entity[] entityHere(double x,double y) {
		return this.entity.stream().filter(a -> a.coordonnes.isSameTile(x, y)).toArray(Entity[]::new);
	}
	
}
