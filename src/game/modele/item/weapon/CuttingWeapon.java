package game.modele.item.weapon;

import game.modele.entity.Entity;
import game.modele.entity.living.EntityLiving;
import game.modele.entity.living.Player;
import game.modele.utils.Direction;
import game.modele.world.World;

public class CuttingWeapon extends Weapon{

	public CuttingWeapon(String name, int pa) {
		super(name, pa);
	}

	@Override
	public void attaque() {
		Player player = World.player;
		int dir = player.direction.getDirection();
		int y = (dir==Direction.West?(int)player.coordonnes.getX()-1:dir==Direction.East?(int)player.coordonnes.getX()+1:(int)player.coordonnes.getX());
		int x = (dir==Direction.South?(int)player.coordonnes.getY()+1:dir==Direction.North?(int)player.coordonnes.getY()-1:(int)player.coordonnes.getY());
		for(Entity entity:World.currentMap.entityOnTileHere(x, y)){
			if(entity instanceof EntityLiving && entity != player) {
				((EntityLiving) entity).perdrePV(super.getAttackDamage());
			}
		}
		
	}

}
