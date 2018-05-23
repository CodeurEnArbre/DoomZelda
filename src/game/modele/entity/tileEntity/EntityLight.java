package game.modele.entity.tileEntity;

import game.modele.entity.Entity;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import game.modele.world.World;

public abstract class EntityLight extends TileEntity{

	protected int lightLvl;
	private boolean f = true;
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
		if(f)
			World.currentMap.AddTorch((int)this.coordonnes.getX(), (int)this.coordonnes.getY(),8,1);
		else
			World.currentMap.AddTorch((int)this.coordonnes.getX(), (int)this.coordonnes.getY(),-8,-1);
		f = !f;
	}

}
