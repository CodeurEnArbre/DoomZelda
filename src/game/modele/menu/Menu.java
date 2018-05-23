package game.modele.menu;

import game.modele.world.World;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Menu {
	
	public static IntegerProperty selectedButton=new SimpleIntegerProperty(0);
	public static StringProperty currentMenu = new SimpleStringProperty("Main");
	public static String lastMenu;
	
	public static void back() {
		switch (currentMenu.get()) {
		case "Main":
			//Nothing to do
			break;
		case "InGame":
			lastMenu=null;
			currentMenu.setValue(null);
			break;
		case "Options":
			currentMenu.set(lastMenu);
			lastMenu=null;
			break;		
		default:
			if(World.isWorldLoaded.get())
				currentMenu.set("InGame");
			break;
		}
	}
	
	public static void validate() {
		switch (currentMenu.get()) {
		case "Main":
			MainMenu.validate();
			break;
		case "inGame":
			InGameMenu.validate();
			break;
		case "options":
			Options.validate();
			break;
		}
	}
	
	public static void selectUp() {
		if(selectedButton.get()>0)
			selectedButton.add(1);
	}
	
	public static void selectDown() {
		int min=0;
		switch(currentMenu.get()) {
		case "Main":
			min=3;
			break;
		case "inGame":
			min=3;
			break;
		case "options":
			min=5;
			break;
		}
		
		if(selectedButton.get()<min)
			selectedButton.subtract(1);
	}
	
}
