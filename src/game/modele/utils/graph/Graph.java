package game.modele.utils.graph;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import game.modele.world.World;

public class Graph {

	public static final int[] left = new int[] {-1,0};
	public static final int[] right = new int[] {1,0};
	public static final int[] top = new int[] {0,-1};
	public static final int[] bot = new int[] {0,1};

	public int[][] node;
	public SimpleEntry<Integer,int[]>[][] direction;


	private int width;
	private int height;

	public Graph(int width,int height) {
		this.width = width;
		this.height = height;
		node = new int[width][height];
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
		push(x,y,new int[] {0,0},0);
		Dijcalcul(0);
		
		for(SimpleEntry<Integer,int[]>[] s : direction) {
			for(SimpleEntry<Integer,int[]> d : s) {
				if(d != null)
					System.out.print(d.getKey() + " ");
				else
					System.out.print("-1" );
			}
		System.out.println();
		}
	}
	private void Dijcalcul(int min) {
		for(int x = 0; x < width;x++) {
			for(int y = 0; y < height;y++) {
				if(direction[x][y] == null)continue;
				
				SimpleEntry<Integer,int[]> e = direction[x][y];

				if(e.getKey() >= min) {

					for(int[] direc : new int[][]{left,right,top,bot}) {

						int tmp = push(x,y,direc,min);
						if(tmp != -10)	{
							Dijcalcul(tmp);
						}
					}
				}
			}
		}
	}
	private int push(int x,int y,int[] t,int limite) {
		int nx = x + t[0];
		int ny = y + t[1];


		if(nx >= width || ny >= height || nx < 0 || ny < 0) return -10;

		int value = (node[x][y] + node[nx][ny]) / 2;

		if(node[nx][ny] < limite)return -10;

		SimpleEntry<Integer,Integer> rootKey = new SimpleEntry<Integer, Integer>(x,y);
		SimpleEntry<Integer,Integer> key = new SimpleEntry<Integer, Integer>(nx,ny);

		if(x == nx && y == ny) { 
			direction[nx][ny] = new SimpleEntry<Integer, int[]>(0, t);
			return 0;
		}

		if(direction[x][y] != null)
			value += direction[x][y].getKey();

		if(direction[nx][ny] != null) {
			if(direction[nx][ny].getKey() > value) {
				direction[nx][ny] = new SimpleEntry<Integer, int[]>(value, t);
				return value;
			}else {
				return -10;
			}
		}else {
			direction[nx][ny] = new SimpleEntry<Integer, int[]>(value,t);
			return value;
		}
	}

	private int getValue(int x,int y) {
		if(x >= height || x < 0 || y >= width || y < 0) {
			return Integer.MIN_VALUE;
		}
		else if(!World.currentMap.getTile(x, y).solid())
			return (World.currentMap.getTileTerrain(x,y).speedIndice());
		else return -1;
	}
}
