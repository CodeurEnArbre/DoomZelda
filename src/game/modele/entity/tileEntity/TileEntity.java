package game.modele.entity.tileEntity;

import game.modele.entity.Entity;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public abstract class TileEntity extends Entity {
	protected BooleanProperty etat;
	
	public TileEntity(String id, Coordonnees coordoner,Direction direction, boolean etat) {
		super(id,coordoner,direction);
		this.etat=new SimpleBooleanProperty(etat);
	}	
	
	public boolean getEtat() {
		return this.etat.get();
	}
	
	public BooleanProperty getEtatProperty() {
		return this.etat;
	}
	
	public void setEtat(boolean etat) {
		this.etat.set(etat);
	}
	
	public abstract void onHit(Entity entity);
}
