package game.controler;

import game.modele.utils.Direction;
import game.modele.world.WorldLoader;
import javafx.scene.input.KeyCode;

public class Interaction {
	/*
	 * Assignation des booleans de direction à l'enfoncement de la touche
	 * */
	public static void KeyInteractDown(KeyCode k) {

		if(k == KeyCode.Z) {
			WorldLoader.player.moveUP.attente = false;
			WorldLoader.player.moveUP.active = true;
			if(WorldLoader.player.moveDown.active)
				WorldLoader.player.moveDown.attente = true;
			WorldLoader.player.moveDown.active = false;

			setDirection(Direction.North);	

		}else if(k == KeyCode.S){
			WorldLoader.player.moveDown.attente = false;
			WorldLoader.player.moveDown.active = true;
			if(WorldLoader.player.moveUP.active)
				WorldLoader.player.moveUP.attente = true;
			WorldLoader.player.moveUP.active = false;

			setDirection(Direction.South);

		}
		if(k == KeyCode.Q) {
			WorldLoader.player.moveLeft.attente = false;
			WorldLoader.player.moveLeft.active = true;
			if(WorldLoader.player.moveRight.active)
				WorldLoader.player.moveRight.attente = true;

			WorldLoader.player.moveRight.active = false;

			setDirection(Direction.East);

		}else if(k == KeyCode.D) {
			WorldLoader.player.moveRight.attente = false;
			WorldLoader.player.moveRight.active = true;
			if(WorldLoader.player.moveLeft.active)
				WorldLoader.player.moveLeft.attente = true;
			WorldLoader.player.moveLeft.active = false;

			setDirection(Direction.West);

		}
	}

	/*
	 * Set Direction
	 * */
	public static void setDirection(int direction) {
		WorldLoader.player.getOrientation().getDirectionProperty().setValue(direction);
	}

	/*
	 * Assignation des booleans de direction au relachement de la touche
	 * */
	public static void KeyInteractUp(KeyCode k) {
		if(k == KeyCode.Z) {
			WorldLoader.player.moveUP.active = false;
			WorldLoader.player.moveUP.attente = false;
			if(WorldLoader.player.moveDown.attente) {
				WorldLoader.player.moveDown.active = WorldLoader.player.moveDown.attente;
				setDirection(Direction.South);
				WorldLoader.player.moveDown.attente = false;
			}else {
				if(WorldLoader.player.moveLeft.active)
					setDirection(Direction.East);

				if(WorldLoader.player.moveRight.active)
					setDirection(Direction.West);

			}
		}else if(k == KeyCode.S) {
			WorldLoader.player.moveDown.active = false;
			WorldLoader.player.moveDown.attente = false;
			if(WorldLoader.player.moveUP.attente) {
				WorldLoader.player.moveUP.active = WorldLoader.player.moveUP.attente;
				setDirection(Direction.North);	
				WorldLoader.player.moveUP.attente = false;
			}else {
				if(WorldLoader.player.moveLeft.active)
					setDirection(Direction.East);

				if(WorldLoader.player.moveRight.active)
					setDirection(Direction.West);
			}
		}else if(k == KeyCode.Q) {
			WorldLoader.player.moveLeft.active = false;
			WorldLoader.player.moveLeft.attente = false;
			if(WorldLoader.player.moveRight.attente) {
				WorldLoader.player.moveRight.active = WorldLoader.player.moveRight.attente;
				setDirection(Direction.West);
				WorldLoader.player.moveRight.attente = false;
			}else {
				if(WorldLoader.player.moveUP.active)
					setDirection(Direction.North);

				if(WorldLoader.player.moveDown.active)
					setDirection(Direction.South);
			}
		}else if(k == KeyCode.D) {
			WorldLoader.player.moveRight.active = false;
			WorldLoader.player.moveRight.attente = false;
			if(WorldLoader.player.moveLeft.attente) {
				WorldLoader.player.moveLeft.active = WorldLoader.player.moveLeft.attente;
				setDirection(Direction.East);
				WorldLoader.player.moveLeft.attente = false;
			}else {
				if(WorldLoader.player.moveUP.active)
					setDirection(Direction.North);

				if(WorldLoader.player.moveDown.active)
					setDirection(Direction.South);				
			}
		}




	}	
}
