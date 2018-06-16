package game.modele.item.weapon;

import game.modele.entity.Entity;
import game.modele.entity.projectile.Arrow;
import game.modele.entity.projectile.Projectile;
import game.modele.utils.Coordonnees;
import game.modele.utils.ActionConsumer.CountActionConsumer;
import game.modele.utils.ActionConsumer.InfiniteActionConsumer;
import game.modele.utils.ActionConsumer.Function.Function;
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
			theProjectile = new Arrow(new Coordonnees(World.player.coordonnes.getX()-0.5f,World.player.coordonnes.getY()-0.5f), World.player.direction, World.player);
			break;
		}
		World.addEntity(theProjectile);
		theProjectile.addAction(new InfiniteActionConsumer(new FunctionMove()));
		theProjectile.addAction(new CountActionConsumer(30,new Function() {
			@Override
			protected void Action(Entity e) {}			
			@Override
			public void finishAction(Entity e) {
				theProjectile.delete();
			}	
		}));
		theProjectile.setMouvementDirection(World.player.direction);
		
	}
	
}
