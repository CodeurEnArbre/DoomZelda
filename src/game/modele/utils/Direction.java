package game.modele.utils;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Direction {
	public static final int North = 0;
	public static final int South = 1;
	public static final int East = 2;
	public static final int West = 3;
	
	private IntegerProperty direction;
	
	public Direction(int value) {
		direction = new SimpleIntegerProperty();
		direction.setValue(value);
	}
	
	public IntegerProperty getDirectionProperty() {
		return this.direction;
	}
	
	public int getDirection() {
		return this.direction.get();
	}
}
