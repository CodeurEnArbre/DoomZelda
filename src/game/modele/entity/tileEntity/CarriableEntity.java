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
	public void active(Entity e) {
	}

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
		int dir = entity.direction.getDirection();
		int x = (dir==Direction.West?(int)entity.coordonnes.getX()-1:dir==Direction.East?(int)entity.coordonnes.getX()+1:(int)entity.coordonnes.getX());
		int y = (dir==Direction.South?(int)entity.coordonnes.getY()+1:dir==Direction.North?(int)entity.coordonnes.getY()-1:(int)entity.coordonnes.getY());
		if(World.currentMap.getTile(x, y).getId() != 0) {
			return false;
		}
		System.out.println(World.currentMap.getTile(x, y).getId());
		super.coordonnes.setCoordoner(x,y);
		World.currentMap.entity.add(this);
		World.player.isCarriedSomething.set(false);
		World.player.carriedEntity=null;
		return true;
	}
	
	public void throwEntity(Entity entity) {
		super.etat.set(false);
		
	}
	
}
