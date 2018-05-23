package game.modele.utils.graph;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Graph {

	private Node[][] tab;
	private Stack<Link>[][] allPath;	
	
	
	public Graph(int width,int height) {
		tab = new Node[width][height];
		for(int x = 0; x < width; x++)
			for(int y = 0; y < height; y++)
				tab[x][y] = new Node(x,y);
		
		allPath = new Stack[width][height];
		for(int xPoint = 0; xPoint < allPath.length;xPoint++) {
			for(int yPoint = 0; yPoint < allPath[xPoint].length;yPoint++) {
			
				allPath[xPoint][yPoint] = new Stack<>();
				
			}
		}
	}
	
	public void addLinks(int x,int y,int xd,int yd,int value) {
		tab[x][y].addLink(new Link(tab[x][y],tab[xd][yd], value));
	}
	
	public Stack<Link> getPath(int x,int y) {
		return allPath[x][y];
	}
	
	
	public Map<SimpleEntry<Integer,Integer>,Stack<Link>> Dijkstra(int x,int y){
		Map<SimpleEntry<Integer,Integer>,Stack<Link>> path = new HashMap<>();
		recDijk(y, x);
		return null;
	}
	
	private void recDijk(int x,int y) {
		for(Link l : tab[x][y].getLink()) {
			int currentPathValue = allPath[l.getNodeArrive().coord.getKey()][l.getNodeArrive().coord.getValue()].stream().mapToInt(o -> o.getValue()).sum();
			int possiblePathValue = allPath[x][y].stream().mapToInt(o -> o.getValue()).sum() + l.getValue();
			
			if(currentPathValue == 0 || currentPathValue > possiblePathValue) {
				allPath[l.getNodeArrive().coord.getKey()][l.getNodeArrive().coord.getValue()] = new Stack<>();
				
				for(Link copy : allPath[x][y]) {
					allPath[l.getNodeArrive().coord.getKey()][l.getNodeArrive().coord.getValue()].push(new Link(copy.getNodeDepart(),copy.getNodeArrive(),copy.getValue()));
				}
				allPath[l.getNodeArrive().coord.getKey()][l.getNodeArrive().coord.getValue()].push(new Link(l.getNodeDepart(),l.getNodeArrive(),l.getValue()));
				
				recDijk(l.getNodeArrive().coord.getKey(),l.getNodeArrive().coord.getValue());
			}
		}
	}	
}
