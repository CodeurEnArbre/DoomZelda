package game.modele.world;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import game.modele.entity.Player;
import game.modele.tile.Tile;
import game.modele.tile.TileEntity;
import game.modele.utils.Coordonnees;
import game.modele.utils.Orientation.Direction;

public class WorldLoader {

	public static World currentMap;
	public static Player player;

	public static void loadPlayer() {
		player = new Player(null,new Coordonnees(3,3),Direction.NORTH);
	}

	public static void loadWorld(String file) {
		try {
			ArrayList<TileEntity> tileEntity= new ArrayList<TileEntity>();

			Tile[][] tileSolid = null;
			Tile[][] tileTop = null;

			BufferedReader br = new BufferedReader(new FileReader(new File("ressources/map/"+file+".map")));

			String name = br.readLine();
			int width = Integer.parseInt(br.readLine());
			int height = Integer.parseInt(br.readLine());

			Tile[][] tileGround = new Tile[width][height];
			Pattern pat = Pattern.compile(",");
			for(int i = 0; i < height;i++) {
				String[] tabGround = pat.split(new StringBuilder(br.readLine()));
				for(int x = 0; x < tabGround.length ; x++)
					tileGround[i][x] = new Tile(Integer.parseInt(tabGround[x]));
			}

			String[] tabSolid = pat.split(new StringBuilder(br.readLine()));

			String[] tabTop = pat.split(new StringBuilder(br.readLine()));

			br.close();

			currentMap=new World(name, width, height, tileGround, tileSolid, tileTop, tileEntity);

		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
