package game.modele.entity.tileEntity;

import game.modele.entity.Entity;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;

public class TikiTorchSmall extends EntityLight {

	public TikiTorchSmall(Coordonnees coordoner, Direction direction, boolean etat, int lightLvl) {
		super("TikiTorchSmall", coordoner, direction, etat, lightLvl);
	}

	@Override
	public void onHit(Entity entity) {
		super.etat.set(false);
		
	}

}
