package game.controler;

public class EntityImageValue {
	
	public static int getEntityNum(String entityID) {
		switch(entityID) {
		case "Bush":
			return 2;
		case "Rock":
			return 3;
		case "Gold Chest":
			return 4;
		case "Wood Chest":
			return 5;
		case "Iron Chest":
			return 6;
		}
		return 0;
	}
}
