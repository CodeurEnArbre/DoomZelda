package game.modele.entity.projectile;

import game.modele.entity.Entity;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;

public abstract class Projectile extends Entity{

	Entity shooter;
	int damage;
	
	public Projectile(String id, Coordonnees coordonnees, Direction direction, Entity shooter, int damage) {
		super(id, coordonnees, direction);
		this.shooter=shooter;
		this.damage=damage;
	}
	

}
