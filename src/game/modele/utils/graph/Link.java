package game.modele.utils.graph;

public class Link {

	private Node arrive;
	private Node depart; 
	private int value;
	public Link(Node depart,Node arrive,int value) {
		this.value = value;
		this.depart = depart;
		this.arrive = arrive;
	}
	
	public Node getNodeArrive() {
		return arrive;
	}
	
	public Node getNodeDepart() {
		return depart;
	}
	
	public int getValue() {
		return value;
	}
	
}
