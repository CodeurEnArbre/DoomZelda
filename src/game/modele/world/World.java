package game.modele.world;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import game.modele.entity.Entity;
import game.modele.entity.EntityFactory;
import game.modele.entity.EntityTP;
import game.modele.entity.Player.Player;
import game.modele.tile.Tile;
import game.modele.tile.TileFactory;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class World {

	public static BooleanProperty onPause = new SimpleBooleanProperty(false);
	public static WorldData currentMap;
	public static Player player;
	public static BooleanProperty isWorldLoaded = new SimpleBooleanProperty(false);
	
	
	private static Timeline GameLoop = new Timeline();

	public static void loadGameLoop() {
		GameLoop.setCycleCount(Timeline.INDEFINITE);
		GameLoop.play();
	}

	//Ajoute des fonctions qui seront �x�cut� dans la gameloop
	private static void addKeyGameLoop(EventHandler<ActionEvent> e) {
		KeyFrame keyf = new KeyFrame(Duration.seconds(0.017),e);
		GameLoop.getKeyFrames().add(keyf);
	}

	public static void pauseGameLoop() {
		GameLoop.pause();
		onPause.set(true);
	}
	
	public static void playGameLoop() {
		GameLoop.play();
	}
	
	public static void loadPlayer() {
		player = new Player(null,new Coordonnees(14,10),new Direction(5));
		addEntity(player);
	}

	public static void addEntity(Entity e) {
		if(e != null) {
			currentMap.entity.add(e);
			addKeyGameLoop(y -> e.update());
		}
	}
	
	
	/*
	 * Chargement de la map : cr�ation du tableau utilis� par l'affichage
	 * */
	public static void loadWorld(String file, String worldName) {
		TileFactory.load();
		try {
			//Chargement des tiles
			BufferedReader tilesData = new BufferedReader(new FileReader(new File("ressources/map/"+file+".map")));

			String name = tilesData.readLine();
			int width = Integer.parseInt(tilesData.readLine());
			int height = Integer.parseInt(tilesData.readLine());
			boolean outside = Boolean.parseBoolean(tilesData.readLine());

			Tile[][] tileGround = makeTileGrid(width, height, tilesData);

			Tile[][] tileSolid =  makeTileGrid(width, height, tilesData);

			Tile[][] tileTop=  makeTileGrid(width, height, tilesData);

			tilesData.close();

			if(worldName==null)
				currentMap=new WorldData(name, width, height, outside, tileGround, tileSolid, tileTop, loadEntity(file));
			else {
				currentMap.newWorld(worldName, width, height, outside, tileGround, tileSolid, tileTop, loadEntity(file));
			}
			isWorldLoaded.setValue(true);
		}catch(IOException e) {
			System.out.println("Impossible de charger la map");
			e.printStackTrace();
		}
		
	}

	public static ArrayList<Entity> loadEntity(String world) throws IOException{
		ArrayList<Entity> entity= new ArrayList<Entity>();
		BufferedReader entityData = new BufferedReader(new FileReader(new File("ressources/map/"+world+".entity")));

		String nextLine = entityData.readLine();
		while(nextLine!= null && nextLine.length()> 1) {
			entity.add(EntityFactory.create(nextLine, entityData.readLine()));
			//entity.add(EntityFactory.create(entityData.readLine(), entityData.readLine()));

			nextLine = entityData.readLine();
		}
		entityData.close();
		return entity;
	}

	/*
	 * Chargement d'un Tableau de Tile utilis� par la fonction loadWorld();
	 * */
	private static Tile[][] makeTileGrid(int width,int height,BufferedReader br){
		try {
			Tile[][] tile = new Tile[height][width];
			Pattern pat = Pattern.compile(",");
			for(int i = 0; i < height;i++) {
				String[] tabGround = pat.split(new StringBuilder(br.readLine()));
				for(int x = 0; x < tabGround.length ; x++)
					tile[i][x] = TileFactory.get(Integer.parseInt(tabGround[x]));
			}
			return tile;
		}catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static WorldData getWorld() {
		return World.currentMap;
	}
}
