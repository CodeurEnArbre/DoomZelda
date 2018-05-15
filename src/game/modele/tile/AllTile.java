package game.modele.tile;

import game.modele.tile.tileGround.tileGrass;
import game.modele.tile.tileGround.tileStone;

public enum AllTile {

	grass{
		@Override
		public Tile get() {
			return new tileGrass();
		}		
	}
,
	stone{
	@Override
		public Tile get() {
			return new tileStone();
		}
	};
	
	
	
	
	public Tile get() {
		return null;
	}
	
	
}
