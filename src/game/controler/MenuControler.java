package game.controler;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import game.Main;
import game.modele.entity.Entity;
import game.modele.utils.Direction;
import game.modele.world.WorldLoader;
import game.vue.EntityLivingTexture;
import game.vue.TextureLoader;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class MenuControler implements Initializable{

	Map<Integer,Image> dicoImageTileTextureMap;
	Map<Integer,Image> dicoImageItemTextureMap;
	Map<Integer,Image> dicoImageAnimationPlayer;
	Map<Integer,Image[]> dicoImageAnimationEntity; 

	private Timeline GameLoop;

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
    private Pane Menu;

	private ImageView player;//l'image du joueur a l'ecran

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//Chargement dans la memoire de toutes les textures
		textureLoading();		

		//Chargement de la map
		WorldLoader.loadWorld("TinyMap",null);

		//Chargement du joueur
		player=new ImageView();
		WorldLoader.loadPlayer();

		//Affichage des toutes les couches de la map
		printCalqueTile(PaneGround,PaneSolid,PaneTop);

		//Initialisation de la liste d'entites et affichage des entit�es
		WorldLoader.Entitys = WorldLoader.getWorld().getEntity();
		affichageEntity(WorldLoader.Entitys);

		//Affichage de l'animation du joueur
		affichageDuJoueur();

		for(int numCoeur=WorldLoader.player.getMaxPv().intValue()/4;numCoeur>0;numCoeur--){
			coeurs.add(new ImageView(dicoImageItemTextureMap.get(2)));
		}

		updateHearts();

		for(ImageView coeur:coeurs){
			PaneHUD.getChildren().add(coeur);
		}

		WorldLoader.player.getPV().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				updateHearts();
			}
		});


		createGameLoop();
		addKeyGameLoop(getanimationPlayer());


		GameLoop.play();
		Main.entityUpdate=true;

		//Ajout d'un Listener si la map change
		WorldLoader.currentMap.getNameProperty().addListener(new ChangeListener<String>(){
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				PaneGround.getChildren().clear();
				PaneSolid.getChildren().clear();
				PaneTop.getChildren().clear();
				printCalqueTile(PaneGround,PaneSolid,PaneTop);
				System.out.println("World Texture Loading Terminer");
			}
		});
		

		

	}

	private void textureLoading() {
		dicoImageTileTextureMap = new HashMap<>();
		dicoImageItemTextureMap = new HashMap<>();
		dicoImageAnimationPlayer = new HashMap<>();
		dicoImageAnimationEntity = new HashMap<>();

		LoadDicoMap(dicoImageTileTextureMap,32,32,16,16,"TileTextureMap");
		LoadDicoMap(dicoImageItemTextureMap,32,32,16,16,"ItemTextureMap");
		LoadAnimation(dicoImageAnimationPlayer, 12, 4);


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

		for(int y=0;y<WorldLoader.currentMap.getHeight();y++) {
			for(int x=0;x<WorldLoader.currentMap.getWidth();x++) {

				ImageView tile = new ImageView(dicoImageTileTextureMap.get(WorldLoader.currentMap.getTileTerrain(y, x).getId()));	
				tile.setX(x*32);
				tile.setY(y*32);
				pane.getChildren().add(tile);

				tile = new ImageView(dicoImageTileTextureMap.get(WorldLoader.currentMap.getTile(y, x).getId()));
				tile.setX(x*32);
				tile.setY(y*32);
				paneTile.getChildren().add(tile);

				tile = new ImageView(dicoImageTileTextureMap.get(WorldLoader.currentMap.getTileTop(y, x).getId()));
				tile.setX(x*32);
				tile.setY(y*32);
				paneTop.getChildren().add(tile);

			}
		}
	}

	//Create game Loop du jeu
	private void createGameLoop() {
		GameLoop = new Timeline();
		GameLoop.setCycleCount(Timeline.INDEFINITE);
	}

	//Ajoute des fonctions qui seront �x�cut� dans la gameloop
	private void addKeyGameLoop(EventHandler<ActionEvent> e) {
		KeyFrame keyf = new KeyFrame(Duration.seconds(0.017),e);
		GameLoop.getKeyFrames().add(keyf);
	}

	private final static EventHandler<ActionEvent> getanimationPlayer() {
		return new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if(!WorldLoader.player.moveDown.active && !WorldLoader.player.moveUP.active && !WorldLoader.player.moveLeft.active && !WorldLoader.player.moveRight.active) {
					WorldLoader.player.resetAnim();
					WorldLoader.player.speed = WorldLoader.player.baseSpeed;
				}
				if(WorldLoader.player.moveDown.active) {
					if(WorldLoader.player.moveLeft.active ^ WorldLoader.player.moveRight.active) {
						WorldLoader.player.addY(WorldLoader.player.speed * 2/3);
					}
					else {
						if(WorldLoader.player.speed < WorldLoader.player.maxSpeed) {
							WorldLoader.player.speed += WorldLoader.player.acce;
						}
						WorldLoader.player.addY(WorldLoader.player.speed);
						WorldLoader.player.incAnim();
					}
				}
				if(WorldLoader.player.moveUP.active) {
					if(WorldLoader.player.moveLeft.active ^ WorldLoader.player.moveRight.active)
					{	
						WorldLoader.player.addY(-WorldLoader.player.speed * 2/3);	
					}else
					{
						if(WorldLoader.player.speed < WorldLoader.player.maxSpeed) {
							WorldLoader.player.speed += WorldLoader.player.acce;
						}
						WorldLoader.player.addY(-WorldLoader.player.speed);	
						WorldLoader.player.incAnim();
					}
				}
				if(WorldLoader.player.moveLeft.active) {
					if(WorldLoader.player.moveUP.active ^ WorldLoader.player.moveDown.active)
					{
						WorldLoader.player.addX(-WorldLoader.player.speed * 2/3);
						WorldLoader.player.incAnim();
					}		else
					{	
						if(WorldLoader.player.speed < WorldLoader.player.maxSpeed) {
							WorldLoader.player.speed += WorldLoader.player.acce;
						}
						WorldLoader.player.addX(-WorldLoader.player.speed);
						WorldLoader.player.incAnim();
					}
				}
				if(WorldLoader.player.moveRight.active) {
					if(WorldLoader.player.moveUP.active ^ WorldLoader.player.moveDown.active)
					{
						WorldLoader.player.addX(WorldLoader.player.speed * 2/3);
						WorldLoader.player.incAnim();
					}else
					{
						if(WorldLoader.player.speed < WorldLoader.player.maxSpeed) {
							WorldLoader.player.speed += WorldLoader.player.acce;
						}
						WorldLoader.player.addX(WorldLoader.player.speed); 
						WorldLoader.player.incAnim();
					}
				}
			}
		};
	}

	private void updateHearts() {
		int maxPv = WorldLoader.player.getMaxPv().intValue();
		int pv = WorldLoader.player.getPV().intValue();
		System.out.println(pv);
		for(ImageView coeur:coeurs){
			int pvid=0;

			if(pv>=4)
				pvid=6;
			else if(pv<=0)
				pvid=2;
			else
				pvid=pv+2;
			coeur.setImage(dicoImageItemTextureMap.get(pvid));
			pv-=4;
		}

		int coeurAt=0;
		for(ImageView coeur:coeurs){
			coeur.relocate(coeurAt*32+5, 5);
			coeurAt++;
		}

	}

	private void affichageEntity(ArrayList<Entity> e) {
		for(Entity entity : e) {
			entity.getimageView().setImage(SwingFXUtils.toFXImage(EntityLivingTexture.getEntityTexture(entity.getTextureFile(), entity.getTextureWidth(), entity.getTextureHeight(), entity.getTextureX(), entity.getTextureY()).getTexture(), null));
			entity.getimageView().setFitWidth(32);
			entity.getimageView().setFitHeight(64);
			entity.getimageView().setX(entity.getX());
			entity.getimageView().setX(entity.getY());

			EntityPane.getChildren().add(entity.getimageView());
		}
	}

	private void affichageDuJoueur() {
		player.setImage(SwingFXUtils.toFXImage(EntityLivingTexture.getEntityTexture("player", 24, 32, 0, 2).getTexture(), null));
		player.setFitWidth(32);
		player.setFitHeight(64);
		player.setX(WorldLoader.player.getCoordoner().getX());
		player.setY(WorldLoader.player.getCoordoner().getY());
		PlayerPane.getChildren().add(player);

		paneGame.layoutXProperty().bind(WorldLoader.player.getCoordoner().getXpro().multiply(-32).add(432));
		paneGame.layoutYProperty().bind(WorldLoader.player.getCoordoner().getYpro().multiply(-32).add(320));

		player.xProperty().bind(WorldLoader.player.getCoordoner().getXpro().multiply(32).subtract(16));
		player.yProperty().bind(WorldLoader.player.getCoordoner().getYpro().multiply(32).subtract(48));

		WorldLoader.player.getAnimationProperty().addListener(
				new ChangeListener<Number>() {

					@Override
					public void changed(ObservableValue<? extends Number> observable, Number oldValue,Number newValue) {

						switch(WorldLoader.player.getOrientation().getDirection()) {
						case Direction.North:
							player.setImage(dicoImageAnimationPlayer.get((observable.getValue().intValue() / 3)));
							break;
						case Direction.West:
							player.setImage(dicoImageAnimationPlayer.get((observable.getValue().intValue() / 3)+12));
							break;
						case Direction.South:
							player.setImage(dicoImageAnimationPlayer.get((observable.getValue().intValue() / 3)+24));
							break;
						case Direction.East:
							player.setImage(dicoImageAnimationPlayer.get((observable.getValue().intValue() / 3)+36));
							break;
						}
					}
				}
				);
	}
}
