package game.modele.tile.tileInteract;

import game.modele.entity.Entity;
import game.modele.tile.Tile;

public abstract class tileInteract extends Tile{

	public tileInteract(int id) {
		super(id);
	}

	public void Action(Entity e) {
		System.out.println("appuyer sur enter");
		Show();
	}
	protected abstract void Show();
	
}
