package game.modele.utils.graph;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import game.modele.entity.Entity.infoDeplacement;

public class Graph {
	
	
	private int[][] tab;
	public infoDeplacement[][] allPath;	
	
	
	public Graph(int width,int height) {
		tab = new int[width][height];
		for(int x = 0; x < width; x++)
			for(int y = 0; y < height; y++)
				tab[x][y] = Integer.MAX_VALUE;
		
		
		for(int xPoint = 0; xPoint < allPath.length;xPoint++) {
			for(int yPoint = 0; yPoint < allPath[xPoint].length;yPoint++) {
			
				allPath[xPoint][yPoint] = null;
				
			}
		}
	}
	
	public void addLinks(int x,int y,int xd,int yd,int value) {
	}

	
	
	public Link getPath(int x,int y) {
		return null;
	}
	
	
	public void Dijkstra(int x,int y){
	
				
		
	}
	
}
