package game;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class InGameMenu {
	
	public static IntegerProperty selectedButtonInGame=new SimpleIntegerProperty(0);
	public static BooleanProperty enterOptions=new SimpleBooleanProperty(false);

	
	public static void Save(String name) {
		//TODO
	}

	public static void selectionDown() {
		if(selectedButtonInGame.get() < 2)
			selectedButtonInGame.set(selectedButtonInGame.get()+1);			
	}
	
	public static void selectionUp() {
		if(selectedButtonInGame.get() > 0)
			selectedButtonInGame.set(selectedButtonInGame.get()-1);			
	}
}
