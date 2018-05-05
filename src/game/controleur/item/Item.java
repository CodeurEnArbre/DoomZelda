package game.controleur.item;

public class Item {
	
	private String name; //Nom de l'item
	private int quantity; //ca quantite, le nombre de cette item
	
	public Item(String name) {
		this.name=name;
		this.quantity=1;
	}
	
	public Item(String name, int quantity) {
		this.name=name;
		this.quantity=quantity;
	}
	
	public String getItemName() {
		return this.name;
	}
	
	public int getItemQuantity() {
		return this.quantity;
	}
	
	public void addItem(int num) {
		this.quantity+=num;
	}
	
	//Enleve 1 item, returne true si possible, et false si <=0
	public boolean removeItem() {
		if(this.quantity>0) {
			this.quantity--;
			return true;
		}else
			return false;
	}
}
