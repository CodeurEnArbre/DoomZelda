package game.modele.item;

import game.modele.item.weapon.Weapon;

public class ItemFactory {

	public static Item getItem(String Itemname) {
		Item superItemOfDoomZelda = null;
		switch(Itemname) {
		case "Wooden Sworden":
			superItemOfDoomZelda = new Weapon("Wooden Sworden", 1);
			break;
		case "Basic Axe":
			superItemOfDoomZelda = new Weapon("Basic Axe", 1);
			break;
		}
		return superItemOfDoomZelda;
	}
}
