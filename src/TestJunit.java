import static org.junit.Assert.*;

import org.junit.Test;

import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import game.modele.world.World;

public class TestJunit {

	@Test
	public void testDeplacement() {
		
		Coordonnees playerCoord = new Coordonnees(10, 10);
		World.initWorldSave("Boss", playerCoord, new Direction(2), 0, 0, 0, null, null, null);
		
		World.player.update();
		
		assertTrue("cas avant deplacement", 10==playerCoord.getY()&&playerCoord.getX()==10);
		
		World.player.moveDown.active = true;
		World.player.update();
		
		assertTrue("cas apres deplacement", 10<playerCoord.getY()&&playerCoord.getX()==10);
		World.player.moveDown.active = false;
		
		float testCoordonneeY = playerCoord.getY();
		
		World.player.moveRight.active = true;
		World.player.update();
		assertTrue("cas apres deplacement", playerCoord.getX()>10 && playerCoord.getY()==testCoordonneeY);
		
		
		//Test Collision
		World.player.coordonnes.setCoordoner(11f, 10); //Il y a une tile solide a la coordonnee x:12,y:10 , x:12,y:11 et x:11,y:12    #* // # = player
		//																															   *
		for(int i=0;i<60*5;i++)// Simultation du deplacement  pendant 5 secondes													  *
			World.player.update();	
		
		float testCoordonneeX = playerCoord.getX();
		
		World.player.update();
		
		assertTrue("Cas impossible de ce deplacer car une tile bloque (colision)", testCoordonneeX==playerCoord.getX() && playerCoord.getY()==10);
		
		World.player.moveDown.active = true;
		
		for(int i=0;i<60*5;i++)// Simultation du deplacement  pendant 5 secondes
			World.player.update();
		
		testCoordonneeX = playerCoord.getX();
		testCoordonneeY = playerCoord.getY();
		World.player.update();
		
		assertTrue("Cas impossible de ce deplacer car une tile bloque (colision)", testCoordonneeX==playerCoord.getX() && testCoordonneeY==playerCoord.getY());
		
	}

}
