package game.modele.utils;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Coordonnees {
	private DoubleProperty x,y;
	
	public Coordonnees(double x, double y) {
		this.x= new SimpleDoubleProperty(x);
		this.y= new SimpleDoubleProperty(y);
	}
	
	public DoubleProperty getXpro() {
		return this.x;
	}
	
	public double getX() {
		return this.x.doubleValue();
	}
	
	public DoubleProperty getYpro() {
		return this.y;
	}
	
	public double getY() {
		return this.y.doubleValue();
	}
	
	public void setCoordoner(double x, double y) {
		this.x.set(x);
		this.y.set(y);
	}
}
