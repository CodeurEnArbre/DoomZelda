package game.modele.tile.tileGround;

public class tileThree extends TileGround{

	public enum Three{
		Three_LeaveLeftTop(49),
		Three_LeaveRightTop(51),
		Three_LeaveTop(50),
		Three_LeaveBot(66),
		Three_LeaveBotLeft(65),
		Three_LeaveBotRight(67),
		Three_RootLeft(81),
		Three_Root(82),
		Three_RootRight(83),
		
		DeadThree_LeftTop(52),
		DeadThree_Top(53),
		DeadThree_RightTop(54),
		DeadThree_LeftCenter(68),
		DeadThree_Center(69),
		DeadThree_RightCenter(70),
		DeadThree_LeftBot(84),
		DeadThree_Bot(85),
		DeadThree_RightBot(86),
		
		Bush_TopLeft(97),
		Bush_TopRight(98),
		Bush_BotLeft(113),
		Bush_BotRight(114);
		
		
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
}
