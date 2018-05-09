package game.modele.entity;

import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class EntityLiving extends Entity{
	
	protected Direction direction;
	protected IntegerProperty PV;
	protected IntegerProperty maxPv;
	//La direction du regard

	
	
	public EntityLiving(Coordonnees position, Direction direction) {
		super(position);
		this.direction=direction;
		PV=new SimpleIntegerProperty(12);
		maxPv=new SimpleIntegerProperty(12);
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
		if(PV.getValue()>0)
			PV.set(PV.get()-1);
	}
	
	public void gagnerPV() {
		if(PV.getValue()<maxPv.getValue())
			PV.set(PV.get()+1);
	}
	
	public IntegerProperty getPV() {
		return PV;
	}
	
	
	public void addHeart() {
		this.maxPv.add(4);
	}
	
	public IntegerProperty getMaxPv() {
		return this.maxPv;
	}
	
	
	
}
