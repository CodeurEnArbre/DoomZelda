package game.modele.entity.tileEntity;

import game.modele.entity.Entity;
import game.modele.tile.Tile;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import game.modele.world.World;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class EntityLight extends TileEntity{

	public IntegerProperty lightLvl;
	
	public EntityLight(String id, Coordonnees coordoner, Direction direction, boolean etat, int lightLvl) {
		super(id, coordoner, direction, etat);
		this.lightLvl = new SimpleIntegerProperty(lightLvl);
		
	}

	@Override
	public void update() {
		for(double x=coordonnes.getX()-lightLvl.intValue(); x < World.currentMap.getWidth() && x < coordonnes.getX()+lightLvl.intValue();x++)
			for(double y=coordonnes.getY()-lightLvl.intValue(); y < World.currentMap.getHeight() && y < coordonnes.getY()+lightLvl.intValue();y++)
				World.currentMap.getTile((int)x, (int)y).modifLight(Tile.Max_Light);
	}

	@Override
	public void incAnim() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void active(Entity e) {
		// TODO Auto-generated method stub
		
	}

}
