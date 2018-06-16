package game.modele.utils.ActionConsumer.Function;

import game.modele.entity.Entity;
import game.modele.entity.living.Player;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import game.modele.world.World;

public class FunctionMoveOneTile extends Function{

	Direction orientation;
	Direction direction;
	float speed;
	float distance=0;
	float distancex=0;
	float distancey=0;
	Player thePlayer = World.player;
	Coordonnees startCoordonnees;
	
	public FunctionMoveOneTile(Entity e,Direction orientation, float speed, Direction direction) {
		this.orientation=orientation;
		this.direction=direction;
		this.speed= speed;
		this.startCoordonnees = new Coordonnees(thePlayer.coordonnes.getX(),thePlayer.coordonnes.getY());
	}
	
	@Override
	protected void Action(Entity e) {
		
		if(distance>=1) {
			thePlayer.isTruePushingSomething=false;
			ce.clear();
			e.clearAction();
			
		}else {
			distance+=speed;
			float x = 0;
			float y = 0;
			switch(orientation.getDirection()) {
			case Direction.North:
				if(direction.getDirection() == orientation.getDirection())
					y-=speed;
				else
					y+=speed;
				break;
			case Direction.South:
				if(direction.getDirection() == orientation.getDirection())
					y+=speed;
				else
					y-=speed;
				break;
			case Direction.East:
				if(direction.getDirection() == orientation.getDirection())
					x+=speed;
				else
					x-=speed;
				break;
			case Direction.West:
				if(direction.getDirection() == orientation.getDirection())
					x-=speed;
				else
					x+=speed;
				break;
			}
			distancex+=x;
			distancey+=y;
			e.forceAddX(x);
			e.forceAddY(y);
			thePlayer.coordonnes.setCoordoner(startCoordonnees.getX()+distancex, startCoordonnees.getY()+distancey);
			
		}
	}

}
