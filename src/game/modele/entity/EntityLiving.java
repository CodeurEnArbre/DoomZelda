package game.modele.entity;

import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class EntityLiving extends Entity{
	
	protected Direction direction;
	private IntegerProperty PV;
	//La direction du regard
	
	public EntityLiving(Coordonnees position, Direction direction) {
		super(position);
		this.direction=direction;
		PV=new SimpleIntegerProperty(12);
	}
	
	public EntityLiving(Coordonnees position, Direction direction, int pv) {
		super(position);
		this.direction=direction;
		PV.set(pv);
	}
	
	public Direction getOrientation() {
		return this.direction;
	}
	
	public void setDirection(Direction direction) {
		this.direction=direction;
	}
	
	public void perdrePV() {
		PV.set(PV.get()-1);
	}
	
	public void gagnerPV() {
		PV.set(PV.get()+1);
	}
	
	public IntegerProperty getPV() {
		return PV;
	}
}
