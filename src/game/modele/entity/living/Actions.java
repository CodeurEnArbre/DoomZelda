package game.modele.entity.living;

public enum Actions {
	
	walk(0),
	useLeftItem(1),
	useRightItem(2),
	raise(3),
	walkAndRaise(4),
	place(5),
	push(6);
	
	private int value;
	Actions(int i){
		value = i;
	}
	public int get(){
		return this.value;
	}
}
