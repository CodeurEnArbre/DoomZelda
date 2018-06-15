package game.modele.world;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import game.modele.entity.Entity;
import game.modele.entity.EntityFactory;
import game.modele.entity.living.Player;
import game.modele.item.Item;
import game.modele.item.weapon.Weapon;
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

	private static Timeline GameLoop;

	public static void loadGameLoop() {
		if(GameLoop != null)
			GameLoop.stop();
		
		GameLoop = new Timeline();
		GameLoop.setCycleCount(Timeline.INDEFINITE);
	}

	//Ajoute des fonctions qui seront execute dans la gameloop
	public static void addKeyGameLoop(EventHandler<ActionEvent> e) {
		KeyFrame keyf = new KeyFrame(Duration.seconds(1/60),e);
		GameLoop.getKeyFrames().add(keyf);
	}

	public static void pauseGameLoop() {
		GameLoop.pause();
		onPause.set(true);
	}

	public static void playGameLoop() {
		GameLoop.play();
	}

	public static void initWorldSave(String world, Coordonnees coord, Direction direction, int maxPv, int pv, int ruby,  Weapon[] weapons,Item leftEquip, Item rightEquip) {

		loadGameLoop();
		
		player = new Player(coord,direction,maxPv,pv,ruby,weapons,leftEquip, rightEquip);
		
		loadWorld(world,null);
		addEntity(player);
	
		GameLoop.getKeyFrames().add(new KeyFrame(Duration.seconds(0.017), e ->{
			World.currentMap.g.Dijkstra((int)World.player.coordonnes.getY(),(int)World.player.coordonnes.getX());
		}));
		
		GameLoop.getKeyFrames().add(new KeyFrame(Duration.seconds(0.017), e ->{
			World.currentMap.g.AlgoLigneDroite((int)World.player.coordonnes.getY(),(int)World.player.coordonnes.getX());
		}));
		
		isWorldLoaded.setValue(true);


		currentMap.g.initNodes();
		
		
		//Demarage des la gameloop
		playGameLoop();
		onPause.set(false);

	}

	public static void addEntity(Entity e) {
		currentMap.entity.add(e);
		addKeyGameLoop(y -> e.update());
	}


	/*
	 * Chargement de la map : creation du tableau utilisee par l'affichage
	 * */
	public static void loadWorld(String file, String worldName) {
		if(currentMap != null)
			for(Entity e : currentMap.entity) {
				if(!e.getId().equals("Player"))
					e.dispose();
			}
		
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
			
			Entity.key=0;
			ArrayList<Entity> entitys = loadEntity(file);

			if(worldName==null) {
				currentMap=new WorldData(name, width, height, outside, tileGround, tileSolid, tileTop, entitys);
			}else {
				currentMap.newWorld(worldName, width, height, outside, tileGround, tileSolid, tileTop, entitys);
			}
			World.currentMap.g.initNodes();
		}catch(IOException e) {
			System.out.println("Impossible de charger la map");
			e.printStackTrace();
		}

	}

	public static ArrayList<Entity> loadEntity(String world) throws IOException{
		ArrayList<Entity> entity= new ArrayList<Entity>();
		BufferedReader entityData = new BufferedReader(new FileReader(new File("saves/"+Save.saveName+"/data/"+world+".entity")));

		String nextLine = entityData.readLine();
		while(nextLine != null && nextLine.length()> 1) {
			Entity newEntity = EntityFactory.create(nextLine, entityData.readLine());
			if(newEntity != null)
				entity.add(newEntity);
			nextLine = entityData.readLine();
		}
		entityData.close();
		return entity;
	}

	/*
	 * Chargement d'un Tableau de Tile utilise par la fonction loadWorld();
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

}
