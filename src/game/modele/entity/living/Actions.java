package game.modele.entity.living;

public enum Actions {
	
	walk(0),
	useWeapon(1),
	raise(2),
	walkAndRaise(3),
	place(4),
	push(5);
	
	private int value;
	Actions(int i){
		value = i;
	}
	public int get(){
		return this.value;
	}
}
