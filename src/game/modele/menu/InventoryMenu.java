package game.modele.menu;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class InventoryMenu {
	
	
	public static IntegerProperty InventoryZone = new SimpleIntegerProperty(0); // 0=SelectionType, 1=ItemsZone, 2=special

	public static void validate() {
		if(Menu.selectedButtonX.get()==8)
			InventoryZone.set(Menu.selectedButtonY.get()-2);
		else if(InventoryZone.get() == 0){
			//Use Consomable
		}
	}
	
	//X
	public static void selectLeft() {
		if(Menu.selectedButtonX.get()>0) {
			if( Menu.selectedButtonX.get() == 4 && Menu.selectedButtonY.get() < 2 ) {//To special
				Menu.selectedButtonX.set(0);
				Menu.selectedButtonY.set(0);
			}else {
				Menu.selectedButtonX.set(Menu.selectedButtonX.get()+1);
			}
		}
	}

	public static void selectRight() {
		if(Menu.selectedButtonX.get() < 8) {
			if( Menu.selectedButtonX.get() == 0 && Menu.selectedButtonY.get() < 2 ) {//From gem
				Menu.selectedButtonX.set(4);
			}else {
				Menu.selectedButtonX.set(Menu.selectedButtonX.get()-1);
			}
		}
	}

	//Y
	public static void selectUp() {
		if(Menu.selectedButtonY.get()>0) {
			if(Menu.selectedButtonY.get() == 2) {
				if(Menu.selectedButtonX.get() <= 3) {
					Menu.selectedButtonX.set(0);
					Menu.selectedButtonY.set(0);
				}else {
					Menu.selectedButtonX.set(5);
					Menu.selectedButtonY.set(1);
				}
			}else {
				Menu.selectedButtonY.set(Menu.selectedButtonY.get()+1);
			}
		}
	}
	
	public static void selectDown() {
		if(Menu.selectedButtonY.get()<4) {
			if(Menu.selectedButtonY.get() == 0) {
				Menu.selectedButtonX.set(0);
				Menu.selectedButtonY.set(2);
			}else{
				Menu.selectedButtonY.set(Menu.selectedButtonY.get()-1);
			}
		}
	}
	
}
