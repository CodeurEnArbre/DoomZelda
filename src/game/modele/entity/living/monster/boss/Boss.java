package game.modele.entity.living.monster.boss;

import game.modele.entity.living.MemberPart;
import game.modele.entity.living.monster.EntityMonster;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;

public class Boss extends EntityMonster{
	
	public MemberPart[] bodyPart;

	public Boss(String id, Coordonnees position, Direction direction, int pvMax) {
		super(id, position, direction, pvMax);
		super.maxPv.set(pvMax);
	}

}
