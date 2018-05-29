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
	
	public void pickupEntity(Entity entity) {
		super.etat.set(true);
		World.player.CarriedEntity=this;
		World.currentMap.entity.remove(this);
	}
	
	public void placeEntity(Entity entity) {
		super.etat.set(false);
		super.coordonnes.setCoordoner((entity.direction.getDirection()==Direction.South?(int)entity.coordonnes.getX()+1:(int)entity.coordonnes.getX()-1),
										(entity.direction.getDirection()==Direction.North?(int)entity.coordonnes.getY()-1:(int)entity.coordonnes.getY()+1));
		
	}
	
	public void throwEntity(Entity entity) {
		super.etat.set(false);
		
	}
	
}
