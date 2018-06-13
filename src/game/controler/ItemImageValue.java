package game.controler;

public class ItemImageValue {
	public static int getValue(String name) {
		switch(name) {
		
		case "GreenRupee":
			return 7;
		case "BlueRupee":
			return 8;
		case "RedRupee":
			return 9;
		case "PurpleRupee":
			return 10;
		case "GoldRupee":
			return 11;
		case "SilverRupee":
			return 12;
		case "Rupoor"://Roupie
			return 13;
		case "PieceOfHeart":
			return 14;
		case "Heart":
			return 15;
		case "Wooden Sworden":
			return 17;
		case "Basic Axe":
			return 18;
		case "Bow":
			return 19;
		}
		return 0;
	}
}
