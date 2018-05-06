package game.controleur.world;

import java.util.ArrayList;

import game.controleur.tile.Tile;
import game.controleur.tile.TileEntity;

public class World {
	
	private String zoneName;
	private Tile ground[];
	private Tile tiles[];
	private Tile tilesTop[];
	private ArrayList<TileEntity> tileEntity;
	private int width,height;
	
	public World(String zoneName, int width, int height, Tile ground[], Tile tiles[], Tile tilesTop[], ArrayList<TileEntity> tileEntity) {
		this.zoneName=zoneName;
		this.width=width;
		this.height=height;
		this.ground=ground;
		this.tiles=tiles;
		this.tilesTop=tilesTop;
		this.tileEntity=tileEntity;
	}
	
	public String getName() {
		return this.zoneName;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public Tile getTileTerrain(int x, int y) {
		if(x+y*this.width+1<this.width*this.height) {
			return ground[x+y*this.width+1];
		}else
			return null;
	}
	
	public Tile getTile(int x, int y) {
		if(x+y*this.width+1<this.width*this.height) {
			return tiles[x+y*this.width+1];
		}else
			return null;
	}
	
	public Tile getTileTop(int x, int y) {
		if(x+y*this.width+1<this.width*this.height) {
			return tilesTop[x+y*this.width+1];
		}else
			return null;
	}
	
	public ArrayList<TileEntity> getTileEntity(){
		return this.tileEntity;
	}
	
}
