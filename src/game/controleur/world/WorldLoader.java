package game.controleur.world;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import game.controleur.tile.TileEntity;
import game.controleur.tile.TileSolid;
import game.controleur.tile.TileTerrain;
import game.controleur.tile.TileTop;

public class WorldLoader {
	
	public static World currentMap;
	
	public static void loadWorld(String file) {
		try {
		
			String name="";
			int width = 0,height=0;
			TileTerrain[] tileBackground = null;
			TileSolid[] tile = null;
			TileTop[] tileTop = null;
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
				case 4://Background/Soil
					tileBackground=new TileTerrain[width*height];
					int charAt=0;
					int tileAt=0;
					String tileId="";
					while(charAt<ligne.length()) {
						if(ligne.charAt(charAt)==',') {
							tileBackground[tileAt]=new TileTerrain(Integer.parseInt(tileId));
							tileAt++;
							tileId="";
						}else {
							tileId+=""+ligne.charAt(charAt);
						}
						
						charAt++;
					}
					if(tileId!=null)
						tileBackground[tileAt]=new TileTerrain(Integer.parseInt(tileId));
					break;
					
				case 5://Solid
					tile=new TileSolid[width*height];
					charAt=0;
					tileAt=0;
					tileId="";
					while(charAt<ligne.length()) {
						if(ligne.charAt(charAt)==',') {
							tile[tileAt]=new TileSolid(Integer.parseInt(tileId));
							tileAt++;
							tileId="";
						}else {
							tileId+=""+ligne.charAt(charAt);
						}
						
						charAt++;
					}
					if(tileId!=null)
						tile[tileAt]=new TileSolid(Integer.parseInt(tileId));
					break;
					
				case 6://Top
					tileTop=new TileTop[width*height];
					charAt=0;
					tileAt=0;
					tileId="";
					while(charAt<ligne.length()) {
						if(ligne.charAt(charAt)==',') {
							tileTop[tileAt]=new TileTop(Integer.parseInt(tileId));
							tileAt++;
							tileId="";
						}else {
							tileId+=""+ligne.charAt(charAt);
						}
						
						charAt++;
					}
					if(tileId!=null)
						tileTop[tileAt]=new TileTop(Integer.parseInt(tileId));
					break;
				}
				
				ligneNum++;
			}
			br.close();
		
			currentMap=new World(name, width, height, tileBackground, tile, tileTop, tileEntity);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
