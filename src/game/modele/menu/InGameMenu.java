package game.modele.menu;

public class InGameMenu {

	public static void Save(String name) {
		//TODO
	}


	public static void validate() {
		switch(Menu.selectedButton.get()) {
		
		case 0:
			Menu.lastMenu=Menu.InGameMenuID;
			Menu.currentMenu.set(Menu.OptionsMenuID);
			break;
		case 1:
			
			break;
			
		case 2:
			
			break;
		}
		
	}

	


}
