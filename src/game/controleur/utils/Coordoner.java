package game.controleur.utils;

public class Coordoner {
	private long x,y;
	
	public Coordoner(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public long getX() {
		return this.x;
	}
	
	public long getY() {
		return this.y;
	}
	
	public void setCoordoner(int x, int y) {
		this.x=x;
		this.y=y;
	}
}
