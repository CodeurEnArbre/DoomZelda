package game.modele.menu;

import game.modele.item.Item;
import game.modele.world.World;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class InventoryMenu {
	
	
	public static IntegerProperty InventoryZone = new SimpleIntegerProperty(0); //0= consomables, 1 = items, 2 = armes
	public static BooleanProperty newItem = new SimpleBooleanProperty(false);
	public static IntegerProperty lastItemAdded = new SimpleIntegerProperty();

	@SuppressWarnings("unchecked")
	public static void equipItem(boolean isLeft) {
		if(InventoryZone.getValue() == 2) {
			if(Menu.selectedButtonX.get()<8 && Menu.selectedButtonY.get() > 1) {
				int slot = Menu.selectedButtonX.get()+8*(Menu.selectedButtonY.get()-2);
				
				if(isLeft && World.player.weapons[slot] != null) {
					if(World.player.haveLeftItemEquip.get() && World.player.weapons[slot].primaryKey != ((Item)World.player.RightItemEquip).primaryKey)
						World.player.haveRightItemEquip.set(false);
					World.player.LeftItemEquip = (World.player.weapons[slot]);
					
				}else if(World.player.weapons[slot] != null) {
					if(World.player.haveRightItemEquip.get() && World.player.weapons[slot].primaryKey != ((Item)World.player.LeftItemEquip).primaryKey)
						World.player.haveLeftItemEquip.set(false);
					World.player.RightItemEquip = (World.player.weapons [slot]);
					
				}
			}
		}
	}
	
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
			if( Menu.selectedButtonX.get() <= 4 && Menu.selectedButtonY.get() < 2 ) {//To special
				Menu.selectedButtonX.set(0);
				Menu.selectedButtonY.set(0);
			}else {
				Menu.selectedButtonX.set(Menu.selectedButtonX.get()-1);
			}
		}
	}

	public static void selectRight() {
		if(Menu.selectedButtonX.get() < 8) {
			if( Menu.selectedButtonX.get() == 0 && Menu.selectedButtonY.get() < 2 ) {//From gem
				Menu.selectedButtonX.set(4);
			}else if(Menu.selectedButtonY.get() < 2 && Menu.selectedButtonX.get() >= 5){
				Menu.selectedButtonX.set(8);
				Menu.selectedButtonY.set(2);
			}else {
				Menu.selectedButtonX.set(Menu.selectedButtonX.get()+1);
			}
		}
	}

	//Y
	public static void selectUp() {
		if(Menu.selectedButtonY.get()>0) {

			if( Menu.selectedButtonY.get() == 2 ){

				if( Menu.selectedButtonX.get() > 4) {
					Menu.selectedButtonX.set(5);
					Menu.selectedButtonY.set(1);

				}else if( Menu.selectedButtonX.get() <= 3 ) {
					Menu.selectedButtonX.set(0);
					Menu.selectedButtonY.set(0);
				}

			}else
				Menu.selectedButtonY.set(Menu.selectedButtonY.get()-1);

		}
	}
	
	public static void selectDown() {
		if(Menu.selectedButtonY.get()<4) {
			if(Menu.selectedButtonY.get() <= 1 && Menu.selectedButtonX.get() <= 3) {
				Menu.selectedButtonX.set(0);
				Menu.selectedButtonY.set(2);
			}else{
				Menu.selectedButtonY.set(Menu.selectedButtonY.get()+1);
			}
		}
	}
	
}
