package game;

import game.controleur.world.WorldLoader;

public class Main {

	public static void main(String[] args) {
		WorldLoader.loadWorld("TestMap");
		
		System.out.println("Info : "+WorldLoader.currentMap.getTile(12, 62));
	}

}
