package game.modele.tile;

import java.util.HashMap;
import java.util.Map;

import game.modele.tile.tileGround.tileCactus;
import game.modele.tile.tileGround.tileDirt;
import game.modele.tile.tileGround.tileDirt.Dirt;
import game.modele.tile.tileGround.tileFloor;
import game.modele.tile.tileGround.tileFloor.Floor;
import game.modele.tile.tileGround.tileGrass;
import game.modele.tile.tileGround.tileGrass.Grass;
import game.modele.tile.tileGround.tileHouse;
import game.modele.tile.tileGround.tileHouse.House;
import game.modele.tile.tileGround.tileIce;
import game.modele.tile.tileGround.tileLadder;
import game.modele.tile.tileGround.tileLadder.Ladder;
import game.modele.tile.tileGround.tileRock;
import game.modele.tile.tileGround.tileRock.Rock;
import game.modele.tile.tileGround.tileRockStairs;
import game.modele.tile.tileGround.tileRockStairs.RockStairs;
import game.modele.tile.tileGround.tileStone;
import game.modele.tile.tileGround.tileStone.Stone;
import game.modele.tile.tileGround.tileThree;
import game.modele.tile.tileGround.tileThree.Three;
import game.modele.tile.tileGround.tileVoid;
import game.modele.tile.tileInteract.tileBed;
import game.modele.tile.tileInteract.tileBed.Bed;
import game.modele.tile.tileInteract.tileSign;
import game.modele.tile.tileInteract.tileSign.Sign;

public class TileFactory {	
	private static Map<Integer,Tile> dicoTile = new HashMap<Integer, Tile>();

	public static Tile get(int id) {
		return dicoTile.get(id);
	}
	
	public static void load() {
			dicoTile.put(0,new tileVoid());
			dicoTile.put(1, new tileGrass());
			dicoTile.put(2,new tileGrass());
			dicoTile.put(3,new tileGrass(Grass.Grass_BottomRight));
			dicoTile.put(4,new tileGrass(Grass.Grass_Bottom));
			dicoTile.put(5,new tileGrass(Grass.Grass_BottomLeft));
			dicoTile.put(6,new tileRock(Rock.Rock_BottomRight));
			dicoTile.put(7,new tileRock(Rock.Rock_Bottom));
			dicoTile.put(8,new tileRock(Rock.Rock_BottomLeft));
			dicoTile.put(9,new tileRockStairs(RockStairs.RockStair_Top));
			dicoTile.put(10,new tileRockStairs(RockStairs.RockStair_Bottom));
			dicoTile.put(11,new tileRockStairs(RockStairs.RockStair_Left));
			dicoTile.put(12,new tileRockStairs(RockStairs.RockStair_Right));
			dicoTile.put(13, new tileRock(Rock.Rock_Stone));
			dicoTile.put(14, new tileStone());
			dicoTile.put(15, new tileStone(Stone.SandStone));
			dicoTile.put(16, new tileIce());
			dicoTile.put(17, new tileDirt(Dirt.Dirt_TopLeft));
			dicoTile.put(18, new tileDirt(Dirt.Dirt_TopRight));
			dicoTile.put(19, new tileGrass(Grass.Grass_Right));
			dicoTile.put(20, new tileDirt());
			dicoTile.put(21, new tileGrass(Grass.Grass_Left));
			dicoTile.put(22, new tileRock(Rock.Rock_Right));
			dicoTile.put(23, new tileRock());
			dicoTile.put(24, new tileRock(Rock.Rock_Left));
			dicoTile.put(25, new tileRock(Rock.Rock_CornerBottomRight));
			dicoTile.put(26, new tileRock(Rock.Rock_CornerBottomLeft));
			dicoTile.put(27, new tileCactus());

			dicoTile.put(33, new tileDirt(Dirt.Dirt_BotLeft));
			dicoTile.put(34, new tileDirt(Dirt.Dirt_BotRight));
			dicoTile.put(35, new tileGrass(Grass.Grass_TopRight));
			dicoTile.put(36, new tileGrass(Grass.Grass_Top));
			dicoTile.put(37, new tileGrass(Grass.Grass_TopLeft));
			dicoTile.put(38, new tileRock(Rock.Rock_TopRight));
			dicoTile.put(39, new tileRock(Rock.Rock_Top));
			dicoTile.put(40, new tileRock(Rock.Rock_TopLeft));
			dicoTile.put(41, new tileRock(Rock.Rock_CornerTopRight));
			dicoTile.put(42, new tileRock(Rock.Rock_CornerTopLeft));
			dicoTile.put(43, new tileSign(Sign.Sign_Direction));
			dicoTile.put(44, new tileSign());

			dicoTile.put(49, new tileThree(Three.Three_LeaveLeftTop));
			dicoTile.put(50, new tileThree(Three.Three_LeaveTop));
			dicoTile.put(51, new tileThree(Three.Three_LeaveRightTop));
			dicoTile.put(52, new tileThree(Three.DeadThree_LeftTop));
			dicoTile.put(53, new tileThree(Three.DeadThree_Top));
			dicoTile.put(54, new tileThree(Three.DeadThree_RightTop));

			dicoTile.put(58, new tileHouse(House.House_TopLeft));
			dicoTile.put(59, new tileHouse(House.House_TopCenter1));
			dicoTile.put(60, new tileHouse(House.House_TopCenter2));
			dicoTile.put(61, new tileHouse(House.House_TopRight));

			dicoTile.put(65, new tileThree(Three.Three_LeaveBotLeft));
			dicoTile.put(66, new tileThree(Three.Three_LeaveBot));
			dicoTile.put(67, new tileThree(Three.Three_LeaveBotRight));
			dicoTile.put(68, new tileThree(Three.DeadThree_LeftCenter));
			dicoTile.put(69, new tileThree(Three.DeadThree_Center));
			dicoTile.put(70, new tileThree(Three.DeadThree_RightCenter));

			dicoTile.put(74, new tileHouse(House.House_CenterLeft));
			dicoTile.put(75, new tileHouse(House.House_CenterDoor));
			dicoTile.put(76, new tileHouse(House.House_Center));
			dicoTile.put(77, new tileHouse(House.House_CenterRight));
			
			dicoTile.put(81, new tileThree(Three.Three_RootLeft));
			dicoTile.put(82, new tileThree(Three.Three_Root));
			dicoTile.put(83, new tileThree(Three.Three_RootRight));
			dicoTile.put(84,new tileThree(Three.DeadThree_LeftBot));
			dicoTile.put(85, new tileThree(Three.DeadThree_Bot));
			dicoTile.put(86, new tileThree(Three.DeadThree_RightBot));

			dicoTile.put(90, new tileHouse(House.House_BotLeft));
			dicoTile.put(91, new tileHouse(House.House_Door));
			dicoTile.put(92, new tileHouse(House.House_Bot));
			dicoTile.put(93, new tileHouse(House.House_BotRight));

			dicoTile.put(97, new tileThree(Three.Bush_TopLeft));
			dicoTile.put(98, new tileThree(Three.Bush_TopRight));

			dicoTile.put(106, new tileBed());
			dicoTile.put(107, new tileLadder(Ladder.Ladder_Top));
			dicoTile.put(108, new tileFloor());
			dicoTile.put(109, new tileFloor(Floor.Floor_other));

			dicoTile.put(113, new tileThree(Three.Bush_BotLeft));
			dicoTile.put(114, new tileThree(Three.Bush_BotRight));
			
			dicoTile.put(123, new tileLadder());
			
			dicoTile.put(122, new tileBed(Bed.Bed_Bottom));

			dicoTile.put(139,new tileLadder(Ladder.Ladder_Bottom));

			
			
			
	}




}
