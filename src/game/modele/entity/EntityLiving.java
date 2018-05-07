package game.modele.entity;

import game.modele.utils.Coordonnees;
import game.modele.utils.Orientation.Direction;

public class EntityLiving extends Entity{
	
	private Direction direction;//La direction auquel regarde l'entitee
	
	public EntityLiving(Coordonnees position, Direction direction) {
		super(position);
		this.direction=direction;
	}
	
	public Direction getDirection() {
		return this.direction;
	}
	
	public void setDirection(Direction direction) {
		this.direction=direction;
	}
	
}
