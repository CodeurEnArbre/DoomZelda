package game.controleur.tile;

public class Tile {
	
	public static String[] tileName = {	"air","grass","top left dirt path","top midle dirt path","top right dirt path","","","","","","","","","","","",
										"","middle left dirt path","middle dirt path","middle right dirt path","","","","","","","","","","","","",
										"","","","","","","","","","","","","","","","",
										"top left tree","top right tree","","","","","","","","","","","","","","",
										"middle left tree","middle right tree","","","","","","","","","","","","","","",
										"bottom left tree","bottom right tree","","","","","","","","","","","","","","",
										"","","","","","","","","","","","","","","","",
										"","","","","","","","","","","","","","","","",
										"","","","","","","","","","","","","","","","",
										"","","","","","","","","","","","","","","","",
										"","","","","","","","","","","","","","","","",
										"","","","","","","","","","","","","","","","",
										"","","","","","","","","","","","","","","","",
										"","","","","","","","","","","","","","","","",
										"","","","","","","","","","","","","","","","",
										"","","","","","","","","","","","","","","","",};
	
	private int id;
	
	public Tile(int id) {
		this.id=id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String toString() {
		return "id:"+(id-1)+", name:"+tileName[id-1];
	}
}
