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
import game.modele.utils.Direction;
import javafx.scene.input.KeyCode;

public class WorldLoader {

	public static World currentMap;
	public static Player player;

	public static void loadPlayer() {
		player = new Player(null,new Coordonnees(14,10),new Direction(5));
	}

	/*
	 * Assignation des booleans de direction à l'enfoncement de la touche
	 * */
	public static void KeyInteractDown(KeyCode k) {

		if(k == KeyCode.Z) {

			WorldLoader.player.moveUP = true;
			WorldLoader.player.moveDown = false;
			setDirection(Direction.North);	

		}else if(k == KeyCode.S){
			WorldLoader.player.moveDown = true;
			WorldLoader.player.moveUP = false;
			setDirection(Direction.South);

		}
		if(k == KeyCode.Q) {
			WorldLoader.player.moveLeft = true;
			WorldLoader.player.moveRight = false;
			setDirection(Direction.East);

		}else if(k == KeyCode.D) {
			WorldLoader.player.moveRight = true;	
			WorldLoader.player.moveLeft = false;
			setDirection(Direction.West);

		}
	}

	/*
	 * Set Direction
	 * */
	public static void setDirection(int direction) {
		WorldLoader.player.getOrientation().getDirectionProperty().setValue(direction);
	}

	/*
	 * Assignation des booleans de direction au relachement de la touche
	 * */
	public static void KeyInteractUp(KeyCode k) {
		if(k == KeyCode.Z) {
			WorldLoader.player.moveUP = false;
		}else if(k == KeyCode.S) {
			WorldLoader.player.moveDown = false;
		}else if(k == KeyCode.Q) {
			WorldLoader.player.moveLeft = false;
		}else if(k == KeyCode.D) {
			WorldLoader.player.moveRight = false;
		}
	}

	/*
	 * Chargement de la map : création du tableau utilisé par l'affichage
	 * */
	public static void loadWorld(String file) {
		try {
			ArrayList<TileEntity> tileEntity= new ArrayList<TileEntity>();
			BufferedReader br = new BufferedReader(new FileReader(new File("ressources/map/"+file+".map")));

			String name = br.readLine();
			int width = Integer.parseInt(br.readLine());
			int height = Integer.parseInt(br.readLine());


			Tile[][] tileGround = makeTileGrid(width, height, br);

			Tile[][] tileSolid =  makeTileGrid(width, height, br);

			Tile[][] tileTop=  makeTileGrid(width, height, br);

			br.close();

			currentMap=new World(name, width, height, tileGround, tileSolid, tileTop, tileEntity);

		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Chargement d'un Tableau de Tile utilisé par la fonction loadWorld();
	 * */
	private static Tile[][] makeTileGrid(int width,int height,BufferedReader br){
		try {
			Tile[][] tile = new Tile[height][width];
			Pattern pat = Pattern.compile(",");
			for(int i = 0; i < height;i++) {
				String[] tabGround = pat.split(new StringBuilder(br.readLine()));
				for(int x = 0; x < tabGround.length ; x++)
					tile[i][x] = new Tile(Integer.parseInt(tabGround[x]));
			}
			return tile;
		}catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
