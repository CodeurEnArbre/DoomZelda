package game.modele.menu;

import game.modele.world.World;

public class InGameMenu {

	public static void Save(String name) {
		//TODO
	}


	public static void validate() {
		switch(Menu.selectedButtonY.get()) {
		
		case 0:
			Menu.lastMenu=Menu.InGameMenuID;
			Menu.currentMenu.set(Menu.OptionsMenuID);
			break;
		case 1:
			
			break;
			
		case 2:
			World.isWorldLoaded.set(false);
			break;
		}
		
	}

	


}
