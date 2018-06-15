package game.modele.entity.living;

import game.modele.entity.Entity;
import game.modele.utils.Coordonnees;

public abstract class MemberPart extends Entity{

	public MemberPart(String id, Coordonnees coordonnees) {
		super(id, coordonnees);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void active(Entity e) {}

	@Override
	public void incAnim() {}

}
