package game.modele.utils.graph;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Graph {

	private Node[][] tab;
	
	public Graph(int width,int height) {
		tab = new Node[width][height];
		for(int x = 0; x < width; x++)
			for(int y = 0; y < height; y++)
				tab[x][y] = new Node();
	}
	
	public void addLinks(int x,int y,int xd,int yd,int value) {
		tab[x][y].addLink(new Link(tab[xd][yd], value));
	}
	
	
	public Map<SimpleEntry<Integer,Integer>,Stack<SimpleEntry<Integer,Integer>>> Dijkstra(){
		Map<SimpleEntry<Integer,Integer>,Stack<SimpleEntry<Integer,Integer>>> path;
		path = new HashMap<>();
		
		
		
		
		
		return null;
	}
	
	
	public void print() {
		for(int x = 0; x < tab.length; x++)
			for(int y = 0; y < tab[x].length ; y++) {
				System.out.println(tab[x][y].getLink().size()  + "  " + x + " : " + y);
			}
	}
	
}
