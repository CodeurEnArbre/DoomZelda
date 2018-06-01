package game.modele.world;

import java.awt.Point;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import game.modele.entity.Entity;
import game.modele.tile.Tile;
import game.modele.utils.Direction;
import game.modele.utils.graph.Graph;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WorldData {

	private StringProperty zoneName=new SimpleStringProperty("null");
	private Tile[][] tileGround;
	private Tile[][] tiles;
	private Tile[][] tilesTop;
	private IntegerProperty[][] luminosity;

	private Map<SimpleEntry<Integer,Integer>, Stack<SimpleEntry<Integer,Integer>>> dijkstra;

	public ObservableList<Entity> entity ;
	private int width,height;
	public Graph g;
	private boolean outSide;

	public WorldData(String zoneName, int width, int height, boolean outside, Tile ground[][], Tile tiles[][], Tile tilesTop[][], ArrayList<Entity> entitys) {
		this.entity = FXCollections.observableArrayList();
		this.luminosity = new SimpleIntegerProperty[width][height];
		newWorld(zoneName, width, height, outside, ground, tiles, tilesTop, entitys);
	}

	public void newWorld(String zoneName, int width, int height, boolean outside, Tile ground[][], Tile tiles[][], Tile tilesTop[][], ArrayList<Entity> entitys) {
		Entity.key=0;
		this.width=width;
		this.height=height;
		this.outSide=outside;
		this.tileGround=ground;
		this.tiles=tiles;
		this.tilesTop=tilesTop;
		this.entity.clear();
		for(Entity e : entitys) {
			this.entity.add(e);
			World.addKeyGameLoop(y -> e.update());
		}
		this.luminosity = new SimpleIntegerProperty[width][height];
		for(int x = 0 ; x < width ; x++)
			for(int y = 0; y < height ; y++) {
				luminosity[x][y] = new SimpleIntegerProperty(0);
			}

		this.zoneName.setValue(zoneName);
		dijkstra = new HashMap<>();
		g = new Graph(width, height);
	}

	public String getName() {
		return this.zoneName.get();
	}

	public boolean isOutside() {
		return this.outSide;
	}

	public StringProperty getNameProperty() {
		return this.zoneName;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public Tile getTileTerrain(int x, int y) {
		return tileGround[x][y];
	}

	public Tile getTile(int x, int y) {
		return tiles[x][y];
	}

	public Tile getTileTop(int x, int y) {
		return tilesTop[x][y];
	}

	public IntegerProperty getShadow(int x,int y) {
		return luminosity[x][y];
	}

	public ObservableList<Entity> getEntity(){
		return this.entity;
	}

	public Entity getEntity(int x, int y){
		for(Entity entity:entity) {
			if((int)entity.coordonnes.getX() == x && (int)entity.coordonnes.getY() == y)
				return entity;
		}
		return null;
	}

	public void deleteEntity(int primaryKey) {
		for(int i = 0; i < entity.size(); i++) {
			if(entity.get(i).primaryKey == primaryKey) {
				entity.remove(i);
			}
		}
	}

	public Entity[] entityHere(double x,double y) {
		return this.entity.stream().filter(a -> a.coordonnes.isSameTile(x, y)).toArray(Entity[]::new);
	}

	public void DirectionnalTorch(int x,int y,int intensity,int pas,int direction,boolean allumage) {
		ArrayList<SimpleEntry<Point,Integer>> q = new ArrayList<>(); //coord + intensity
		q.add(new SimpleEntry<Point, Integer>(new Point(x,y), intensity));
		if(allumage)
			addLight(x, y, intensity);
		else
			delLight(x, y, intensity);
		directionnalLight(q, intensity, pas, direction,allumage);
	}

	private void directionnalLight(ArrayList<SimpleEntry<Point,Integer>> q,int intensity,int pas,int direction,boolean allumage) {
		while(q.size() > 0) {
			//etape 1
			for(int x = 0; x < q.size();x++) {
				SimpleEntry<Point,Integer> value = q.get(x);	
				boolean canProgress = true;
				switch(direction)
				{
				case Direction.North:
					canProgress = LightProgressing(value, 0, 1,pas,allumage);
					break;
				case Direction.South:
					canProgress = LightProgressing(value, 0, -1,pas,allumage);
					break;
				case Direction.East:
					canProgress = LightProgressing(value, -1,0,pas,allumage);
					break;
				case Direction.West:
					canProgress = LightProgressing(value, 1,0,pas,allumage);
					break;
				default:
					break;
				}
				if(!canProgress)
				{
					q.remove(x);
					x--;
				}
			}
			//etape 2
			int y = q.size();
			for(int x = 0; x < y;x++) {
				SimpleEntry<Point,Integer> value = q.get(x);
				SimpleEntry<Point,Integer> nvalue1 = null;
				SimpleEntry<Point,Integer> nvalue2 = null;

				switch(direction)
				{
				case Direction.North:
				case Direction.South:
					nvalue1 = LightDiffuse(value,-1,0,pas);
					nvalue2 = LightDiffuse(value,1,0,pas);
					break;
				case Direction.East:
				case Direction.West:
					nvalue2 = LightDiffuse(value,0,-1,pas);
					nvalue1 = LightDiffuse(value, 0,1,pas);
					break;
				default:
					break;
				}
				AddElement(q, nvalue1,allumage);
				AddElement(q, nvalue2,allumage);
			}
		}
	}

	private void AddElement(ArrayList<SimpleEntry<Point,Integer>> q,SimpleEntry<Point,Integer> e,boolean add) {
		if(e != null && !containPoint(q,e.getKey()))
		{
			if(add)
				addLight(e.getKey().x,e.getKey().y, e.getValue());
			else
				delLight(e.getKey().x,e.getKey().y, e.getValue());
			q.add(e);
		}
	}

	private boolean containPoint(ArrayList<SimpleEntry<Point,Integer>> q, Point p) {
		for(SimpleEntry<Point,Integer> e : q)
			if(e.getKey().equals(p))
				return true;

		return false;
	}

	private boolean LightProgressing(SimpleEntry<Point,Integer> value,int vx,int vy,int pas,boolean add) {
		int nx = value.getKey().x + vx;
		int ny = value.getKey().y + vy;

		if(value.getValue() - pas >= 0 && nx >= 0 && ny >= 0 && nx < height && ny < width) {
			value.getKey().translate(vx,vy);
			value.setValue(value.getValue() - pas);
			if(add) {
				addLight(nx,ny, value.getValue());
			}
			else
				delLight(nx,ny, value.getValue());
		}
		else
		{
			return false;
		}
		return true;
	}

	private SimpleEntry<Point,Integer> LightDiffuse(SimpleEntry<Point,Integer> value,int vx,int vy,int pas) {
		int nx = value.getKey().x + vx;
		int ny = value.getKey().y + vy;

		if(value.getValue() - pas >= 0 && nx >= 0 && ny >= 0 && nx < height && ny < width) {
			return new SimpleEntry<Point, Integer>(new Point(nx,ny),value.getValue() - pas);
		}
		else
		{
			return null; 
		}
	}

	public void MultiDirectionnalTorch(int x,int y,int i,int pas,boolean add) {

		ArrayList<SimpleEntry<Point,Integer>> allLight = new ArrayList<>(); 
		allLight.add(new SimpleEntry<Point, Integer>(new Point(x,y), i));

		while(allLight.size() > 0) {
			for(int[] vector : new int[][] {{0,1},{0,-1},{1,0},{-1 , 0}}) {

			}




		}
	}

	private void addLight(int x,int y,int value) {
		World.currentMap.luminosity[x][y]
				.set(World.currentMap.luminosity[x][y]
						.get() + value);
	}

	private void delLight(int x,int y,int value) {
		World.currentMap.luminosity[x][y]
				.set(World.currentMap.luminosity[x][y]
						.get() - value);
	}
}
