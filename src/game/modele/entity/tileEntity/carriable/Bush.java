package game.modele.entity.tileEntity.carriable;

import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;

public class Bush extends CarriableEntity{

	public Bush(Coordonnees coordoner) {
		super("Bush", coordoner);
		super.isSolidEntity=true;
	}

}
