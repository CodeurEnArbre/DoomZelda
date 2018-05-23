package game.modele.utils.graph;

import java.util.ArrayList;

public class Node {

	private ArrayList<Link> links;
	public Node() {
		links = new ArrayList<>();
	}

	public void addLink(Link l) {
		links.add(l);
	}
	
	public ArrayList<Link> getLink() {
		return links;
	}
}
