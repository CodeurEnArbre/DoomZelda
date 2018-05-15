package game.modele.tile.tileInteract;

import game.modele.entity.Entity;

public class tileSign extends tileInteract{

	public enum Sign{
		Sign_Direction(43);
	
		
		private int id; 
		Sign(int id) {
			this.id = id;
		}
		public int get() {
			return id;
		}
	}
	
	
	public tileSign() {
		super(44);
	}

	public tileSign(Sign s) {
		super(s.id);
	}
	
	
	@Override
	protected void Show() {
		
		
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
