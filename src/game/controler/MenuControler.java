package game.controler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import game.MainMenu;
import game.modele.entity.Entity;
import game.modele.utils.Direction;
import game.modele.world.World;
import game.vue.EntityLivingTexture;
import game.vue.TextureLoader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class MenuControler implements Initializable{

	Map<Integer,Image> dicoImageTileTextureMap;
	Map<Integer,Image> dicoImageItemTextureMap;
	Map<Integer,Image> dicoImageAnimationPlayer;
	Map<Integer,Image[]> dicoImageAnimationEntity; 

	ArrayList<ImageView> coeurs;

	@FXML
	private Pane paneWindow;//Main avec tout les autres pane dedans
	@FXML
	private Pane paneGame;//Contient les panes d'affichage de la map et des entites
	@FXML 
	private Pane PaneHUD;//Permet d'afficher l'HUD et tout ce qui n'est pas une tile ou une entite


	@FXML
	private Pane PaneGround;
	@FXML
	private Pane PaneSolid;
	@FXML
	private Pane EntityPane;
	@FXML
	private Pane PlayerPane;
	@FXML
	private Pane PaneTop;

	@FXML
	private Pane PaneMenu;
	
	@FXML
    private Pane homeMenu;
	
	@FXML
    private ImageView menuImageFont;

	@FXML
    private ImageView selectionArrow;
	
    @FXML
    private ImageView playImg;
    @FXML
    private ImageView optionImg;
    @FXML
    private ImageView exitImg;

	@FXML
	private Button buttonReprendre;


	private ImageView player;//l'image du joueur a l'ecran

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//chagement du menu principale
		menuLoading();
		
		//
		World.onPause.addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) 
					PaneMenu.setOpacity(1.0);
				else
					PaneMenu.setOpacity(0.0);
			}

		});

		MainMenu.selectedButton.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				System.out.println(newValue);
				selectionArrow.relocate(playImg.getLayoutX()-60, playImg.getLayoutY()+120*newValue.intValue());
			}
			
		});
		
		//Chargement dans la memoire de toutes les textures
		textureLoading();		

		World.isWorldLoaded.addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				homeMenu.setOpacity(0);
				System.out.println("Loading save");
				if(newValue) {
					loadMapTexture();
					
				}
			}

		});
		
	}
	
	public void loadMapTexture() {
		//Chargement de la map


				affichageEntitys();
				//Chargement du joueur
				player=new ImageView();
				World.loadPlayer();

				//Affichage de l'animation du joueur
				affichageDuJoueur();
				
				//Affichage des toutes les couches de la map
				printCalqueTile(PaneGround,PaneSolid,PaneTop);



				for(int numCoeur=World.player.getMaxPv().intValue()/4;numCoeur>0;numCoeur--){
					coeurs.add(new ImageView(dicoImageItemTextureMap.get(2)));
				}

				updateHearts();

				for(ImageView coeur:coeurs){
					PaneHUD.getChildren().add(coeur);
				}

				World.player.getPV().addListener(new ChangeListener<Number>() {
					@Override
					public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
						updateHearts();
					}
				});

				//Ajout d'un Listener si la map change
				World.currentMap.getNameProperty().addListener(new ChangeListener<String>(){
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						PaneGround.getChildren().clear();
						PaneSolid.getChildren().clear();
						PaneTop.getChildren().clear();
						printCalqueTile(PaneGround,PaneSolid,PaneTop);
					}
				});

				World.loadGameLoop();
	}
	
	private void menuLoading() {
		try {
			menuImageFont.setImage(SwingFXUtils.toFXImage(ImageIO.read(new File("ressources/textures/background.png").toURI().toURL()),null));
			playImg.setImage(SwingFXUtils.toFXImage(ImageIO.read(new File("ressources/textures/playButton.png").toURI().toURL()),null));
			optionImg.setImage(SwingFXUtils.toFXImage(ImageIO.read(new File("ressources/textures/optionButton.png").toURI().toURL()),null));
			exitImg.setImage(SwingFXUtils.toFXImage(ImageIO.read(new File("ressources/textures/exitButton.png").toURI().toURL()),null));
			selectionArrow.setImage(SwingFXUtils.toFXImage(ImageIO.read(new File("ressources/textures/selectionArrow.png").toURI().toURL()),null));
			selectionArrow.relocate(playImg.getLayoutX()-60, playImg.getLayoutY());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void textureLoading() {
		dicoImageTileTextureMap = new HashMap<>();
		dicoImageItemTextureMap = new HashMap<>();
		dicoImageAnimationPlayer = new HashMap<>();
		dicoImageAnimationEntity = new HashMap<>();

		LoadDicoMap(dicoImageTileTextureMap,32,32,16,16,"TileTextureMap");
		LoadDicoMap(dicoImageItemTextureMap,32,32,16,16,"ItemTextureMap");
		LoadAnimation(dicoImageAnimationPlayer, 28, 4);


		coeurs = new ArrayList<ImageView>();
	}

	private void LoadDicoMap(Map<Integer,Image> dico,int imageWidthPixels, int imageHeightPixels, int imageWidth, int imageHeight, String textureMapName) {
		for(int x = 0; x < imageWidth*imageHeight; x++) {
			dico.put(x + 1,SwingFXUtils.toFXImage(TextureLoader.getTextureMapImage(textureMapName,imageWidthPixels,imageHeightPixels,imageWidth,imageHeight,x).getTexture(), null));
		}
	}
	private void LoadAnimation(Map<Integer,Image> dico, int frame, int animation) {
		for(int x = 0;x < frame;x++)
			for(int y = 0;y < animation;y++)
				dico.put(x + frame * y,SwingFXUtils.toFXImage(EntityLivingTexture.getEntityTexture("player", 24, 32, x, y).getTexture(), null));		
	}

	//Permet d'afficher dans dans chaque pane toute les textures de chaque couches de la map
	private void printCalqueTile(Pane pane,Pane paneTile,Pane paneTop) {

		for(int y=0;y<World.currentMap.getHeight();y++) {
			for(int x=0;x<World.currentMap.getWidth();x++) {

				ImageView tile = new ImageView(dicoImageTileTextureMap.get(World.currentMap.getTileTerrain(y, x).getId()));	
				tile.setX(x*32);
				tile.setY(y*32);
				pane.getChildren().add(tile);

				tile = new ImageView(dicoImageTileTextureMap.get(World.currentMap.getTile(y, x).getId()));
				tile.setX(x*32);
				tile.setY(y*32);
				paneTile.getChildren().add(tile);

				tile = new ImageView(dicoImageTileTextureMap.get(World.currentMap.getTileTop(y, x).getId()));
				tile.setX(x*32);
				tile.setY(y*32);
				paneTop.getChildren().add(tile);

			}
		}
	}

	private void updateHearts() {
		int pv = World.player.getPV().intValue();
		for(ImageView coeur:coeurs){
			int pvid = 0;

			if(pv >= 4)
				pvid = 6;
			else if(pv <= 0)
				pvid = 2;
			else
				pvid = pv + 2;
			coeur.setImage(dicoImageItemTextureMap.get(pvid));
			pv -= 4;
		}

		int coeurAt = 0;
		for(ImageView coeur:coeurs){
			coeur.relocate(coeurAt * 32 + 5, 5);
			coeurAt++;
		}

	}
	private void affichageDuJoueur() {
		player.setImage(SwingFXUtils.toFXImage(EntityLivingTexture.getEntityTexture("player", 24, 32, 0, 2).getTexture(), null));
		player.setFitWidth(32);
		player.setFitHeight(64);
		player.setX(World.player.coordonnes.getX());
		player.setY(World.player.coordonnes.getY());
		PlayerPane.getChildren().add(player);

		paneGame.layoutXProperty().bind(World.player.coordonnes.getXpro().multiply(-32).add(432));
		paneGame.layoutYProperty().bind(World.player.coordonnes.getYpro().multiply(-32).add(320));

		player.xProperty().bind(World.player.coordonnes.getXpro().multiply(32).subtract(16));
		player.yProperty().bind(World.player.coordonnes.getYpro().multiply(32).subtract(48));
	}
	private void affichageEntitys() {
		
		World.currentMap.entity.addListener(new ListChangeListener<Entity>(){

			@Override
			public void onChanged(Change<? extends Entity> c) {
				 while (c.next()) {
		                     for (Entity remEntity : c.getRemoved()) {
		                         
		                     }
		                     for (Entity addEntity : c.getAddedSubList()) {
		                    	 if(addEntity.getId() == -1) {
		                    		 addEntity.etatDeplacement.addListener(
		             						new ChangeListener<Number>() {

		             							@Override
		             							public void changed(ObservableValue<? extends Number> observable, Number oldValue,Number newValue) {

		             								switch(addEntity.direction.getDirection()) {
		             								case Direction.North:
		             									player.setImage(dicoImageAnimationPlayer.get((observable.getValue().intValue() / 3)));
		             									break;
		             								case Direction.West:
		             									player.setImage(dicoImageAnimationPlayer.get((observable.getValue().intValue() / 3)+28));
		             									break;
		             								case Direction.South:
		             									player.setImage(dicoImageAnimationPlayer.get((observable.getValue().intValue() / 3)+56));
		             									break;
		             								case Direction.East:
		             									player.setImage(dicoImageAnimationPlayer.get((observable.getValue().intValue() / 3)+84));
		             									break;
		             								}
		             							}
		             						}
		             						);
		                    	 }else {
		                    		 addEntity.etatDeplacement.addListener(
		             						new ChangeListener<Number>() {

		             							@Override
		             							public void changed(ObservableValue<? extends Number> observable, Number oldValue,Number newValue) {

		             								switch(addEntity.direction.getDirection()) {
		             								case Direction.North:
		             									player.setImage(
		             											dicoImageAnimationEntity.get(
		             													addEntity.getId())
		             											[observable.getValue().intValue() / 3]);
		             									break;
		             								case Direction.West:
		             									player.setImage(
		             											dicoImageAnimationEntity.get(
		             													addEntity.getId())
		             											[observable.getValue().intValue() / 3 + 28]);
		             									break;
		             								case Direction.South:
		             									player.setImage(
		             											dicoImageAnimationEntity.get(
		             													addEntity.getId())
		             											[observable.getValue().intValue() / 3 + 56]);
		             									break;
		             								case Direction.East:
		             									player.setImage(
		             											dicoImageAnimationEntity.get(
		             													addEntity.getId())
		             											[observable.getValue().intValue() / 3 + 84]);
		             									break;
		             								}
		             							}
		             						}
		             						);
		                    	 }
		                     }
		                 }
		             
			}
		});
	}
}
