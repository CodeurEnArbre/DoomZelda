package game.modele.tile;

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
	public static Tile get(int id) {
		switch(id)
		{
		case 0:
			return new tileVoid();
		case 1:
			return new tileGrass();
		case 2:
			return new tileGrass();
		case 3:
			return new tileGrass(Grass.Grass_BottomRight);
		case 4:
			return new tileGrass(Grass.Grass_Bottom);
		case 5:
			return new tileGrass(Grass.Grass_BottomLeft);
		case 6:
			return new tileRock(Rock.Rock_BottomRight);
		case 7:
			return new tileRock(Rock.Rock_Bottom);
		case 8:
			return new tileRock(Rock.Rock_BottomLeft);
		case 9:
			return new tileRockStairs(RockStairs.RockStair_Top);
		case 10:
			return new tileRockStairs(RockStairs.RockStair_Bottom);
		case 11:
			return new tileRockStairs(RockStairs.RockStair_Left);
		case 12:
			return new tileRockStairs(RockStairs.RockStair_Right);
		case 13:
			return new tileRock(Rock.Rock_Stone);
		case 14:
			return new tileStone();
		case 15:
			return new tileStone(Stone.SandStone);
		case 16:
			return new tileIce();
		case 17:
			return new tileDirt(Dirt.Dirt_TopLeft);
		case 18:
			return new tileDirt(Dirt.Dirt_TopRight);
		case 19:
			return new tileGrass(Grass.Grass_Right);
		case 20:
			return new tileDirt();
		case 21:
			return new tileGrass(Grass.Grass_Left);
		case 22:
			return new tileRock(Rock.Rock_Right);
		case 23:
			return new tileRock();
		case 24:
			return new tileRock(Rock.Rock_Left);
		case 25:
			return new tileRock(Rock.Rock_CornerBottomRight);
		case 26:
			return new tileRock(Rock.Rock_CornerBottomLeft);
		case 27:
			return new tileCactus();
		case 28:
			//NOT USED
		case 29:
			//NOT USED
		case 30:
			//NOT USED
		case 31:
			//NOT USED
		case 32:
			//NOT USED
		case 33:
			return new tileDirt(Dirt.Dirt_BotLeft);
		case 34:
			return new tileDirt(Dirt.Dirt_BotRight);
		case 35:
			return new tileGrass(Grass.Grass_TopRight);
		case 36:
			return new tileGrass(Grass.Grass_Top);
		case 37:
			return new tileGrass(Grass.Grass_TopLeft);
		case 38:
			return new tileRock(Rock.Rock_TopRight);
		case 39:
			return new tileRock(Rock.Rock_Top);
		case 40:
			return new tileRock(Rock.Rock_TopLeft);
		case 41:
			return new tileRock(Rock.Rock_CornerTopRight);
		case 42:
			return new tileRock(Rock.Rock_CornerTopLeft);
		case 43:
			return new tileSign(Sign.Sign_Direction);
		case 44:
			return new tileSign();
		case 45:
		case 46:
		case 47:
		case 48:
		case 49:
			return new tileThree(Three.Three_LeaveLeftTop);
		case 50:
			return new tileThree(Three.Three_LeaveTop);
		case 51:
			return new tileThree(Three.Three_LeaveRightTop);
		case 52:
			return new tileThree(Three.DeadThree_LeftTop);
		case 53:
			return new tileThree(Three.DeadThree_Top);
		case 54:
			return new tileThree(Three.DeadThree_RightTop);
		case 55:
		case 56:
		case 57:
		case 58:
			return new tileHouse(House.House_TopLeft);
		case 59:
			return new tileHouse(House.House_TopCenter1);
		case 60:
			return new tileHouse(House.House_TopCenter2);
		case 61:
			return new tileHouse(House.House_TopRight);
		case 62:
		case 63:
		case 64:
		case 65:
			return new tileThree(Three.Three_LeaveBotLeft);
		case 66:
			return new tileThree(Three.Three_LeaveBot);
		case 67:
			return new tileThree(Three.Three_LeaveBotRight);
		case 68:
			return new tileThree(Three.DeadThree_LeftCenter);
		case 69:
			return new tileThree(Three.DeadThree_Center);
		case 70:
			return new tileThree(Three.DeadThree_RightCenter);
		case 71:
		case 72:
		case 73:
		case 74:
			return new tileHouse(House.House_CenterLeft);
		case 75:
			return new tileHouse(House.House_CenterDoor);
		case 76:
			return new tileHouse(House.House_Center);
		case 77:
			return new tileHouse(House.House_CenterRight);
		case 78:
		case 79:
		case 80:
		case 81:
			return new tileThree(Three.Three_RootLeft);
		case 82:
			return new tileThree(Three.Three_Root);
		case 83:
			return new tileThree(Three.Three_RootRight);
		case 84:
			return new tileThree(Three.DeadThree_LeftBot);
		case 85:	
			return new tileThree(Three.DeadThree_Bot);
		case 86:
			return new tileThree(Three.DeadThree_RightBot);
		case 87:
		case 88:
		case 89:
		case 90:
			return new tileHouse(House.House_BotLeft);
		case 91:
			return new tileHouse(House.House_Door);
		case 92:
			return new tileHouse(House.House_Bot);
		case 93:
			return new tileHouse(House.House_BotRight);
		case 94:
		case 95:
		case 96:
		case 97:
			return new tileThree(Three.Bush_TopLeft);
		case 98:
			return new tileThree(Three.Bush_TopRight);
		case 99:
		case 100:
		case 101:
		case 102:
		case 103:
		case 104:
		case 105:
		case 106:
			return new tileBed();
		case 107:
			return new tileLadder(Ladder.Ladder_Top);
		case 108:
			return new tileFloor();
		case 109:
			return new tileFloor(Floor.Floor_other);
		case 110:
		case 111:
		case 112:
		case 113:
			return new tileThree(Three.Bush_BotLeft);
		case 114:
			return new tileThree(Three.Bush_BotRight);
		case 115:
		case 116:
		case 117:
		case 118:
		case 119:
		case 120:
		case 121:	
		case 122:
			return new tileBed(Bed.Bed_Bottom);
		case 123:
		case 124:
		case 125:
		case 126:
		case 127:
		case 128:
		case 129:
		case 130:
		case 131:
		case 132:
		case 133:
		case 134:
		case 135:
		case 136:
		case 137:
		case 138:
		case 139:
			return new tileLadder(Ladder.Ladder_Bottom);
		case 140:
		case 141:
		case 142:
		case 143:
		case 144:
		case 145:
		case 146:
		case 147:
		case 148:
		case 149:
		case 150:
			return new tileVoid();
		default:
			throw new Error("tile non trouvé : " + id);
		}

	}
}
