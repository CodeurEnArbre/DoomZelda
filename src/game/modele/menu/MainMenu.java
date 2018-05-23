package game.modele.menu;

import game.modele.world.World;

public class MainMenu {
	
	public static void validate() {
		Menu.selectedButton.set(0);
		switch(Menu.selectedButton.get()) {
		case 0 : 
			World.loadWorld("TinyMap",null);
			break;
		case 1 :
			Menu.lastMenu="Main";
			Menu.currentMenu.set("Options");
			break;
		case 2 : 
			System.exit(0); 
			break;
		}
	}
}
