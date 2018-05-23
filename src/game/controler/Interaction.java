package game.controler;

import game.MainMenu;
import game.InGameMenu;
import game.modele.utils.Direction;
import game.modele.world.World;
import javafx.scene.input.KeyCode;

public class Interaction {
	/*
	 * Assignation des booleans de direction � l'enfoncement de la touche
	 * */
	
	public static KeyCode AVANCER = KeyCode.Z;
	public static KeyCode RECULER = KeyCode.S;
	public static KeyCode GAUCHE = KeyCode.Q;
	public static KeyCode DROITE = KeyCode.D;
	
	public static void KeyInteractDown(KeyCode k) {
		
		if(k == KeyCode.ENTER) {
			if(World.isWorldLoaded.get()) {
				if(World.onPause.get()) {
					if(InGameMenu.selectedButtonInGame.get() == 0) {
						if(InGameMenu.enterMenu.get()) {
							InGameMenu.enterPressed();							
						}else {
							InGameMenu.enterMenu.set(true);
						}
					}				
				}else {//In the game
					
				}

			}else {
				switch(MainMenu.selectedButton.get()) {
					case 0 : World.loadWorld("TinyMap",null);
						break;
					case 1 : //TODO OPTIONS
						break;
					case 2 : System.exit(0); 
				}
			}
		}

		if(k == KeyCode.ESCAPE) {
			if(World.isWorldLoaded.get()) {
				if(World.onPause.get()) {
					if(InGameMenu.enterMenu.get()) {
						if(InGameMenu.enterOption.get()) {
							InGameMenu.enterOption.set(false);
						}else {
							InGameMenu.enterMenu.set(false);
						}
					}else {
						World.onPause.set(!World.onPause.get());
					}
					World.playGameLoop();	
				}else {
					World.pauseGameLoop();					
				}
							
			}else {

			}
		}

		if(k == AVANCER) {
			if(World.isWorldLoaded.get()) {
				if(World.onPause.get() && !InGameMenu.enterOption.get()) {
					if(InGameMenu.enterMenu.get() ) {
						InGameMenu.selectionUpOption();
						
					}else {
						InGameMenu.selectionUp();
					}
				}else {
					World.player.moveUP.attente = false;
					World.player.moveUP.active = true;
					if(World.player.moveDown.active) {
						World.player.moveDown.attente = true;
						World.player.moveDown.active = false;
					}
				}
				
				setDirection(Direction.North);	
			}else {
				if(MainMenu.selectedButton.get()>0)
					MainMenu.selectedButton.set(MainMenu.selectedButton.get()-1);
			}

		}else if(k == RECULER){
			if(World.isWorldLoaded.get()) {
				if(World.onPause.get() && !InGameMenu.enterOption.get()) {
					if(InGameMenu.enterMenu.get() ) {
						InGameMenu.selectionDownOption();
					}else {
						InGameMenu.selectionDown();
					}
				}else {
					World.player.moveDown.attente = false;
					World.player.moveDown.active = true;
					if(World.player.moveUP.active)
						World.player.moveUP.attente = true;
					World.player.moveUP.active = false;

				setDirection(Direction.South);
				}
			}else {
				if(MainMenu.selectedButton.get()<2) {
					MainMenu.selectedButton.set(MainMenu.selectedButton.get()+1);		
				}
			}
		}
		if(k == GAUCHE && !InGameMenu.enterOption.get()) {
			if(World.isWorldLoaded.get()) {
				World.player.moveLeft.attente = false;
				World.player.moveLeft.active = true;
				if(World.player.moveRight.active)
					World.player.moveRight.attente = true;

				World.player.moveRight.active = false;

				setDirection(Direction.East);
			}
		}else if(k == DROITE && !InGameMenu.enterOption.get()) {
			if(World.isWorldLoaded.get()) {
				World.player.moveRight.attente = false;
				World.player.moveRight.active = true;
				if(World.player.moveLeft.active)
					World.player.moveLeft.attente = true;
				World.player.moveLeft.active = false;

				setDirection(Direction.West);
			}

		}else if(k != KeyCode.ENTER) {
			if(InGameMenu.enterOption.get()) {
				InGameMenu.setBind(k);
				InGameMenu.enterOption.set(false);
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
