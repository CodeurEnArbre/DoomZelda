package game.modele.entity.tileEntity.light;

import game.modele.entity.Entity;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;

public class TikiTorchSmall extends EntityLight {

	public TikiTorchSmall(Coordonnees coordoner, boolean etat, int lightLvl) {
		super("TikiTorchSmall", coordoner, etat, lightLvl);
	}

	@Override
	public void onHit(Entity entity) {
		super.etat.set(false);
		
	}

}
