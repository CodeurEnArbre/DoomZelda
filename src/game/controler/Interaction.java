package game.controler;

import game.modele.utils.Direction;
import game.modele.world.World;
import javafx.scene.input.KeyCode;

public class Interaction {
	/*
	 * Assignation des booleans de direction � l'enfoncement de la touche
	 * */
	public static void KeyInteractDown(KeyCode k) {

		if(k == KeyCode.ESCAPE) {
			World.onPause.set(!World.onPause.get());
			if(World.onPause.get())
				World.pauseGameLoop();
			else
				World.playGameLoop();
		}
		
		if(k == KeyCode.Z) {
			World.player.moveUP.attente = false;
			World.player.moveUP.active = true;
			if(World.player.moveDown.active)
				World.player.moveDown.attente = true;
			World.player.moveDown.active = false;

			setDirection(Direction.North);	

		}else if(k == KeyCode.S){
			World.player.moveDown.attente = false;
			World.player.moveDown.active = true;
			if(World.player.moveUP.active)
				World.player.moveUP.attente = true;
			World.player.moveUP.active = false;

			setDirection(Direction.South);

		}
		if(k == KeyCode.Q) {
			World.player.moveLeft.attente = false;
			World.player.moveLeft.active = true;
			if(World.player.moveRight.active)
				World.player.moveRight.attente = true;

			World.player.moveRight.active = false;

			setDirection(Direction.East);

		}else if(k == KeyCode.D) {
			World.player.moveRight.attente = false;
			World.player.moveRight.active = true;
			if(World.player.moveLeft.active)
				World.player.moveLeft.attente = true;
			World.player.moveLeft.active = false;

			setDirection(Direction.West);

		}
	}

	/*
	 * Set Direction
	 * */
	public static void setDirection(int direction) {
		World.player.direction.getDirectionProperty().setValue(direction);
	}

	/*
	 * Assignation des booleans de direction au relachement de la touche
	 * */
	public static void KeyInteractUp(KeyCode k) {
		if(k == KeyCode.Z) {
			World.player.moveUP.active = false;
			World.player.moveUP.attente = false;
			if(World.player.moveDown.attente) {
				World.player.moveDown.active = World.player.moveDown.attente;
				setDirection(Direction.South);
				World.player.moveDown.attente = false;
			}else {
				if(World.player.moveLeft.active)
					setDirection(Direction.East);

				if(World.player.moveRight.active)
					setDirection(Direction.West);

			}
		}else if(k == KeyCode.S) {
			World.player.moveDown.active = false;
			World.player.moveDown.attente = false;
			if(World.player.moveUP.attente) {
				World.player.moveUP.active = World.player.moveUP.attente;
				setDirection(Direction.North);	
				World.player.moveUP.attente = false;
			}else {
				if(World.player.moveLeft.active)
					setDirection(Direction.East);

				if(World.player.moveRight.active)
					setDirection(Direction.West);
			}
		}else if(k == KeyCode.Q) {
			World.player.moveLeft.active = false;
			World.player.moveLeft.attente = false;
			if(World.player.moveRight.attente) {
				World.player.moveRight.active = World.player.moveRight.attente;
				setDirection(Direction.West);
				World.player.moveRight.attente = false;
			}else {
				if(World.player.moveUP.active)
					setDirection(Direction.North);

				if(World.player.moveDown.active)
					setDirection(Direction.South);
			}
		}else if(k == KeyCode.D) {
			World.player.moveRight.active = false;
			World.player.moveRight.attente = false;
			if(World.player.moveLeft.attente) {
				World.player.moveLeft.active = World.player.moveLeft.attente;
				setDirection(Direction.East);
				World.player.moveLeft.attente = false;
			}else {
				if(World.player.moveUP.active)
					setDirection(Direction.North);

				if(World.player.moveDown.active)
					setDirection(Direction.South);				
			}
		}




	}	
}
