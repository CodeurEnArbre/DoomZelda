package game.modele.item;

import game.modele.item.weapon.Bow;
import game.modele.item.weapon.CuttingWeapon;

public class ItemFactory {

	public static Item getItem(String Itemname) {
		
		switch(Itemname) {
		case "null":
			return null;
		case "Wooden Sworden":
			return new CuttingWeapon("Wooden Sworden", 1);
		case "Basic Axe":
			return new CuttingWeapon("Basic Axe", 3);
		case "Bow":
			return new Bow(1);
		default:
			return new Item(Itemname);
			
		}
	}
}
