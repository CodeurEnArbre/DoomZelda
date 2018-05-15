package game.modele.tile.tileGround;

public class tileStone extends tileGround{

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
	public void Action() {
		System.out.println(13);
	}

}
