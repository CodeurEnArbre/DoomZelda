package game.modele.item;

import game.modele.item.weapon.Weapon;

public class ItemFactory {

	public static Item getItem(String Itemname) {
		
		switch(Itemname) {
			
		case "Wooden Sworden":
			return new Weapon("Wooden Sworden", 1);
		case "Basic Axe":
			return new Weapon("Basic Axe", 1);
		default:
			return new Item(Itemname);
			
		}
	}
}
