package game.modele.menu;

import game.modele.world.Save;

public class MainMenu {
	
	public static void validate() {
		switch(Menu.selectedButtonY.get()) {
		case 0 : 
			//World.loadWorld("TinyMap",null);
			Menu.currentMenu.set(Menu.NoMenuID);
			Save.deleteSave("default");
			Save.createSave("default");
			Save.loadSave("default");
			
			break;
		case 1 :
			Menu.lastMenu=Menu.MainMenuID;
			Menu.currentMenu.set(Menu.OptionsMenuID);
			break;
		case 2 : 
			System.exit(0); 
			break;
		}
	}
}
