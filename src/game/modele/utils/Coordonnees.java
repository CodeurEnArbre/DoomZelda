package game.modele.utils;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Coordonnees {
	private DoubleProperty x,y;
	
	public Coordonnees(double x, double y) {
		this.x = new SimpleDoubleProperty(x);
		this.y = new SimpleDoubleProperty(y);
	}
	
	public DoubleProperty getXpro() {
		return this.x;
	}
	
	public double getX() {
		return this.x.doubleValue();
	}
	public Coordonnees setX(double x) {
		this.x.set(x);
		return this;
	}
	
	public DoubleProperty getYpro() {
		return this.y;
	}
	
	public double getY() {
		return this.y.doubleValue();
	}
	
	public Coordonnees setY(double y) {
		this.y.set(y);
		return this;
	}
	
	public void setCoordoner(double x, double y) {
		this.x.set(x);
		this.y.set(y);
	}
	
	public boolean isSameTile(double x,double y) {
		return (int)this.x.get() == (int)x && 
				(int)this.y.get() == (int)y;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Coordonnees) {
			Coordonnees c = ((Coordonnees)obj);
			return (int)c.getX() == (int)this.getX()
					&& (int)c.getY() == (int)this.getY();
		}else
			return false;
	}
	
	public int distance(Coordonnees c) {
		double dx = (c.getX() - this.getX());
		double dy = (c.getY() - this.getY());
		dx *= dx;
		dy *= dy;
		return (int)Math.sqrt(dx + dy);
	}
	
	
	public String toString() {
		return ("x:"+this.x.doubleValue()+", y:"+this.y.doubleValue());
	}
}
