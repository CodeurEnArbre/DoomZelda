package game.modele.tile.tileInteract;

import game.modele.entity.Entity;

public class tileBed extends tileInteract {

	public enum Bed{
		Bed_Bottom(122);
		
		private int id; 
		Bed(int id) {
			this.id = id;
		}
		public int get() {
			return id;
		}
	}
	
	public tileBed() {
		super(106);
	}
	
	public tileBed(Bed b) {
		super(b.id);
	}
	
	

	@Override
	protected void Show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEntityOver(Entity e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void leaveEntity(Entity e) {
		// TODO Auto-generated method stub
		
	}

}
