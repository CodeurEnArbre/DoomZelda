package game.modele.entity.living.monster;

import game.modele.entity.Entity;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import game.modele.utils.ActionConsumer.ConsumerAction;
import game.modele.utils.ActionConsumer.InfiniteActionConsumer;
import game.modele.utils.ActionConsumer.Function.FunctionIA;
import game.modele.utils.ActionConsumer.Function.FunctionMove;
import game.modele.utils.ActionConsumer.Function.FunctionMovement;

public class Zombie extends EntityMonster{

	ConsumerAction deplacement = new InfiniteActionConsumer(new FunctionMove());
	ConsumerAction mouvement = new InfiniteActionConsumer(new FunctionMovement());
	ConsumerAction ia = new InfiniteActionConsumer(new FunctionIA());
	
	
	public Zombie(Coordonnees c,Direction d, int pv) {
		super("Zombie",c,d, pv);
		this.speed = 0.02;
		this.acce = 0;
		this.baseSpeed = 0.05;
		this.maxSpeed = 0.05;		
		addAction(deplacement);
		addAction(mouvement);
		addAction(ia);
	}

	@Override
	public void dispose() {
		delAction(deplacement);
		delAction(mouvement);
		delAction(ia);
	}
	@Override
	public void active(Entity e) {

	}
	
	@Override
	public void incAnim() {
		this.etatDeplacement.set(
				this.etatDeplacement.get()
				+ (this.etatDeplacement.get() < 83 ? 1 : -83));
	}
	
	
}
