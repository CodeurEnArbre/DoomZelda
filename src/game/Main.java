package game;

import java.io.File;

import game.controleur.world.WorldLoader;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hello WORLD!!!");
		WorldLoader.loadWorld(new File("ressources/map/TestMap.map"));
		
		System.out.println("Info : "+WorldLoader.currentMap.getTile(12, 62));
	}

}
