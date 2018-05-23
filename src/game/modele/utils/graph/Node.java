package game.modele.utils.graph;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;

public class Node {

	public SimpleEntry<Integer,Integer> coord;
	private ArrayList<Link> links;
	public Node(int x,int y) {
		links = new ArrayList<>();
		coord = new SimpleEntry<Integer, Integer>(x, y);
	}

	public void addLink(Link l) {
		links.add(l);
	}
	
	public ArrayList<Link> getLink() {
		return links;
	}
}
