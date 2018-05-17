package game.modele.entity;

import java.lang.reflect.Constructor;

import game.modele.entity.living.monster.Zombie;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;

public class EntityFactory {

	public static Entity create(String id, String params) {
		switch(id) {
		case "Player":
			return null;
		case "Test":
			return castParams(Zombie.class.getConstructors()[0],params);
		default :
			return null;


		}
	}
	public static Entity castParams(Constructor<?> c,String parameters){
		
		String[] params = parameters.split(",");
		Object[] os = new Object[c.getParameterCount()];
		
		Class<?>[] o = c.getParameterTypes();
		for(Class<?> p : o)
			if(p.getName().equals(Coordonnees.class.getName())) {
				os[0] = new Coordonnees(Double.parseDouble(params[0]), Double.parseDouble(params[1]));
				
				
				
			}else if(p.getName().equals(Direction.class.getName())) {
				
			}
		
		
		
		return null;
	}
	
	
	
	
	
}
