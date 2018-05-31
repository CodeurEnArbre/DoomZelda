package game.modele.entity.tileEntity;

import game.modele.entity.Entity;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import game.modele.world.World;

public class CarriableEntity extends TileEntity{

	
	public CarriableEntity(String id, Coordonnees coordoner, Direction direction) {
		super(id, coordoner, direction, false);
	}

	@Override
	public void active(Entity e) {}

	@Override
	public void incAnim() {}
	
	@Override
	public void onHit(Entity entity) {
		World.currentMap.entity.remove(this);
	}
	
	public boolean pickupEntity(Entity entity) {
		super.etat.set(true);
		World.player.carriedEntity=this;
		World.currentMap.entity.remove(this);
		World.player.isCarriedSomething.set(true);
		return true;
	}
	
	public boolean placeEntity(Entity entity) {
		super.etat.set(false);
		int x = (entity.direction.getDirection()==Direction.South?(int)entity.coordonnes.getX()+1:(int)entity.coordonnes.getX()-1);
		int y = (entity.direction.getDirection()==Direction.North?(int)entity.coordonnes.getY()-1:(int)entity.coordonnes.getY()+1);
		if(World.currentMap.getTileTerrain(x, y) != null) {
			return false;
		}
		super.coordonnes.setCoordoner(x,y);
		World.player.isCarriedSomething.set(false);
		return true;
	}
	
	public void throwEntity(Entity entity) {
		super.etat.set(false);
		
	}
	
}
