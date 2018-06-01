package game.modele.item;

public class Item {
	
	public String name; //Nom de l'item
	private int quantity; //ca quantite, le nombre de cette item
	public static int key = 0;
	public int primaryKey;
	private boolean stackable; // si l'item est empilable
	
	public Item(String name) {
		this.name=name;
		this.quantity=1;
		this.primaryKey = key++;
		stackable=false;
	}
	
	public Item(String name, int quantity) {
		this.name=name;
		this.quantity=quantity;
		this.primaryKey = key++;
		stackable=true;
	}
	
	public String getItemName() {
		return this.name;
	}
	
	public int getItemQuantity() {
		return this.quantity;
	}
	
	public boolean addItem(int num) {
		if(stackable) {
			this.quantity+=num;
			return true;
		}else
			return false;
	}
	
	//Enleve 1 item, returne true si possible, et false si <=0
	public boolean removeItem() {
		if(this.quantity>0) {
			this.quantity--;
			return true;
		}else
			return false;
	}
	
	public int getPrimaryKey() {
		return this.primaryKey;
	}
	
	public String toString() {
		return primaryKey+" "+name+" "+quantity;
	}
}
