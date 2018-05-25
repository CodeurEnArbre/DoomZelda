package game.controler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import game.modele.entity.Entity;
import game.modele.entity.living.EntityLiving;
import game.modele.entity.tileEntity.EntityLight;
import game.modele.entity.tileEntity.TileEntity;
import game.modele.menu.Menu;
import game.modele.menu.OptionsMenu;
import game.modele.utils.Direction;
import game.modele.world.World;
import game.vue.EntityLivingTexture;
import game.vue.TextureLoader;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

@SuppressWarnings("unlikely-arg-type")
public class MenuControler implements Initializable{

	Map<Integer,Image> dicoImageTileTextureMap;
	Map<Integer,Image> dicoImageItemTextureMap;
	Map<Integer,Image> dicoImageAnimationPlayer;
	Map<Integer,Image[]> dicoImageAnimationEntity; 
	Map<Entity,ImageView> listEntityView = new HashMap<>();
	Map<Integer,Image> dicoShadow;
	
	
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
	private Pane PaneSolid;
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
	private Pane PaneOptions;
	
	@FXML
	private Pane inventoryMenu;
	
	@FXML
	private Pane homeMenu;

	@FXML
	private ImageView menuImageFont;

	@FXML
	private ImageView selectorMain;

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
    private ImageView selectorInOption;
    @FXML
    private ImageView fowardImg;
    @FXML
    private ImageView temoinAssignation;
    @FXML
    private ImageView resetDefaultImg;
    @FXML
    private Label KeyName1;
    @FXML
    private Label KeyName2;
    @FXML
    private Label KeyName3;
    @FXML
    private Label KeyName4;
    
	@FXML
	private Button buttonReprendre;
	
	@FXML
	private ImageView player;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//chagement des menus
		loadMenus();
		
		//chargement des options
		loadOptions();
		
		//Listener si un monde est charger
		World.isWorldLoaded.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				
				if(newValue) {
					homeMenu.setOpacity(0);
					System.out.println("Loading save");
					loadMapTexture();
					
				}else {
					System.out.println("Return to menu");
					PaneGround.getChildren().clear();
					PaneSolid.getChildren().clear();
					PaneTop.getChildren().clear();
					EntityPane.getChildren().clear();
					PlayerPane.getChildren().clear();
					PaneHUD.getChildren().clear();
					coeurs.clear();
					homeMenu.setOpacity(1);
					Menu.currentMenu.set(Menu.MainMenuID);
				}
			}});
		
		//Chargement dans la memoire de toutes les textures
		textureLoading();		
	}
	
	public void loadMenus() {
		//Listener de la selection des boutons des Menus en x
		Menu.selectedButtonX.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if( Menu.currentMenu.get() == Menu.InventoryMenuID ) {
					//TODO
				}
				
			}
			
		});
		
		//Listener de la selection des boutons des Menus en y
		Menu.selectedButtonY.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				switch (Menu.currentMenu.get()) {
				
				case Menu.MainMenuID:
					selectorMain.relocate(playImg.getLayoutX(), playImg.getLayoutY()+120*newValue.intValue());
					break;
					
				case Menu.InGameMenuID:
					selectorPauseInGame.relocate(optionInGameImg.getLayoutX(), optionInGameImg.getLayoutY()+120*newValue.intValue());
					break;
					
				case Menu.OptionsMenuID:
					if(newValue.intValue() == 4) {
						selectorInOption.relocate(resetDefaultImg.getLayoutX(), resetDefaultImg.getLayoutY()+10);
					}else {
						selectorInOption.relocate(fowardImg.getLayoutX(), fowardImg.getLayoutY()+70*newValue.intValue());
					}
					break;
				}
				
			}});

		

		//Listener de changement de menu
		Menu.currentMenu.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				
				Menu.selectedButtonY.set(0);
				selectorMain.relocate(playImg.getLayoutX(), playImg.getLayoutY()+120*Menu.selectedButtonY.intValue());
				selectorPauseInGame.relocate(optionInGameImg.getLayoutX(), optionInGameImg.getLayoutY()+120*Menu.selectedButtonY.intValue());
				selectorInOption.relocate(fowardImg.getLayoutX(), fowardImg.getLayoutY()+70*Menu.selectedButtonY.intValue());
				
				switch(newValue.intValue()) {
				case Menu.OptionsMenuID:
					PaneOptions.setOpacity(1);
					PaneMenu.setOpacity(0);
					break;
					
				case Menu.InGameMenuID:
					PaneOptions.setOpacity(0);
					PaneMenu.setOpacity(1);
					break;
				
				case Menu.InventoryMenuID:
					inventoryMenu.setOpacity(1);
					break;
					
				default :
					PaneOptions.setOpacity(0);
					PaneMenu.setOpacity(0);
					inventoryMenu.setOpacity(0);
					break;
				
				}

			}});	
		
		//Listener du temoin graphique d'assignation des touches
		OptionsMenu.enterOption.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {
					
					temoinAssignation.setOpacity(1);
				}else {
					temoinAssignation.setOpacity(0);
				}
				
			}});
	}

	private void loadOptions() {
		
		try {
			BufferedReader optionsData = new BufferedReader(new FileReader(new File("ressources/saves/options.txt").getAbsolutePath()));
			Pattern pat = Pattern.compile(",");
			String[] optionLine;
			optionLine = pat.split(optionsData.readLine());
			for(int option=0; option < optionLine.length ;option++) {
				Menu.selectedButtonY.set(option);
				OptionsMenu.setBind(KeyCode.valueOf(optionLine[option]));
			}
			Menu.selectedButtonY.set(0);
			optionsData.close();
		} catch (IOException e) {
			OptionsMenu.SaveKeyBinding();
		}
		
		//Bind des valeurs d'assignation des touches
		KeyName1.textProperty().bind(OptionsMenu.upKey);
		KeyName2.textProperty().bind(OptionsMenu.downKey);
		KeyName3.textProperty().bind(OptionsMenu.rightKey);
		KeyName4.textProperty().bind(OptionsMenu.leftKey);
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
		shadowTopPane.getChildren().clear();
		if(!World.currentMap.isOutside())
		for(int x=0; x < World.currentMap.getWidth() ;x++) {
			for(int y=0; y < World.currentMap.getHeight() ;y++) {
					createShadow(shadowTopPane, World.currentMap.getShadow(x, y),x,y);
				}
		}
	}
	
	private void createShadow(Pane pane, IntegerProperty shadow, int x, int y) {
		ImageView i = new ImageView();
		i.relocate(x * 32, y * 32);
		i.setImage(dicoShadow.get(0));
		i.minHeight(32);
		i.minWidth(32);
		i.maxHeight(32);
		i.maxWidth(32);
		shadow.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				i.setImage(dicoShadow.get(observable.getValue().intValue() <= 15 ? observable.getValue() : 15));
			}
		});		
		
		pane.getChildren().add(i);
	}
	
	private void textureLoading() {
		dicoImageTileTextureMap = new HashMap<>();
		dicoImageItemTextureMap = new HashMap<>();
		dicoImageAnimationPlayer = new HashMap<>();
		dicoImageAnimationEntity = new HashMap<>();
		dicoShadow = new HashMap<>();
		
		LoadDicoMap(dicoImageTileTextureMap,32,32,16,16,"TileTextureMap");
		LoadDicoMap(dicoImageItemTextureMap,32,32,16,16,"ItemTextureMap");
		loadAnimationPlayer(dicoImageAnimationPlayer, 28, 4);
		
		coeurs = new ArrayList<>();
		
		for(int i = 0; i < 16;i++)
		
		dicoShadow.put(i,SwingFXUtils.toFXImage(
				EntityLivingTexture.getEntityTexture("darkness",32,32,i,0).getTexture(), null));
		
		
		
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
	
//	private void loadAnimation(Map<Integer,Image[]> dico, int frame, int animation) {
//		for(int entity=0;entity<3;entity++) {
//			Image[] imgs=new Image[frame*animation];
//			for(int x = 0;x < frame;x++)
//				for(int y = 0;y < animation;y++)
//					imgs[x + frame * y]=SwingFXUtils.toFXImage(EntityLivingTexture.getEntityTexture("Zombie", 32, 48, x, y).getTexture(), null);	
//			dico.put(entity,imgs);
//		}
//	}

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
			if(e instanceof EntityLiving)
				i.setImage(SwingFXUtils.toFXImage(EntityLivingTexture.getEntityTexture(e.getId(), 32, 48, 0, 0).getTexture(), null));
			else if(e instanceof EntityLight)
				i.setImage(SwingFXUtils.toFXImage(EntityLivingTexture.getEntityTexture(e.getId(), 32, 112, 1, 0).getTexture(), null));
			EntityPane.getChildren().add(i);
		}

	}
	
	private ImageView getEntityImageView(Entity e) {
		ImageView img = null;
		for(ImageView imageView:listEntityView.values())
			if(imageView.getId().equals(e.getId())) {
				return imageView;
			}
		
		return img;
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
				
				listEntityView.remove(img);
			}
			
		}
		
		//Cree les imageviews des entites
		for(Entity entity:World.currentMap.getEntity()) {
			if(entity != null) {
				affichageEntity(new ImageView(), entity);
				if(entity instanceof TileEntity) {
					((TileEntity) entity).getEtatProperty().addListener(new ChangeListener<Boolean>() {

						@Override
						public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
								Boolean newValue) {			
							ImageView entityImg = getEntityImageView(entity);
							if(((TileEntity) entity).getEtat() && entityImg != null)
								entityImg.setImage(SwingFXUtils.toFXImage(EntityLivingTexture.getEntityTexture(entity.getId(), 32, 112, 1, 0).getTexture(), null));
					//		else
					//Bug			entityImg.setImage(SwingFXUtils.toFXImage(EntityLivingTexture.getEntityTexture(entity.getId(), 32, 112, 0, 0).getTexture(), null));
							
						}
						
					});
				}
			}
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
