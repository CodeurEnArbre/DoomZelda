package game.modele.item.weapon;

public abstract class RangedWeapon extends Weapon{

	
	String projectile;
	
	public RangedWeapon(String name, int pa, String projectile) {
		super(name, pa);
		this.projectile=projectile;
	}

}
