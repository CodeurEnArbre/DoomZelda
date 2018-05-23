package game.modele.utils.graph;

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
	
	
	
	
	
	public void print() {
		for(Node[] nn : tab)
			for(Node n : nn) {
				System.out.println(n);
				for(Link l : n.getLink()) {
					System.out.println(l.getValue());
					System.out.println(l.getNode());
				}
			}
	}
	
}
