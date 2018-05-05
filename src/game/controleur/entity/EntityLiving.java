package game.controleur.entity;

import game.controleur.utils.Coordoner;
import game.controleur.utils.Orientation.Direction;

public class EntityLiving extends Entity{
	
	private Direction direction;//La direction auquel regarde l'entitee
	
	public EntityLiving(Coordoner position, Direction direction) {
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
