package game.modele.world;

import java.util.ArrayList;

import game.modele.tile.Tile;
import game.modele.tile.TileEntity;

public class World {
	
	private String zoneName;
	private Tile tileGround[][];
	private Tile tiles[][];
	private Tile tilesTop[][];
	private ArrayList<TileEntity> tileEntity;
	private int width,height;
	
	public World(String zoneName, int width, int height, Tile ground[][], Tile tiles[][], Tile tilesTop[][], ArrayList<TileEntity> tileEntity) {
		this.zoneName=zoneName;
		this.width=width;
		this.height=height;
		this.tileGround=ground;
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
			return tileGround[x][y];
	}
	
	public Tile getTile(int x, int y) {
			return tiles[x][y];
	}
	
	public Tile getTileTop(int x, int y) {
			return tilesTop[x][y];
	}
	
	public ArrayList<TileEntity> getTileEntity(){
		return this.tileEntity;
	}
	
}
