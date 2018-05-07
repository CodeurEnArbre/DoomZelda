package game.modele.entity;

import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;

public class EntityLiving extends Entity{
	
	protected Direction direction;
	//La direction du regard
	
	public EntityLiving(Coordonnees position, Direction direction) {
		super(position);
		this.direction=direction;
	}
	
	public Direction getOrientation() {
		return this.direction;
	}
	
	public void setDirection(Direction direction) {
		this.direction=direction;
	}
	
}
