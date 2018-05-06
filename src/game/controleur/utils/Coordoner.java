package game.controleur.utils;

public class Coordoner {
	private double x,y;
	
	public Coordoner(double x, double y) {
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
