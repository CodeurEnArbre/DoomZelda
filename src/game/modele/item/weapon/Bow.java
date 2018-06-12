package game.modele.item.weapon;

import game.modele.entity.projectile.Arrow;
import game.modele.entity.projectile.Projectile;
import game.modele.utils.ActionConsumer.InfiniteActionConsumer;
import game.modele.utils.ActionConsumer.Function.FunctionMove;
import game.modele.world.World;

public class Bow extends RangedWeapon{

	public Bow(int pa) {
		super("Bow", pa, "Arrow");
	}

	@Override
	public void attaque() {
		Projectile theProjectile;
		switch(projectile) {
		default :
			theProjectile = new Arrow(World.player.coordonnes, World.player.direction, World.player);
			break;
		}
		theProjectile.addAction(new InfiniteActionConsumer(new FunctionMove()));
		World.currentMap.entity.add(theProjectile);
	}
	
}
