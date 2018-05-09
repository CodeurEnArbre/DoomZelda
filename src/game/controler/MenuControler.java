package game.controler;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class MenuControler implements Initializable{


	Map<Integer,Image> dicoImageTileTextureMap;
	Map<Integer,Image> dicoImageItemTextureMap;
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
	private Pane PaneTop;


	private ImageView player;//l'image du joueur a l'ecran


	@FXML
	void option(ActionEvent event) {

	}

	@FXML
	void play(ActionEvent event) {


	}

	@FXML
	void quitter(ActionEvent event) {

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Chargement dans la memoire de toutes les textures
		textureLoading();		

		//Chargement de la map
		WorldLoader.loadWorld("TinyMap");

		//Chargement du joueur
		player=new ImageView();
		WorldLoader.loadPlayer();
		
		//Affichage des toutes les couches de la map
		printCalqueTile(PaneGround,PaneSolid,PaneTop);

		//Affichage du joueur
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

		Timeline GameLoop = new Timeline();
		GameLoop.setCycleCount(Timeline.INDEFINITE);
		KeyFrame keyf = new KeyFrame(
				Duration.seconds(0.017),
				e -> {
					
					if(WorldLoader.player.moveDown) {
						if(WorldLoader.player.moveLeft ^ WorldLoader.player.moveRight)
							WorldLoader.player.addY(0.2);
						else
							WorldLoader.player.addY(0.14);
					}

					if(WorldLoader.player.moveUP) {
						if(WorldLoader.player.moveLeft ^ WorldLoader.player.moveRight)
							WorldLoader.player.addY(-0.14);	
						else
							WorldLoader.player.addY(-0.2);	
					}
					if(WorldLoader.player.moveLeft) {
						if(WorldLoader.player.moveUP ^ WorldLoader.player.moveDown)
							WorldLoader.player.addX(-0.14);
						else
							WorldLoader.player.addX(-0.2);
					}

					if(WorldLoader.player.moveRight) {
						if(WorldLoader.player.moveUP ^ WorldLoader.player.moveDown)
							WorldLoader.player.addX(0.14);
						else
							WorldLoader.player.addX(0.2); 
					}
				});
		GameLoop.getKeyFrames().add(keyf);
		GameLoop.play();
	}
	private void LoadDicoMap(Map<Integer,Image> dico,int imageWidthPixels, int imageHeightPixels, int imageWidth, int imageHeight, String textureMapName) {
		for(int x = 0; x < imageWidth*imageHeight; x++) {
			dico.put(x + 1,SwingFXUtils.toFXImage(TextureLoader.getTextureMapImage(textureMapName,imageWidthPixels,imageHeightPixels,imageWidth,imageHeight,x).getTexture(), null));
		}
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

	private void textureLoading() {
		dicoImageTileTextureMap = new HashMap<>();
		dicoImageItemTextureMap = new HashMap<>();
		LoadDicoMap(dicoImageTileTextureMap,32,32,16,16,"TileTextureMap");
		LoadDicoMap(dicoImageItemTextureMap,32,32,16,16,"ItemTextureMap");
		coeurs = new ArrayList<ImageView>();
	}
	
	private void affichageDuJoueur() {
		player.setImage(SwingFXUtils.toFXImage(EntityLivingTexture.getEntityTexture("player", 24, 64, 0, 2).getTexture(), null));
		player.setFitWidth(32);
		player.setFitHeight(64);
		player.setX(WorldLoader.player.getCoordoner().getX());
		player.setY(WorldLoader.player.getCoordoner().getY());
		EntityPane.getChildren().add(player);

		paneGame.layoutXProperty().bind(WorldLoader.player.getCoordoner().getXpro().multiply(-32).add(432));
		paneGame.layoutYProperty().bind(WorldLoader.player.getCoordoner().getYpro().multiply(-32).add(320));

		player.xProperty().bind(WorldLoader.player.getCoordoner().getXpro().multiply(32).subtract(16));
		player.yProperty().bind(WorldLoader.player.getCoordoner().getYpro().multiply(32).subtract(48));
		
	

		WorldLoader.player.getOrientation().getDirectionProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				switch(observable.getValue().intValue()) {
				case Direction.North:
					player.setImage(SwingFXUtils.toFXImage(EntityLivingTexture.getEntityTexture("player", 24, 64, 0, 0).getTexture(), null));
					break;
				case Direction.West:
					player.setImage(SwingFXUtils.toFXImage(EntityLivingTexture.getEntityTexture("player", 24, 64, 0, 1).getTexture(), null));
					break;
				case Direction.South:
					player.setImage(SwingFXUtils.toFXImage(EntityLivingTexture.getEntityTexture("player", 24, 64, 0, 2).getTexture(), null));
					break;
				case Direction.East:
					player.setImage(SwingFXUtils.toFXImage(EntityLivingTexture.getEntityTexture("player", 24, 64, 0, 3).getTexture(), null));
					break;
				}
			}
		}); 
	}
}
