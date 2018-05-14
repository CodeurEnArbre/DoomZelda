package game.modele.entity.living;

import game.modele.entity.Entity;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class EntityLiving extends Entity{
	
	//La direction du regard
	protected Direction direction;
	protected IntegerProperty PV;
	protected IntegerProperty maxPv;	
	
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
	
	public void addPermanentHeart() {
		this.maxPv.add(4);
	}
	
	public IntegerProperty getMaxPv() {
		return this.maxPv;
	}
	
	
	//deplacement
	
	public BooleanProperty touche(Entity e) {
		BooleanProperty result = new SimpleBooleanProperty();
		if(this.coordonnes.getX() - e.coordonnes.getX() <= this.hitBoxX && this.coordonnes.getY() - e.coordonnes.getY() <= this.hitBoxY)
			result.setValue(true);
		else
			result.setValue(false);

		return result;
	}
}
