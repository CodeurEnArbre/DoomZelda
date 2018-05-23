package game.modele.utils.graph;

public class Link {

	private Node n;
	private int value;
	public Link(Node n,int value) {
		this.value = value;
		this.n = n;
	}
	
	public Node getNode() {
		return n;
	}

	public int getValue() {
		return value;
	}
	
}
