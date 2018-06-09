package game.modele.entity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import game.modele.entity.living.Player;
import game.modele.entity.living.friendly.sheeps.WhiteSheep;
import game.modele.entity.living.monster.Zombie;
import game.modele.entity.tileEntity.EntityTP;
import game.modele.entity.tileEntity.carriable.Bush;
import game.modele.entity.tileEntity.chest.GoldChest;
import game.modele.entity.tileEntity.chest.IronChest;
import game.modele.entity.tileEntity.chest.WoodChest;
import game.modele.entity.tileEntity.light.TikiTorchSmall;
import game.modele.item.Item;
import game.modele.item.ItemFactory;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import game.modele.world.World;

public class EntityFactory {

	public static Entity create(String id, String params) {
		Entity e = null;
		try {
			switch(id) {
			case "Player":
				e = (Player) Player.class.getConstructors()[0]
						.newInstance(castParams(Player.class.getConstructors()[0],params));
				break;
			case "TileEntityTP":
				e = (EntityTP) EntityTP.class.getConstructors()[0]
						.newInstance(castParams(EntityTP.class.getConstructors()[0],params));
				break;
			case "TikiTorchSmall":
				e = (TikiTorchSmall) TikiTorchSmall.class.getConstructors()[0]
						.newInstance(castParams(TikiTorchSmall.class.getConstructors()[0],params));
				break;
			case "Bush":
				e = (Bush) Bush.class.getConstructors()[0]
						.newInstance(castParams(Bush.class.getConstructors()[0],params));
				break;
			case "Zombie":
				e = (Entity) Zombie.class.getConstructors()[0]
						.newInstance(castParams(Zombie.class.getConstructors()[0],params));
				break;
			case "White Sheep":
				e = (Entity) WhiteSheep.class.getConstructors()[0]
						.newInstance(castParams(WhiteSheep.class.getConstructors()[0],params));
				break;
			case "Gold Chest":
				e = (Entity) GoldChest.class.getConstructors()[0]
						.newInstance(castParams(GoldChest.class.getConstructors()[0],params));
				break;
			case "Iron Chest":
				e = (Entity) IronChest.class.getConstructors()[0]
						.newInstance(castParams(IronChest.class.getConstructors()[0],params));
				break;
			case "Wood Chest":
				e = (Entity) WoodChest.class.getConstructors()[0]
						.newInstance(castParams(WoodChest.class.getConstructors()[0],params));
				break;
			case "EntityItemOnGround":
				e = (EntityItemOnGround) EntityItemOnGround.class.getConstructors()[0]
						.newInstance(castParams(EntityItemOnGround.class.getConstructors()[0],params));
				break;
			default :


			}
		}catch(IllegalAccessException 
				|InstantiationException 
				|IllegalArgumentException 
				|InvocationTargetException 
				|SecurityException e1) {
			e1.printStackTrace();
		}
		return e;
	}
	public static Object[] castParams(Constructor<?> c,String parameters){

		String[] params = parameters.split(",");
		Object[] os = new Object[c.getParameterCount()];

		int nb = 0;


		for(int i = 0; i < os.length;i++) {			
			Class<?> o = c.getParameterTypes()[i];
			if(o.getName().equals(Coordonnees.class.getName())) {
				os[i] = new Coordonnees(
						Double.parseDouble(params[nb++]), Double.parseDouble(params[nb++]));
			}else if(o.getName().equals(Direction.class.getName())) {
				os[i] = new Direction(
						Integer.parseInt(params[nb++]));
			}else if(o.getName().equals(boolean.class.getName())) {
				os[i] =	Boolean.parseBoolean(params[nb++]);
			}else if(o.getName().equals(String.class.getName())) {
				os[i] = params[nb++]; 
			}else if(o.getName().equals(int.class.getName())) {
				os[i] = Integer.parseInt(params[nb++]);
			}else if(o.getName().equals(Item.class.getName())) {
				os[i] = ItemFactory.getItem(params[nb++]);
			}
		}
		return os;

	}}
