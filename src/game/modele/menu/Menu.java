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
	public static final int InventoryMenuID = 100 ;
	
	public static IntegerProperty selectedButtonX = new SimpleIntegerProperty(0);
	public static IntegerProperty selectedButtonY = new SimpleIntegerProperty(0);
	public static IntegerProperty currentMenu = new SimpleIntegerProperty(MainMenuID);
	public static int lastMenu;
	
	public static void inventory() {
		if(Menu.currentMenu.get() == Menu.NoMenuID) {
			selectedButtonX.set(8);
			selectedButtonY.set(2);
			System.out.println(selectedButtonX+" "+selectedButtonY);
			Menu.currentMenu.set(Menu.InventoryMenuID);
			World.pauseGameLoop();
			World.onPause.set(true);
		}else if(Menu.currentMenu.get() == Menu.InventoryMenuID) {
			Menu.currentMenu.set(Menu.NoMenuID);
			World.playGameLoop();
			World.onPause.set(false);
		}
	}
	
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
			
		case InventoryMenuID:
			currentMenu.setValue(NoMenuID);
			World.playGameLoop();
			World.onPause.set(false);
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
		if(currentMenu.get() != NoMenuID && currentMenu.get()<100) {
			switch (currentMenu.get()) {
			case MainMenuID:
				MainMenu.validate();
				break;
			case InGameMenuID:
				InGameMenu.validate();
				break;
			case OptionsMenuID:
				OptionsMenu.validate();
				break;
			case InventoryMenuID:
				InventoryMenu.validate();
				break;
			default:
				
				break;
			}
		}
	}

	public static void selectUp() {
		if(currentMenu.get() != NoMenuID) {
			if(currentMenu.get() == InventoryMenuID){
				InventoryMenu.selectUp();
			}else if(selectedButtonY.get()>0)
				selectedButtonY.set(selectedButtonY.get()-1);
		}
	}
	
	public static void selectDown() {
		if(currentMenu.get() != NoMenuID && currentMenu.get() <100){
			int min=0;
			switch(currentMenu.get()) {
			case MainMenuID:
				min=3;
				break;
			case InGameMenuID:
				min=3;
				break;
			case OptionsMenuID:
				min=6;
				break;
			}
			min--;
			
			if(selectedButtonY.get()<min) {
				selectedButtonY.set(selectedButtonY.get()+1);
			}
		}else if(currentMenu.get() == InventoryMenuID){
			InventoryMenu.selectDown();
		}
	}
	
	public static void selectLeft() {
		if(currentMenu.get() == InventoryMenuID){
			InventoryMenu.selectLeft();
		}
	}
	
	public static void selectRight() {
		if(currentMenu.get() == InventoryMenuID){
			InventoryMenu.selectRight();
		}
	}
}
