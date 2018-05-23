package game.modele.menu;

import game.modele.world.World;

public class MainMenu {
	
	public static void validate() {
		switch(Menu.selectedButton.get()) {
		case 0 : 
			World.loadWorld("TinyMap",null);
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
