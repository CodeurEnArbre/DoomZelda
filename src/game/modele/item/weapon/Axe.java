package game.modele.item.weapon;

import game.modele.entity.Entity;
import game.modele.entity.living.Player;
import game.modele.entity.living.monster.EntityMonster;
import game.modele.utils.Direction;
import game.modele.world.World;

public class Axe extends Weapon{

	public Axe(String name, int pa) {
		super(name, pa);
		
	}

	@Override
	public void attaque(Player player) {
		int dir = player.direction.getDirection();
		int y = (dir==Direction.West?(int)player.coordonnes.getX()-1:dir==Direction.East?(int)player.coordonnes.getX()+1:(int)player.coordonnes.getX());
		int x = (dir==Direction.South?(int)player.coordonnes.getY()+1:dir==Direction.North?(int)player.coordonnes.getY()-1:(int)player.coordonnes.getY());
		
		for(Entity entity:World.currentMap.entityHere(x, y)){
			if(entity instanceof EntityMonster) {
				((EntityMonster) entity).perdrePV(super.getAttackDamage());
			}
		}
	}
	
}
