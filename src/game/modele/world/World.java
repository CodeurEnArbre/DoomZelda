package game.modele.world;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import game.modele.entity.Entity;
import game.modele.entity.EntityTP;
import game.modele.entity.Player.Player;
import game.modele.tile.Tile;
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

	public static BooleanProperty onPause;
	public static WorldData currentMap;
	public static Player player;
	
	private static Timeline GameLoop = new Timeline();

	public static void loadGameLoop() {
		onPause = new SimpleBooleanProperty();
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
	}
	
	public static void playGameLoop() {
		GameLoop.play();
	}
	
	public static void loadPlayer() {
		player = new Player(null,new Coordonnees(14,10),new Direction(5));
		addKeyGameLoop(e -> player.update());
	}

	/*
	 * Chargement de la map : cr�ation du tableau utilis� par l'affichage
	 * */
	public static void loadWorld(String file, String worldName) {
		try {
			//Chargement des tiles
			BufferedReader tilesData = new BufferedReader(new FileReader(new File("ressources/map/"+file+".map")));

			String name = tilesData.readLine();
			int width = Integer.parseInt(tilesData.readLine());
			int height = Integer.parseInt(tilesData.readLine());


			Tile[][] tileGround = makeTileGrid(width, height, tilesData);

			Tile[][] tileSolid =  makeTileGrid(width, height, tilesData);

			Tile[][] tileTop=  makeTileGrid(width, height, tilesData);

			tilesData.close();

			if(worldName==null)
				currentMap=new WorldData(name, width, height, tileGround, tileSolid, tileTop, loadEntity(file));
			else {
				currentMap.newWorld(worldName, width, height, tileGround, tileSolid, tileTop, loadEntity(file));
			}

		}catch(IOException e) {
			System.out.println("Impossible de charger la map");
			e.printStackTrace();
		}
	}

	public static ArrayList<Entity> loadEntity(String world) throws IOException{
		ArrayList<Entity> entity= new ArrayList<Entity>();
		BufferedReader entityData = new BufferedReader(new FileReader(new File("ressources/map/"+world+".entity")));

		String nextLine = entityData.readLine();
		while(nextLine!= null && nextLine.equals(",")) {
			String entityType = entityData.readLine();
			double x = Double.parseDouble(entityData.readLine());
			double y = Double.parseDouble(entityData.readLine());

			switch (entityType) {

			case "TileEntity":
				boolean etatTileEntity= Boolean.parseBoolean(entityData.readLine());
				entityData.readLine();
				int idTileEntity=Integer.parseInt(entityData.readLine());
				entityData.readLine();
				break;

			case "EntityLiving":
				int etatEntityLiving= Integer.parseInt(entityData.readLine());
				Direction directionEntityLiving=new Direction(Integer.parseInt(entityData.readLine()));
				int idEntityLiving=Integer.parseInt(entityData.readLine());//TODO a ajouter id dans EntityLiving
				entityData.readLine();

				break;

			case "TileEntityTP":
				boolean etatTileEntityTP= Boolean.parseBoolean(entityData.readLine());
				String mapTP = entityData.readLine();
				double xTP=Double.parseDouble(entityData.readLine());
				double yTP=Double.parseDouble(entityData.readLine());//TODO 1-> tpid
				entity.add(new EntityTP(0, new Coordonnees(x, y), etatTileEntityTP, mapTP, new Coordonnees(xTP, yTP)));
				break;
			}

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
					tile[i][x] = new Tile(Integer.parseInt(tabGround[x]));
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
