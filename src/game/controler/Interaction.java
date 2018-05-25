package game.controler;

import game.modele.menu.Menu;
import game.modele.menu.OptionsMenu;
import game.modele.utils.Direction;
import game.modele.world.World;
import javafx.scene.input.KeyCode;

public class Interaction {
	/*
	 * Assignation des booleans de direction � l'enfoncement de la touche
	 * */
	
	//Direction
	public static KeyCode AVANCER = KeyCode.Z;
	public static KeyCode RECULER = KeyCode.S;
	public static KeyCode GAUCHE = KeyCode.Q;
	public static KeyCode DROITE = KeyCode.D;
	
	//Inventaire
	public static KeyCode INVENTAIRE = KeyCode.E;
	
	public static void KeyInteractDown(KeyCode k) {
		
		if(k == KeyCode.ENTER) {
			Menu.validate();
				
		}

		if(k == KeyCode.ESCAPE) {
			Menu.escape();
		}
		
		if(k == INVENTAIRE) {
			Menu.inventory();
		}

		if(k == AVANCER) {
			Menu.selectUp();
			if(World.isWorldLoaded.get() && Menu.currentMenu.get() == Menu.NoMenuID) {
					World.player.moveUP.attente = false;
					World.player.moveUP.active = true;
					if(World.player.moveDown.active) {
						World.player.moveDown.attente = true;
						World.player.moveDown.active = false;
				}
				
				setDirection(Direction.North);	
			}

		}else if(k == RECULER){
			Menu.selectDown();
			if(World.isWorldLoaded.get() && Menu.currentMenu.get() == Menu.NoMenuID) {
					World.player.moveDown.attente = false;
					World.player.moveDown.active = true;
					if(World.player.moveUP.active)
						World.player.moveUP.attente = true;
					World.player.moveUP.active = false;

				setDirection(Direction.South);
			}
		}
		if(k == GAUCHE) {
			Menu.selectLeft();
			if(World.isWorldLoaded.get() && Menu.currentMenu.get() == Menu.NoMenuID) {
				World.player.moveLeft.attente = false;
				World.player.moveLeft.active = true;
				if(World.player.moveRight.active)
					World.player.moveRight.attente = true;

				World.player.moveRight.active = false;

				setDirection(Direction.East);
			}
		}else if(k == DROITE ) {
			Menu.selectRight();
			if(World.isWorldLoaded.get() && Menu.currentMenu.get() == Menu.NoMenuID) {
				World.player.moveRight.attente = false;
				World.player.moveRight.active = true;
				if(World.player.moveLeft.active)
					World.player.moveLeft.attente = true;
				World.player.moveLeft.active = false;

				setDirection(Direction.West);
			}

		}else if(k != KeyCode.ENTER) {
			if(OptionsMenu.enterOption.get()) {
				OptionsMenu.setBind(k);
				OptionsMenu.enterOption.set(false);
			}
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
		if(k == AVANCER) {
			if(World.isWorldLoaded.get()) {
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
			}

		}else if(k == RECULER) {
			if(World.isWorldLoaded.get()) {
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
			}
		}else if(k == GAUCHE) {
			if(World.isWorldLoaded.get()) {
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
			}
		}else if(k == DROITE) {
			if(World.isWorldLoaded.get()) {
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
}
