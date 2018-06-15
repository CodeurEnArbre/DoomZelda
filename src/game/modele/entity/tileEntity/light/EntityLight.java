package game.modele.entity.tileEntity.light;

import game.modele.entity.Entity;
import game.modele.entity.tileEntity.TileEntity;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import game.modele.utils.ActionConsumer.ConsumerAction;
import game.modele.utils.ActionConsumer.InfiniteActionConsumer;
import game.modele.utils.ActionConsumer.Function.FunctionLampe;
public abstract class EntityLight extends TileEntity{

	ConsumerAction cl = new InfiniteActionConsumer(new FunctionLampe());
	public int lightLvl;
	public EntityLight(String id, Coordonnees coordoner, boolean etat, int lightLvl) {
		super(id, coordoner, etat);
		this.lightLvl = lightLvl;
		addAction(cl);
	}
	
	
	@Override
	public void active(Entity e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void incAnim() {
		// TODO Auto-generated method stub
		
	}
}
