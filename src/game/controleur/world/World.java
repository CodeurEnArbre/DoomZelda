package game.controleur.world;

import java.util.ArrayList;

import game.controleur.tile.Tile;
import game.controleur.tile.TileEntity;

public class World {
	
	private String zoneName;
	private Tile tiles[];
	private ArrayList<TileEntity> tileEntity;
	private int width,height;
	
	public World(String zoneName, int width, int height, Tile[] tiles, ArrayList<TileEntity> tileEntity) {
		this.zoneName=zoneName;
		this.width=width;
		this.height=height;
		this.tiles=tiles;
		this.tileEntity=tileEntity;
	}
	
	public String getName() {
		return this.zoneName;
	}
	
	public Tile getTile(int x, int y) {
		if(x<=this.width||y<=this.height) {
			return tiles[x+y*this.width+1];
		}else
			return null;
	}
	
	public ArrayList<TileEntity> getTileEntity(){
		return this.tileEntity;
	}
	
}
