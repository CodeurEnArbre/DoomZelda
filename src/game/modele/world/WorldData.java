package game.modele.world;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.sun.java.swing.plaf.windows.resources.windows;

import game.modele.entity.Entity;
import game.modele.entity.tileEntity.TileEntity;
import game.modele.tile.Tile;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WorldData {

	private StringProperty zoneName=new SimpleStringProperty("null");
	private Tile[][] tileGround;
	private Tile[][] tiles;
	private Tile[][] tilesTop;
	private IntegerProperty[][] luminosity;


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
		this.luminosity = new SimpleIntegerProperty[width][height];
		for(int x = 0 ; x < width ; x++)
			for(int y = 0; y < height ; y++)
				luminosity[x][y] = new SimpleIntegerProperty(16);

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
		this.luminosity = new SimpleIntegerProperty[width][height];
		for(int x = 0 ; x < width ; x++)
			for(int y = 0; y < height ; y++) {
				luminosity[x][y] = new SimpleIntegerProperty(16);
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

	public IntegerProperty getShadow(int x,int y) {
		return luminosity[x][y];
	}

	public ObservableList<Entity> getEntity(){
		return this.entity;
	}

	public Entity[] entityHere(double x,double y) {
		return this.entity.stream().filter(a -> a.coordonnes.isSameTile(x, y)).toArray(Entity[]::new);
	}

	public void AddTorch(int x,int y,int i,int pas) {
		ArrayList<IntegerProperty> tmp = new ArrayList<>();
		AddLight(tmp, x, y, i,pas);
	}

	public void AddLight(ArrayList<IntegerProperty> tmp,int x,int y,int i,int pas) {
		if(i <= 0) return;

		if(tmp.contains(luminosity[x][y])) return;

		System.out.println(x + ":" + y + "  : " + i);

		tmp.add(luminosity[x][y]);
		luminosity[x][y].set(i);

		if(x + 1 > World.currentMap.getHeight()) return;

		AddLight(tmp,x + 1, y, i-pas,pas);
		if(x - 1 < 0) return;
		AddLight(tmp,x - 1, y, i-pas,pas);
		if(y + 1 > World.currentMap.getWidth())return;
		AddLight(tmp,x , y  +1, i-pas,pas);
		if(y - 1 < 0) return;
		AddLight(tmp,x, y - 1, i-pas,pas);

	}




	public void DelLight(int x,int y,int i) {

	}
}
