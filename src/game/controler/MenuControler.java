package game.controler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import game.InGameMenu;
import game.MainMenu;
import game.modele.entity.Entity;
import game.modele.tile.Tile;
import game.modele.tile.tileGround.tileVoid;
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

@SuppressWarnings("unlikely-arg-type")
public class MenuControler implements Initializable{

	Map<Integer,Image> dicoImageTileTextureMap;
	Map<Integer,Image> dicoImageItemTextureMap;
	Map<Integer,Image> dicoImageAnimationPlayer;
	Map<Integer,Image[]> dicoImageAnimationEntity; 
	Map<Entity,ImageView> listEntityView = new HashMap<>();

	ArrayList<ImageView> coeurs;
	Image shadowImg;

	@FXML
	private Pane paneWindow;//Main avec tout les autres pane dedans
	@FXML
	private Pane paneGame;//Contient les panes d'affichage de la map et des entites
	@FXML 
	private Pane PaneHUD;//Permet d'afficher l'HUD et tout ce qui n'est pas une tile ou une entite

	@FXML
	private Pane PaneGround;
	@FXML
    private Pane shadowBackgroundPane;
	@FXML
	private Pane PaneSolid;
	@FXML
    private Pane shadowSolidPane;
	@FXML
	private Pane EntityPane;
	@FXML
	private Pane PlayerPane;
	@FXML
	private Pane PaneTop;
	@FXML
    private Pane shadowTopPane;

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
	private ImageView selectorPauseInGame;
    @FXML
    private ImageView optionInGameImg;
    @FXML
    private ImageView saveInGameImg;
    @FXML
    private ImageView quitInGameImg;

	@FXML
	private Button buttonReprendre;
	
	@FXML
	private ImageView player;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//chagement du menu principale
		menuLoading();
		
		//
		World.onPause.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {
					PaneMenu.setOpacity(1.0);				
				}
				else {
					PaneMenu.setOpacity(0.0);
				}
			}

		});

		//Listener de la fleche des menu
		MainMenu.selectedButton.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				selectionArrow.relocate(playImg.getLayoutX()-60, playImg.getLayoutY()+120*newValue.intValue());
			}});
		
		//Listener de la fleche du menu ingame
		InGameMenu.selectedButtonInGame.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				System.out.println(selectorPauseInGame.getLayoutY());
				selectorPauseInGame.relocate(optionInGameImg.getLayoutX(), optionInGameImg.getLayoutY()+120*newValue.intValue());
				System.out.println(selectorPauseInGame.getLayoutY());
			}});

		World.isWorldLoaded.addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				homeMenu.setOpacity(0);
				System.out.println("Loading save");
				if(newValue) {
					loadMapTexture();				
				}}});
		
		//Chargement dans la memoire de toutes les textures
		textureLoading();		
	}

	public void loadMapTexture() {
		
		//Affichage de toutes les couches de la map
		printCalqueTile(PaneGround,PaneSolid,PaneTop);

		//Chargement du joueur
		World.loadPlayer();
		
		//Chargement des ImageView des entites
		affichageEntitys();
		
		//Chargement de l'HUD
		hudLoading();
		
		//Ajout de l'affichage des ombres si la map est a l'interieur
		affichageOmbres();
		
		//Ajout d'un Listener si la map change
		World.currentMap.getNameProperty().addListener(new ChangeListener<String>(){
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				PaneGround.getChildren().clear();
				PaneSolid.getChildren().clear();
				PaneTop.getChildren().clear();
				EntityPane.getChildren().clear();
				for(ImageView entity:listEntityView.values()) {
					if(entity.getId()!="Player")
						listEntityView.remove(entity);
				}
				printCalqueTile(PaneGround,PaneSolid,PaneTop);		
				affichageEntitys();
				affichageOmbres();
			}
		});

		//Demarage des la gameloop
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
	
	private void hudLoading() {
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
	}
	
	private void affichageOmbres() {
		shadowBackgroundPane.getChildren().clear();
		shadowSolidPane.getChildren().clear();
		shadowTopPane.getChildren().clear();
		if(!World.currentMap.isOutside())
		for(int x=0; x < World.currentMap.getWidth() ;x++) {
			for(int y=0; y < World.currentMap.getWidth() ;y++) {
				boolean isShadow=false;
				
				isShadow=createShadow(shadowTopPane, World.currentMap.getTileTop(x, y),x,y);
				
				if(!isShadow)
					isShadow=createShadow(shadowSolidPane, World.currentMap.getTile(x, y),x,y);
				
				if(!isShadow)
					isShadow=createShadow(shadowBackgroundPane, World.currentMap.getTileTerrain(x, y),x,y);	
			}
		}
	}
	
	private boolean createShadow(Pane pane, Tile tile, int x, int y) {
		if(!(tile instanceof tileVoid)) {
			ImageView shadow = new ImageView();
			shadow.setImage(shadowImg);
			shadow.minWidth(32);shadow.maxWidth(32);shadow.minHeight(32);shadow.maxHeight(32);
			shadow.relocate(y*32, x*32);
			shadow.setOpacity((4-World.currentMap.getTile(x, y).light.doubleValue())/Tile.Max_Light);
			tile.light.addListener(new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
					shadow.setOpacity(newValue.doubleValue()/4);
				}
			});
			pane.getChildren().add(shadow);
			return true;
		}
		return false;
	}
	
	private void textureLoading() {
		dicoImageTileTextureMap = new HashMap<>();
		dicoImageItemTextureMap = new HashMap<>();
		dicoImageAnimationPlayer = new HashMap<>();
		dicoImageAnimationEntity = new HashMap<>();

		LoadDicoMap(dicoImageTileTextureMap,32,32,16,16,"TileTextureMap");
		LoadDicoMap(dicoImageItemTextureMap,32,32,16,16,"ItemTextureMap");
		loadAnimationPlayer(dicoImageAnimationPlayer, 28, 4);
		//TODO loadAnimation(dicoImageAnimationEntity, 28,4);
		try {
			shadowImg=SwingFXUtils.toFXImage(ImageIO.read(new File("ressources/textures/shadow.png").toURI().toURL()),null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		coeurs = new ArrayList<ImageView>();
	}

	private void LoadDicoMap(Map<Integer,Image> dico,int imageWidthPixels, int imageHeightPixels, int imageWidth, int imageHeight, String textureMapName) {
		for(int x = 0; x < imageWidth*imageHeight; x++) {
			dico.put(x + 1,SwingFXUtils.toFXImage(TextureLoader.getTextureMapImage(textureMapName,imageWidthPixels,imageHeightPixels,imageWidth,imageHeight,x).getTexture(), null));
		}
	}
	
	private void loadAnimationPlayer(Map<Integer,Image> dico, int frame, int animation) {
		for(int x = 0;x < frame;x++)
			for(int y = 0;y < animation;y++)
				dico.put(x + frame * y,SwingFXUtils.toFXImage(EntityLivingTexture.getEntityTexture("Player", 24, 32, x, y).getTexture(), null));		
	}
	
	private void loadAnimation(Map<Integer,Image[]> dico, int frame, int animation) {
		for(int entity=0;entity<3;entity++) {//TODO
			Image[] imgs=new Image[frame*animation];
			for(int x = 0;x < frame;x++)
				for(int y = 0;y < animation;y++)
					imgs[x + frame * y]=SwingFXUtils.toFXImage(EntityLivingTexture.getEntityTexture("Zombie", 32, 48, x, y).getTexture(), null);	
			dico.put(entity,imgs);
		}
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
	private void affichageEntity(ImageView i,Entity e) {
		i = new ImageView();
		System.out.println(e+" added");
		listEntityView.put(e,i);
		i.setId(""+e.primaryKey);
		i.setFitWidth(32);
		i.setFitHeight(64);
		i.setX(World.player.coordonnes.getX());
		i.setY(World.player.coordonnes.getY());
		
		i.xProperty().bind(e.coordonnes.getXpro().multiply(32).subtract(16));
		i.yProperty().bind(e.coordonnes.getYpro().multiply(32).subtract(48));
		
		if(e.getId().equals("Player")) {
			i.setImage(SwingFXUtils.toFXImage(EntityLivingTexture.getEntityTexture(e.getId(), 24, 32, 0, 2).getTexture(), null));
			paneGame.layoutXProperty().bind(e.coordonnes.getXpro().multiply(-32).add(432));
			paneGame.layoutYProperty().bind(e.coordonnes.getYpro().multiply(-32).add(320));
			e.etatDeplacement.addListener(
					new ChangeListener<Number>() {

						@Override
						public void changed(ObservableValue<? extends Number> observable, Number oldValue,Number newValue) {

							switch(e.direction.getDirection()) {
							case Direction.North:
								listEntityView.get(e).setImage(dicoImageAnimationPlayer.get((observable.getValue().intValue() / 3)));
								break;
							case Direction.West:
								listEntityView.get(e).setImage(dicoImageAnimationPlayer.get((observable.getValue().intValue() / 3)+28));
								break;
							case Direction.South:
								listEntityView.get(e).setImage(dicoImageAnimationPlayer.get((observable.getValue().intValue() / 3)+56));
								break;
							case Direction.East:
								listEntityView.get(e).setImage(dicoImageAnimationPlayer.get((observable.getValue().intValue() / 3)+84));
								break;
							}
						}
					}
					);
			PlayerPane.getChildren().add(i);
		}else {
			i.setImage(SwingFXUtils.toFXImage(EntityLivingTexture.getEntityTexture(e.getId(), 32, 48, 0, 0).getTexture(), null));
			EntityPane.getChildren().add(i);
		}

	}

	private void affichageEntitys() {	
		//Supprime tout les imageview d'entites inutilisee
		for(ImageView img : listEntityView.values()) {
			boolean found = false;
			for(Entity entity : World.currentMap.getEntity()) {
				if(img.getId().equals(entity.primaryKey)) {
					found=true;
					break;
				}
			}
			if(!found) {
				System.out.println(img+" removed");
				listEntityView.remove(img);
			}
			
		}
		
		//Cree les imageviews des entites
		for(Entity entity:World.currentMap.getEntity()) {
			if(entity != null)
				affichageEntity(new ImageView(), entity);
		}
		
		World.currentMap.entity.addListener(new ListChangeListener<Entity>(){

			@Override
			public void onChanged(Change<? extends Entity> c) {
				while (c.next()) {
					
					for (Entity addEntity : c.getAddedSubList()) {

						if(!addEntity.getId().equals("Player")){
						
							affichageEntity(listEntityView.get(addEntity),addEntity);
							addEntity.etatDeplacement.addListener(
									new ChangeListener<Number>() {

										@Override
										public void changed(ObservableValue<? extends Number> observable, Number oldValue,Number newValue) {

											switch(addEntity.direction.getDirection()) {
											case Direction.North:
												listEntityView.get(addEntity).setImage(
														dicoImageAnimationEntity.get(
																addEntity.getId())
														[observable.getValue().intValue() / 3]);
												break;
											case Direction.West:
												listEntityView.get(addEntity).setImage(
														dicoImageAnimationEntity.get(
																addEntity.getId())
														[observable.getValue().intValue() / 3 + 28]);
												break;
											case Direction.South:
												listEntityView.get(addEntity).setImage(
														dicoImageAnimationEntity.get(
																addEntity.getId())
														[observable.getValue().intValue() / 3 + 56]);
												break;
											case Direction.East:
												listEntityView.get(addEntity).setImage(
														dicoImageAnimationEntity.get(
																addEntity.getId())
														[observable.getValue().intValue() / 3 + 84]);
												break;
											}
											
								}});
						}}}

			}
		});
	}

}
