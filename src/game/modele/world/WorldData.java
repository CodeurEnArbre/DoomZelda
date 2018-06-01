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

	public void AddDirectionnalTorch(int x,int y,int i,int pas,int d) {
		ArrayList<SimpleEntry<Point,Integer>> q = new ArrayList<>(); //coord + intensity
		q.add(new SimpleEntry<Point, Integer>(new Point(x,y), i));
		addLight(x, y, i);
		directionnalLight(q, i, pas, d);
	}

	private void directionnalLight(ArrayList<SimpleEntry<Point,Integer>> q,int i,int pas,int d) {
		while(q.size() > 0) {
			//etape 1
			for(int x = 0; x < q.size();x++) {
				SimpleEntry<Point,Integer> value = q.get(x);
				boolean canProgress = true;
				switch(d)
				{
				case Direction.North:
					canProgress = LightProgressing(value, 0, 1,pas);
					break;
				case Direction.South:
					canProgress = LightProgressing(value, 0, -1,pas);
					break;
				case Direction.West:
					canProgress = LightProgressing(value, 1,0,pas);
					break;
				case Direction.East:
					canProgress = LightProgressing(value, -1,0,pas);
					break;
				default:
					//valeur incorrecte
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

				switch(d)
				{
				case Direction.North:
				case Direction.South:
					nvalue1 = LightDiffuse(value,-1,0,pas);
					nvalue2 = LightDiffuse(value,1,0,pas);
					break;
				case Direction.West:
				case Direction.East:
					nvalue1 = LightDiffuse(value,0,1,pas);
					nvalue2 = LightDiffuse(value, 0,-1,pas);
					break;
				default:
					//valeur incorrecte
					break;
				}
				if(nvalue1 != null && !q.contains(nvalue1))
				{
					addLight(nvalue1.getKey().x, nvalue1.getKey().y, nvalue1.getValue());
					q.add(nvalue1);
				}
				if(nvalue2 != null && !q.contains(nvalue2))
				{
					addLight(nvalue2.getKey().x, nvalue2.getKey().y, nvalue2.getValue());
					q.add(nvalue2);
				}
			}
		}
	}

	private boolean LightProgressing(SimpleEntry<Point,Integer> value,int vx,int vy,int pas) {
		int nx = value.getKey().x + vx;
		int ny = value.getKey().y + vy;

		if(value.getValue() - pas >= 0 && nx > 0 && ny > 0 && nx < height && ny < width && !tiles[ny][nx].solid() ) {
			value.getKey().translate(vx,vy);
			value.setValue(value.getValue() - pas);
			addLight(nx,ny, value.getValue());
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

		if(value.getValue() - pas >= 0 && nx > 0 && ny > 0 && nx < height && ny < width && !tiles[ny][nx].solid() ) {
			return new SimpleEntry<Point, Integer>(new Point(nx,ny),value.getValue() - pas);
		}
		else
		{
			return null; 
		}
	}

	private void addLight(int x,int y,int value) {
		World.currentMap.luminosity[x][y]
				.set(World.currentMap.luminosity[x][y]
						.get() + value);
	}

	public void AddTorch(int x,int y,int i,int pas) {
		ArrayList<SimpleEntry<Integer,Integer>> tmp = new ArrayList<>();
		tmp.add(new SimpleEntry<Integer, Integer>(x,y));
		int current = 0;
		addLight(tmp, current, i, pas);

	}

	public void addLight(ArrayList<SimpleEntry<Integer,Integer>> tmp, int current , int i , int pas) {
		if(i == 0) return;

		int lenght = tmp.size();
		for(int p = current; p < lenght;p++) {			
			SimpleEntry<Integer,Integer> si = tmp.get(p);

			int x = si.getKey();
			int y = si.getValue();

			if(x < this.getHeight() && x >= 0  &&  y < this.getWidth()  && y >= 0) {
				if(!tiles[y][x].solid()) //remplacer par test TYLEENTITY
					World.currentMap.luminosity[x][y].set(World.currentMap.luminosity[x][y].get() + i);
				else {
					current++;
					continue;
				}

			}

			if(!tmp.contains(new SimpleEntry<Integer, Integer>(x + 1,y)))
				tmp.add(new SimpleEntry<Integer, Integer>(x + 1,y));

			if(!tmp.contains(new SimpleEntry<Integer, Integer>(x,y + 1)))
				tmp.add(new SimpleEntry<Integer, Integer>(x,y + 1));

			if(!tmp.contains(new SimpleEntry<Integer, Integer>(x - 1,y)))
				tmp.add(new SimpleEntry<Integer, Integer>(x - 1,y));

			if(!tmp.contains(new SimpleEntry<Integer, Integer>(x,y - 1)))
				tmp.add(new SimpleEntry<Integer, Integer>(x,y - 1));



			current++;
		}

		addLight(tmp, current, i - pas, pas);
	}
}
