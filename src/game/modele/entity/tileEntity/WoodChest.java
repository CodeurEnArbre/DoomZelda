package game.modele.entity.tileEntity;

import game.modele.item.Item;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;

public class WoodChest extends Chest{

	public WoodChest(Coordonnees coordoner, Direction direction, Item insideItem) {
		super("Wood Chest", coordoner, direction, insideItem);
		// TODO Auto-generated constructor stub
	}

}
