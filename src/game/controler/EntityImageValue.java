package game.controler;

public class EntityImageValue {
	
	public static int getEntityNum(String entityID) {
		switch(entityID) {
		case "Bush":
			return 2;
		case "StoneBlock":
			return 3;
		}
		return 0;
	}
}
