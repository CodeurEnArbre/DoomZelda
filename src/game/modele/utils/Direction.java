package game.modele.utils;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Direction {
	public static final int North = 0;
	public static final int South = 1;
	public static final int West = 2;
	public static final int East = 3;
	
	private IntegerProperty direction;
	
	public Direction(int value) {
		direction = new SimpleIntegerProperty();
		direction.setValue(value);
	}
	
	public Direction() {
		this(0);
	}
	
	
	public IntegerProperty getDirectionProperty() {
		return this.direction;
	}
	
	public int getDirection() {
		return this.direction.get();
	}
	
	public String toString() {
		switch(this.direction.get()) {
		case 0:
			return "North";
		case 1:
			return "South";
		case 2:
			return "West";
		case 3:
			return "East";
		default:
				return "Direction Invalide";
			
		}
	}
}
