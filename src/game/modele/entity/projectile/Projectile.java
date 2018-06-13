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
	
	public void setMouvementDirection(Direction direction) {
		super.moveDown.active = false;
		super.moveDown.active = false;
		super.moveDown.active = false;
		super.moveDown.active = false;
		switch(direction.getDirection()) {
		case Direction.North:
			super.moveUP.active = true;
			break;
		case Direction.South:
			super.moveDown.active = true;
			break;
		case Direction.East:
			super.moveRight.active = true;
			break;
		case Direction.West:
			super.moveLeft.active = true;
			break;
		}
	}

}
