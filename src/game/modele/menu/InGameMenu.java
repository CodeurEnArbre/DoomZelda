package game.modele.menu;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class InGameMenu {
	
	
	public static IntegerProperty selecterInOption=new SimpleIntegerProperty(0);

	
	public static void Save(String name) {
		//TODO
	}


	public static void validate() {
		Menu.selectedButton.set(0);
		switch(Menu.selectedButton.get()) {
		case 1:
			
			break;
		case 2:
			
			break;
			
		case 3:
			
			break;
		}
		
	}

	


}
