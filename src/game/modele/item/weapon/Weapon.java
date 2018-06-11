package game.modele.item.weapon;

import game.modele.item.Item;

public abstract class Weapon extends Item{
	private int pa;
	
	public Weapon(String name, int pa) {
		super(name);
		this.pa = pa;
	}
	
	public int getAttackDamage() {
		return this.pa;
	}
	
	public abstract void attaque();
}
