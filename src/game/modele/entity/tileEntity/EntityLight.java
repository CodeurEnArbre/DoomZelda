package game.modele.entity.tileEntity;

import game.modele.entity.Entity;
import game.modele.tile.Tile;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import game.modele.utils.ActionConsumer.ConsumerAction;
import game.modele.utils.ActionConsumer.CountActionConsumer;
import game.modele.utils.ActionConsumer.FunctionBank;
import game.modele.world.World;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class EntityLight extends TileEntity{

	protected int lightLvl;
	
	public EntityLight(String id, Coordonnees coordoner, Direction direction, boolean etat, int lightLvl) {
		super(id, coordoner, direction, etat);
		this.lightLvl = lightLvl;
		
		
			
			
			
		
	}

	
	
	
	@Override
	public void incAnim() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void active(Entity e) {
		World.currentMap.AddTorch((int)this.coordonnes.getX(), (int)this.coordonnes.getY(),12,1);
	}

}
