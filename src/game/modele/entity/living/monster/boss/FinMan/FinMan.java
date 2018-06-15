package game.modele.entity.living.monster.boss.FinMan;

import game.modele.entity.living.MemberPart;
import game.modele.entity.living.monster.boss.Boss;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;

public class FinMan extends Boss {
	
	public FinMan(Coordonnees position, int pvMax) {
		super("FinMan", position, new Direction(2), pvMax);
		
		MemberPart[] bodyPart = new MemberPart[3];
		bodyPart[0] = new FinManHead(new Coordonnees(position.getX()+2.5, position.getY()+0.5));
		bodyPart[1] = new FinManLeftArm(new Coordonnees(position.getX(), position.getY()+1.6));
		bodyPart[2] = new FinManRightArm(new Coordonnees(position.getX()+4.5, position.getY()+1.7));
		super.bodyPart = bodyPart;
	}

}
