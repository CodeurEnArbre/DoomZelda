package game.modele.utils;

public class Coordonnees {
	private double x,y;
	
	public Coordonnees(double x, double y) {
		this.x=x;
		this.y=y;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public void setCoordoner(double x, double y) {
		this.x=x;
		this.y=y;
	}
}
