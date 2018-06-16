package game.modele.utils.ActionConsumer.Function;

import game.modele.entity.Entity;
import game.modele.entity.living.Player;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import game.modele.world.World;

public class FunctionMoveOneTile extends Function{

	Direction orientation;
	Direction direction;
	final float speed;
	float distance=0;
	float distancex=0;
	float distancey=0;
	Player thePlayer = World.player;
	Coordonnees startCoordonnees;
	float[] offSet;
	
	public FunctionMoveOneTile(Entity e,Direction orientation, float speed, Direction direction) {
		this.orientation = orientation;
		this.direction = direction;
		this.speed = speed;
		this.startCoordonnees = new Coordonnees(thePlayer.coordonnes.getX(),thePlayer.coordonnes.getY());
		this.offSet = thing(0.98f,0.48f,0.48f,0.48f,direction);
		e.coordonnes.getXpro().bind(thePlayer.coordonnes.getXpro().add(offSet[0]));
		e.coordonnes.getYpro().bind(thePlayer.coordonnes.getYpro().add(offSet[1]-0.5f));
	}
	
	@Override
	protected void Action(Entity e) {
		
		if(distance>=1) {
			e.coordonnes.getXpro().unbind();
			e.coordonnes.getYpro().unbind();
			e.coordonnes.setCoordoner((int)e.coordonnes.getX(),(int)e.coordonnes.getY());
			thePlayer.isTruePushingSomething=false;
			ce.clear();
			e.clearAction();
			
		}else {
			distance+=speed;
			float[] thing = thing(speed,speed,speed,speed,direction);
			distancex+=thing[0];
			distancey+=thing[1];
			//e.forceAddX(x);
			//e.forceAddY(y);
		//	e.coordonnes.setCoordoner(startCoordonnees.getX()+distancex+offSet[0], startCoordonnees.getY()+distancey+offSet[1]);
			thePlayer.coordonnes.setCoordoner(startCoordonnees.getX()+distancex, startCoordonnees.getY()+distancey);
			
		}
	}
	
	private float[] thing(float speedx, float speedy, float iSpeedx, float iSpeedy, Direction direction) {
		float x=0,y=0;
		switch(orientation.getDirection()) {
		case Direction.North:
			if(direction.getDirection() == orientation.getDirection())
				y-=speedy;
			else
				y+=iSpeedy;
			break;
		case Direction.South:
			if(direction.getDirection() == orientation.getDirection())
				y+=speedy;
			else
				y-=iSpeedy;
			break;
		case Direction.East:
			if(direction.getDirection() == orientation.getDirection())
				x+=speedx;
			else
				x-=iSpeedx;
			break;
		case Direction.West:
			if(direction.getDirection() == orientation.getDirection())
				x-=speedx;
			else
				x+=iSpeedx;
			break;
		}
		float[] directionAdd = {x,y};
		System.out.println(speedx+" "+speedy+" / "+directionAdd[0]+" "+directionAdd[1]);
		return  directionAdd;
	}

}
