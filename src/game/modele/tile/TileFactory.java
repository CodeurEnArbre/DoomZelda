package game.modele.tile;

import java.util.HashMap;
import java.util.Map;

import game.modele.tile.tileGround.tileCactus;
import game.modele.tile.tileGround.tileDirt;
import game.modele.tile.tileGround.tileDirt.Dirt;
import game.modele.tile.tileGround.tileFloor.Floor;
import game.modele.tile.tileGround.tileFloor;
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
import game.modele.tile.tileGround.tileStone.Stone;
import game.modele.tile.tileGround.tileThree;
import game.modele.tile.tileGround.tileThree.Three;
import game.modele.tile.tileGround.tileStone;
import game.modele.tile.tileGround.tileVoid;
import game.modele.tile.tileInteract.tileBed;
import game.modele.tile.tileInteract.tileBed.Bed;
import game.modele.tile.tileInteract.tileSign;
import game.modele.tile.tileInteract.tileSign.Sign;

public class TileFactory {	
	private static Map<Integer,Tile> dicoTile = new HashMap<Integer, Tile>();

	public static Tile get(int id) {
		return load(id);
		//return dicoTile.get(id);
	}
	
	private static Tile load(int id) {
		if( id == 0 ) return new tileVoid();
		if( id == 1 ) return new tileGrass();
		if( id == 2 ) return new tileGrass();
		if( id == 3 ) return new tileGrass(Grass.Grass_BottomRight);
		if( id == 4 ) return new tileGrass(Grass.Grass_Bottom);
		if( id == 5 ) return new tileGrass(Grass.Grass_BottomLeft);
		if( id == 6 ) return new tileRock(Rock.Rock_BottomRight);
		if( id == 7 ) return new tileRock(Rock.Rock_Bottom);
		if( id == 8 ) return new tileRock(Rock.Rock_BottomLeft);
		if( id == 9 ) return new tileRockStairs(RockStairs.RockStair_Top);
		if( id == 10 ) return new tileRockStairs(RockStairs.RockStair_Bottom);
		if( id == 11 ) return new tileRockStairs(RockStairs.RockStair_Left);
		if( id == 12 ) return new tileRockStairs(RockStairs.RockStair_Right);
		if( id == 13 ) return new tileRock(Rock.Rock_Stone);
		if( id == 14 ) return new tileStone();
		if( id == 15 ) return new tileStone(Stone.SandStone);
		if( id == 16 ) return new tileIce();
		if( id == 17 ) return new tileDirt(Dirt.Dirt_TopLeft);
		if( id == 18 ) return new tileDirt(Dirt.Dirt_TopRight);
		if( id == 19 ) return new tileGrass(Grass.Grass_Right);
		if( id == 20 ) return new tileDirt();
		if( id == 21 ) return new tileGrass(Grass.Grass_Left);
		if( id == 22 ) return new tileRock(Rock.Rock_Right);
		if( id == 23 ) return new tileRock();
		if( id == 24 ) return new tileRock(Rock.Rock_Left);
		if( id == 25 ) return new tileRock(Rock.Rock_CornerBottomRight);
		if( id == 26 ) return new tileRock(Rock.Rock_CornerBottomLeft);
		if( id == 27 ) return new tileCactus();

		if( id == 33 ) return new tileDirt(Dirt.Dirt_BotLeft);
		if( id == 34 ) return new tileDirt(Dirt.Dirt_BotRight);
		if( id == 35 ) return new tileGrass(Grass.Grass_TopRight);
		if( id == 36 ) return new tileGrass(Grass.Grass_Top);
		if( id == 37 ) return new tileGrass(Grass.Grass_TopLeft);
		if( id == 38 ) return new tileRock(Rock.Rock_TopRight);
		if( id == 39 ) return new tileRock(Rock.Rock_Top);
		if( id == 40 ) return new tileRock(Rock.Rock_TopLeft);
		if( id == 41 ) return new tileRock(Rock.Rock_CornerTopRight);
		if( id == 42 ) return new tileRock(Rock.Rock_CornerTopLeft);
		if( id == 43 ) return new tileSign(Sign.Sign_Direction);
		if( id == 44 ) return new tileSign();

		if( id == 49 ) return new tileThree(Three.Three_LeaveLeftTop);
		if( id == 50 ) return new tileThree(Three.Three_LeaveTop);
		if( id == 51 ) return new tileThree(Three.Three_LeaveRightTop);
		if( id == 52 ) return new tileThree(Three.DeadThree_LeftTop);
		if( id == 53 ) return new tileThree(Three.DeadThree_Top);
		if( id == 54 ) return new tileThree(Three.DeadThree_RightTop);

		if( id == 58 ) return new tileHouse(House.House_TopLeft);
		if( id == 59 ) return new tileHouse(House.House_TopCenter1);
		if( id == 60 ) return new tileHouse(House.House_TopCenter2);
		if( id == 61 ) return new tileHouse(House.House_TopRight);

		if( id == 65 ) return new tileThree(Three.Three_LeaveBotLeft);
		if( id == 66 ) return new tileThree(Three.Three_LeaveBot);
		if( id == 67 ) return new tileThree(Three.Three_LeaveBotRight);
		if( id == 68 ) return new tileThree(Three.DeadThree_LeftCenter);
		if( id == 69 ) return new tileThree(Three.DeadThree_Center);
		if( id == 70 ) return new tileThree(Three.DeadThree_RightCenter);

		if( id == 74 ) return new tileHouse(House.House_CenterLeft);
		if( id == 75 ) return new tileHouse(House.House_CenterDoor);
		if( id == 76 ) return new tileHouse(House.House_Center);
		if( id == 77 ) return new tileHouse(House.House_CenterRight);
		
		if( id == 81 ) return new tileThree(Three.Three_RootLeft);
		if( id == 82 ) return new tileThree(Three.Three_Root);
		if( id == 83 ) return new tileThree(Three.Three_RootRight);
		if( id == 84 ) return new tileThree(Three.DeadThree_LeftBot);
		if( id == 85 ) return new tileThree(Three.DeadThree_Bot);
		if( id == 86 ) return new tileThree(Three.DeadThree_RightBot);

		if( id == 90 ) return new tileHouse(House.House_BotLeft);
		if( id == 91 ) return new tileHouse(House.House_Door);
		if( id == 92 ) return new tileHouse(House.House_Bot);
		if( id == 93 ) return new tileHouse(House.House_BotRight);

		if( id == 97 ) return new tileThree(Three.Bush_TopLeft);
		if( id == 98 ) return new tileThree(Three.Bush_TopRight);

		if( id == 106 ) return new tileBed();
		if( id == 107 ) return new tileLadder(Ladder.Ladder_Top);
		if( id == 108 ) return new tileFloor();
		if( id == 109 ) return new tileFloor(Floor.Floor_other);

		if( id == 113 ) return new tileThree(Three.Bush_BotLeft);
		if( id == 114 ) return new tileThree(Three.Bush_BotRight);
		
		if( id == 123 ) return new tileLadder();
		
		if( id == 122 ) return new tileBed(Bed.Bed_Bottom);

		if( id == 139 ) return new tileLadder(Ladder.Ladder_Bottom);
		
		return null;

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
