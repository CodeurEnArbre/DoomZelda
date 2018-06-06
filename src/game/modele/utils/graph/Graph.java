package game.modele.utils.graph;

import java.awt.Point;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.LinkedList;

import game.modele.world.World;

public class Graph {

	public static final int[] top = new int[] {-1,0};
	public static final int[] bot = new int[] {1,0};
	public static final int[] right = new int[] {0,-1};
	public static final int[] left = new int[] {0,1};

	public int[][] node;//contient les valeurs de déplacement de chaque tile


	public SimpleEntry<Integer,int[]>[][] direction;//indique la direction à prendre calculer par dijkstra
	public LinkedList<Point> queue;

	public ArrayList<SimpleEntry<Point, int[]>> ligneDroite;

	private int width;
	private int height;

	public Graph(int width,int height) {
		this.width = width;
		this.height = height;
		node = new int[width][height];
		ligneDroite = new ArrayList<>();
	}

	public void init() {
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				node[x][y] = getValue(x, y);
			}
		}
	}

	public void Dijkstra(int x,int y){
		direction = new SimpleEntry[width][height];
		queue = new LinkedList<Point>();
		push(x,y,new int[]{0,0},0);
		queue.addLast(new Point(x,y));
		Dijcalcul(0);	

	}
	private void Dijcalcul(int min) {
		while(queue.peek() != null) {
			Point e = queue.getFirst();
			queue.removeFirst();
			int x = e.x;
			int y = e.y;
			for(int[] direc : new int[][]{left,right,top,bot}) {
				push(x,y,direc,min);
			}
		}
	}
	private void push(int x,int y,int[] t,int limite) {
		int nx = x + t[0];
		int ny = y + t[1];

		if(nx >= width || ny >= height || nx < 0 || ny < 0 || node[nx][ny] == -1) return;

		if(x == nx && y == ny) { 
			direction[nx][ny] = new SimpleEntry<Integer, int[]>(0, t);			
		}

		int value = (node[x][y] + node[nx][ny]) / 2;
		if(direction[x][y] != null)
			value += direction[x][y].getKey();



		if(direction[nx][ny] != null) {
			if(direction[nx][ny].getKey() > value) {
				direction[nx][ny] = new SimpleEntry<Integer, int[]>(value, t);
				queue.addLast(new Point(nx,ny));
			}else {
				return;
			}
		}else {
			direction[nx][ny] = new SimpleEntry<Integer, int[]>(value,t);
			queue.addLast(new Point(nx,ny));
		}
	}
	private int getValue(int x,int y) {
		if(x >= height || x < 0 || y >= width || y < 0) {
			return -1;
		}
		else if(!World.currentMap.getTile(x, y).solid())
			return (World.currentMap.getTileTerrain(x,y).speedIndice());
		else return -1;
	}

	/*
	Algo qui determine les différents points accessibles
	 en ligne droite et sans obstacle par rapport aux coordonnés du joueur
	 */
	public void AlgoLigneDroite(int x,int y) {
		ligneDroite.clear();
		for(int[] vector : new int[][] {top,bot,left,right}) {
			ligneDroite(x,y,vector);
		}
	}
	private void ligneDroite(int x,int y,int[] direction) {
		x += direction[0];
		y += direction[1];
		while(x >= 0 && y >= 0 && x < width && y < height && !World.currentMap.getTile(x, y).solid()) {
			ligneDroite.add(new SimpleEntry<Point, int[]>(new Point(x,y), direction));
			x += direction[0];
			y += direction[1];
		}
	}



}
