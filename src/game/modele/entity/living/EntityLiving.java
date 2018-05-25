package game.modele.entity.living;

import game.modele.entity.Entity;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class EntityLiving extends Entity{
	
	//La direction du regard
	protected IntegerProperty PV;
	protected IntegerProperty maxPv;	
	
	public EntityLiving(String id,Coordonnees position, Direction direction) {
		super(id,position,direction);
		
		PV=new SimpleIntegerProperty(12);
		maxPv=new SimpleIntegerProperty(12);
	}
	
	public EntityLiving(String id,Coordonnees position, Direction direction, int pv) {
		super(id,position,direction);
		this.direction=direction;
		PV.set(pv);
	}
	
	public void setDirection(Direction direction) {
		this.direction=direction;
	}
	
	public void perdrePV(int degats) {
		if(PV.getValue() > degats)
			PV.set(PV.get() - degats);
		else
			PV.set(0);
	}
	
	public void gagnerPV() {
		if(PV.getValue()<maxPv.getValue())
			PV.set(PV.get()+1);
	}
	
	public IntegerProperty getPV() {
		return PV;
	}
	
	public void addPermanentHeart() {
		this.maxPv.add(4);
	}
	
	public IntegerProperty getMaxPv() {
		return this.maxPv;
	}
	
	//deplacement
}
