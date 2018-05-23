package game.modele.tile.tileGround;

import game.modele.entity.Entity;

public class tileStone extends TileGround{

	public enum Stone{
		SandStone(15);
		
		private int id; 
		Stone(int id) {
			this.id = id;
		}
		public int get() {
			return id;
		}
	}
	
	public tileStone() {
		super(14);
	}

	public tileStone(Stone s) {
		super(s.id);
	}

	@Override
	public void onEntityOver(Entity e) {
		e.slow = 0.4;
	}

	@Override
	public void leaveEntity(Entity e) {
		e.slow = 1;
	}
	@Override
	public int speedIndice() {
		return 10;
	}
}
