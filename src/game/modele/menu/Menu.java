package game.modele.menu;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Menu {
	
	public static IntegerProperty selectedButton=new SimpleIntegerProperty(0);
	public static String currentMenu = "Main";
	
	public static void enter() {
		switch (currentMenu) {
		case "Main":
			
			break;
		case "inGame":
			
			break;
		case "options":
			
			break;
		}
	}
	
	public static void selectUp() {
		if(selectedButton.get()>0)
			selectedButton.add(1);
	}
	
	public static void selectDown() {
		int min=0;
		switch(currentMenu) {
		case "Main":
			min=3;
			break;
		case "inGame":
			min=3;
			break;
		case "options":
			min=4;
			break;
		}
		if(selectedButton.get()<min)
			selectedButton.subtract(1);
	}
	
}
