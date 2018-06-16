package game.modele.utils;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;

public class Coordonnees {
	private FloatProperty x,y;

	public Coordonnees(float x, float y) {
		this.x = new SimpleFloatProperty(x);
		this.y = new SimpleFloatProperty(y);
	}

	public FloatProperty getXpro() {
		return this.x;
	}

	public float getX() {
		return this.x.floatValue();
	}
	public Coordonnees setX(float x) {
		this.x.set(x);
		return this;
	}

	public FloatProperty getYpro() {
		return this.y;
	}

	public float getY() {
		return this.y.floatValue();
	}

	public Coordonnees setY(float y) {
		this.y.set(y);
		return this;
	}

	public void setCoordoner(float x, float y) {
		this.x.set(x);
		this.y.set(y);
	}

	public boolean isSameTile(float x,float y) {
		return (int)this.x.get() == (int)x && 
				(int)this.y.get() == (int)y;
	}

	public float[] vector(Coordonnees c) {
		return this.vector(c.getX(),c.getY());
	}
	public float[] vector(float x,float y){
		return new float[] {x - this.getX(),this.getY() - y};
	}

	public String toString() {
		return ("x:"+this.x.floatValue()+", y:"+this.y.floatValue());
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
		float dx = (c.getX() - this.getX());
		float dy = (c.getY() - this.getY());
		dx *= dx;
		dy *= dy;
		return (int)Math.sqrt(dx + dy);
	}
}
