package game.modele.menu;

import game.modele.world.World;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Menu {
	
	//Menus id
	public static final int NoMenuID = -1;
	public static final int MainMenuID = 0;
	public static final int InGameMenuID = 1 ;
	public static final int OptionsMenuID = 2 ;
	
	public static IntegerProperty selectedButton = new SimpleIntegerProperty(0);
	public static IntegerProperty currentMenu = new SimpleIntegerProperty(MainMenuID);
	public static int lastMenu;
	
	public static void escape() {
		
		switch (currentMenu.get()) {
		case InGameMenuID:
			currentMenu.setValue(NoMenuID);
			World.playGameLoop();
			World.onPause.set(false);
			lastMenu=InGameMenuID;
			break;
			
		case OptionsMenuID:
			currentMenu.set(lastMenu);
			lastMenu=OptionsMenuID;
			break;	
			
		default:
			if(World.isWorldLoaded.get()) {
				if(!World.onPause.get()) {
					World.pauseGameLoop();
					World.onPause.set(true);
					currentMenu.set(InGameMenuID);
				}
				
			}
			break;
			
		}
	}
	
	public static void validate() {
		if(currentMenu.get() != NoMenuID) {
			switch (currentMenu.get()) {
			case MainMenuID:
				MainMenu.validate();
				break;
			case InGameMenuID:
				InGameMenu.validate();
				break;
			case OptionsMenuID:
				Options.validate();
				break;
			default:
				
				break;
			}
		}
	}
	
	public static void selectUp() {
		if(currentMenu.get() != NoMenuID) {
			if(selectedButton.get()>0)
				selectedButton.set(selectedButton.get()-1);
		}
	}
	
	public static void selectDown() {
		if(currentMenu.get() != NoMenuID) {
			int min=0;
			switch(currentMenu.get()) {
			case MainMenuID:
				min=3;
				break;
			case InGameMenuID:
				min=3;
				break;
			case OptionsMenuID:
				min=5;
				break;
			}
			min--;
			
			if(selectedButton.get()<min) {
				selectedButton.set(selectedButton.get()+1);
			}
		}
	}
}
