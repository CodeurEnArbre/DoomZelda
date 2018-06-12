package game.modele.entity.tileEntity.carriable;

import game.modele.entity.Entity;
import game.modele.entity.living.Actions;
import game.modele.entity.tileEntity.TileEntity;
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
		World.player.action.set(Actions.raise.get());
		World.player.isCarriedSomething.set(true);
		return true;
	}

	public boolean placeEntity(Entity entity) {
		super.etat.set(false);
		int dir = entity.direction.getDirection();
		int y = (dir==Direction.West?(int)entity.coordonnes.getX()-1:dir==Direction.East?(int)entity.coordonnes.getX()+1:(int)entity.coordonnes.getX());
		int x = (dir==Direction.South?(int)entity.coordonnes.getY()+1:dir==Direction.North?(int)entity.coordonnes.getY()-1:(int)entity.coordonnes.getY());
		if(World.currentMap.getTile(x, y).getId() != 0 || World.currentMap.getTile(x, y) == null) {
			return false;
		}
		super.coordonnes.setCoordoner(y,x);
		this.primaryKey=key++;
		World.currentMap.entity.add(this);
		World.player.isCarriedSomething.set(false);
		World.player.carriedEntity=null;
		World.player.action.set(Actions.walk.get());
		return true;
	}

	public void throwEntity(Entity entity) {
		super.etat.set(false);

	}

}
