package game.controleur.world;

import java.util.ArrayList;

import game.controleur.tile.Tile;
import game.controleur.tile.TileEntity;

public class World {
	
	private String zoneName;
	private Tile tiles[];
	private ArrayList<TileEntity> tileEntitys;
	private int width,height;
	
	public World(String zoneName, int width, int height, Tile[] tiles, ArrayList<TileEntity> tileEntitys) {
		this.zoneName=zoneName;
		this.width=width;
		this.height=height;
		this.tiles=tiles;
		this.tileEntitys=tileEntitys;
	}
	
}
