package game.controleur.world;

import java.util.ArrayList;

import game.controleur.tile.TileEntity;
import game.controleur.tile.TileSolid;
import game.controleur.tile.TileTerrain;
import game.controleur.tile.TileTop;

public class World {
	
	private String zoneName;
	private TileTerrain ground[];
	private TileSolid tiles[];
	private TileTop tilesTop[];
	private ArrayList<TileEntity> tileEntity;
	private int width,height;
	
	public World(String zoneName, int width, int height, TileTerrain ground[], TileSolid tiles[], TileTop tilesTop[], ArrayList<TileEntity> tileEntity) {
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
	
	public TileTerrain getTileTerrain(int x, int y) {
		if(x<=this.width||y<=this.height) {
			return ground[x+y*this.width+1];
		}else
			return null;
	}
	
	public TileSolid getTile(int x, int y) {
		if(x<=this.width||y<=this.height) {
			return tiles[x+y*this.width+1];
		}else
			return null;
	}
	
	public TileTop getTileTop(int x, int y) {
		if(x<=this.width||y<=this.height) {
			return tilesTop[x+y*this.width+1];
		}else
			return null;
	}
	
	public ArrayList<TileEntity> getTileEntity(){
		return this.tileEntity;
	}
	
}
