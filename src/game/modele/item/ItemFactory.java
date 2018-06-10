package game.modele.item;

import game.modele.item.weapon.Axe;
import game.modele.item.weapon.Sword;

public class ItemFactory {

	public static Item getItem(String Itemname) {
		
		switch(Itemname) {
		case "null":
			return null;
		case "Wooden Sworden":
			return new Sword("Wooden Sworden", 1);
		case "Basic Axe":
			return new Axe("Basic Axe", 3);
		default:
			return new Item(Itemname);
			
		}
	}
}
