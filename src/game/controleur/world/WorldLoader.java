package game.controleur.world;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import game.controleur.tile.Tile;
import game.controleur.tile.TileEntity;

public class WorldLoader {
	
	public static World currentMap;
	
	public static void loadWorld(String file) {
		try {
		
			String name="";
			int width = 0,height=0;
			Tile[] tileGround = null;
			Tile[] tileSolid = null;
			Tile[] tileTop = null;
			ArrayList<TileEntity> tileEntity= new ArrayList<TileEntity>();
		
			
			BufferedReader br = new BufferedReader(new FileReader(new File("ressources/map/"+file+".map")));
			String ligne;
			int ligneNum=1;
			while ((ligne = br.readLine()) != null) {
				
				switch(ligneNum){
				case 1:
					name=ligne;
					break;
				case 2:
					width=Integer.parseInt(ligne);
					break;
				case 3:
					height=Integer.parseInt(ligne);
					break;
				case 4:
					tileGround=(Tile[]) loadTile(ligne, width, height);
					break;
				case 5:
					tileSolid=(Tile[]) loadTile(ligne, width, height);
					break;
				case 6:
					tileTop=(Tile[]) loadTile(ligne, width, height);
					break;
				}
				
				ligneNum++;
			}
			br.close();
		
			currentMap=new World(name, width, height, tileGround, tileSolid, tileTop, tileEntity);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static Tile[] loadTile(String ligne, int width, int height) {
		Tile[] tile =new Tile[width*height];
		int charAt=0;
		int tileAt=0;
		String tileId="";
		while(charAt<ligne.length()) {
			if(ligne.charAt(charAt)==',') {
				tile[tileAt]=new Tile((Integer.parseInt(tileId)<=0?1:Integer.parseInt(tileId)));
				tileAt++;
				tileId="";
			}else {tileId+=""+ligne.charAt(charAt);}
			charAt++;
		}
		if(tileId!=null)
			tile[tileAt]=new Tile((Integer.parseInt(tileId)<=0?1:Integer.parseInt(tileId)));
		return tile;
	}
	
}
