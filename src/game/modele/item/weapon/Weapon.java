package game.modele.item.weapon;

import game.modele.entity.living.EntityLiving;
import game.modele.item.Item;

public class Weapon extends Item{
	private int pa;
	
	public Weapon(String name, int pa) {
		super(name);
		this.pa = pa;
	}
	
	public int getAttaque() {
		return this.pa;
	}
	
	public void attaquer(EntityLiving e) {
		e.perdrePV(this.pa);
	}	
}
