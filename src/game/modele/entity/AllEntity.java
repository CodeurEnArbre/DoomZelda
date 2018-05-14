package game.modele.entity;

public enum AllEntity {
	Entity_Player(1),
	Entity_Teleporter(2),
	Entity_Zombie(3);
	
	
	private int id;
	private AllEntity(int id) {
		this.id = id;
	}
	public int getId() {
		return this.id;
	}
}
