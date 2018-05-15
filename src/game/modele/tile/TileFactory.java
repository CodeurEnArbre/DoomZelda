package game.modele.tile;

import game.modele.tile.tileGround.tileDirt;
import game.modele.tile.tileGround.tileDirt.Dirt;
import game.modele.tile.tileGround.tileGrass;
import game.modele.tile.tileGround.tileGrass.Grass;
import game.modele.tile.tileGround.tileIce;
import game.modele.tile.tileGround.tileRock;
import game.modele.tile.tileGround.tileRock.Rock;
import game.modele.tile.tileGround.tileRockStrair;
import game.modele.tile.tileGround.tileRockStrair.RockStair;
import game.modele.tile.tileGround.tileStone.Stone;
import game.modele.tile.tileGround.tileThree;
import game.modele.tile.tileGround.tileThree.Three;
import game.modele.tile.tileGround.tileStone;
import game.modele.tile.tileGround.tileVoid;

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
			return new tileRockStrair(RockStair.RockStair_Top);
		case 10:
			return new tileRockStrair(RockStair.RockStair_Bottom);
		case 11:
			return new tileRockStrair(RockStair.RockStair_Left);
		case 12:
			return new tileRockStrair(RockStair.RockStair_Right);
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
			//NOT USED
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
		case 44:
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
		case 53:
		case 54:
		case 55:
		case 56:
		case 57:
		case 58:
		case 59:
		case 60:
		case 61:
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
		case 69:
		case 70:
		case 71:
		case 72:
		case 73:
		case 74:
		case 75:
		case 76:
		case 77:
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
		case 85:	
		case 86:
		case 87:
		case 88:
		case 89:
		case 90:
		case 91:
		case 92:
		case 93:
		case 94:
		case 95:
		case 96:
		case 97:
		case 98:
		case 99:
		case 100:
		case 101:
		case 102:
		case 103:
		case 104:
		case 105:
		case 106:
		case 107:
		case 108:
		case 109:
		case 110:
		case 111:
		case 112:
		case 113:
		case 114:
		case 115:
		case 116:
		case 117:
		case 118:
		case 119:
		case 120:
		case 121:	
		case 122:
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
			throw new Error("tile non trouvé");
		}

	}
}
