package game.modele.tile.tileGround;

public class tileThree extends tileGround{

	public enum Three{
		Three_LeaveLeftTop(49),
		Three_LeaveRightTop(51),
		Three_LeaveTop(50),
		Three_LeaveBot(66),
		Three_LeaveBotLeft(65),
		Three_LeaveBotRight(67),
		Three_RootLeft(81),
		Three_Root(82),
		Three_RootRight(83);
		
		private int id; 
		Three(int id) {
			this.id = id;
		}
		public int get() {
			return id;
		}
		
	}
	
	
	public tileThree() {
		super(64);
	}

	public tileThree(Three t) {
		super(t.id);
	}

	@Override
	public void Action() {
		// TODO Auto-generated method stub
		
	}

}
