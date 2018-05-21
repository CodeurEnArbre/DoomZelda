package game.modele.entity.tileEntity;

import game.modele.entity.Entity;
import game.modele.tile.Tile;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import game.modele.world.World;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class EntityLight extends TileEntity{

	public IntegerProperty lightLvl;
	
	public EntityLight(String id, Coordonnees coordoner, Direction direction, boolean etat, int lightLvl) {
		super(id, coordoner, direction, etat);
		this.lightLvl = new SimpleIntegerProperty(lightLvl);
		
	}

	@Override
	public void update() {
		if(etat.get())
		for(double y=coordonnes.getX()-lightLvl.intValue(); y < World.currentMap.getWidth() && y < coordonnes.getX()+lightLvl.intValue();y++)
			for(double x=coordonnes.getY()-lightLvl.intValue(); x < World.currentMap.getHeight() && x < coordonnes.getY()+lightLvl.intValue();x++) {
				World.currentMap.getTileTerrain((int)x, (int)y).setLight(Tile.Max_Light);
				World.currentMap.getTile((int)x, (int)y).setLight(Tile.Max_Light);
				World.currentMap.getTileTop((int)x, (int)y).setLight(Tile.Max_Light);
				this.setCoordoner(new Coordonnees(x,y));
			}
		
	}

	@Override
	public void incAnim() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void active(Entity e) {
		super.setEtat(!etat.get());
		if(!etat.get()) {
			for(double y=coordonnes.getX()-lightLvl.intValue(); y < World.currentMap.getWidth() && y < coordonnes.getX()+lightLvl.intValue();y++)
				for(double x=coordonnes.getY()-lightLvl.intValue(); x < World.currentMap.getHeight() && x < coordonnes.getY()+lightLvl.intValue();x++) {
					World.currentMap.getTileTerrain((int)x, (int)y).setLight(Tile.Min_Light);
					World.currentMap.getTile((int)x, (int)y).setLight(Tile.Min_Light);
					World.currentMap.getTileTop((int)x, (int)y).setLight(Tile.Min_Light);
					this.setCoordoner(new Coordonnees(x,y));
				}
		}
		
	}

}
