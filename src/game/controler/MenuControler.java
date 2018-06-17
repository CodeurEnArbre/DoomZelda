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

import javax.imageio.ImageIO;

import game.modele.entity.Entity;
import game.modele.entity.EntityItemOnGround;
import game.modele.entity.living.Actions;
import game.modele.entity.living.EntityLiving;
import game.modele.entity.living.MemberPart;
import game.modele.entity.living.Player;
import game.modele.entity.living.monster.boss.Boss;
import game.modele.entity.projectile.Projectile;
import game.modele.entity.tileEntity.TileEntity;
import game.modele.entity.tileEntity.carriable.CarriableEntity;
import game.modele.entity.tileEntity.chest.Chest;
import game.modele.entity.tileEntity.light.EntityLight;
import game.modele.item.Item;
import game.modele.item.weapon.Weapon;
import game.modele.menu.InventoryMenu;
import game.modele.menu.Menu;
import game.modele.menu.OptionsMenu;
import game.modele.utils.Direction;
import game.modele.world.World;
import game.vue.EntityLivingTexture;
import game.vue.TextureLoader;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class MenuControler implements Initializable{

	//Les map de toute les textures
	Map<Integer,Image> dicoImageTileTextureMap;
	Map<Integer,Image> dicoImageItemTextureMap;
	Map<Integer,Image> dicoImageTileEntityMap;
	Map<Integer,Image> dicoImageProjectileMap;
	static Map<Integer,Image> dicoImageAnimationPlayer;
	Map<String,Map<Integer,Image>> dicoImageAnimationEntity;
	static Map<Entity,ImageView> listEntityView = new HashMap<>();
	Map<Integer,Image> dicoShadow;

	ArrayList<ImageView> coeurs;
	Image shadowImg;

	ImageView carriableEntity = new ImageView();

	//Utils
	public IntegerProperty clignotement = new SimpleIntegerProperty(0);
	public static boolean croissant = true;
	public static int opacityVariationMax = 8;
	public static int pickupItemAnim = 0;

	private ObservableList<Property> entityProperty = FXCollections.observableArrayList();

	private ObservableList<ChangeListener> entityListener = FXCollections.observableArrayList();

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
	private Pane ArmePane;
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

	private Label[] KeyName = new Label[OptionsMenu.keyName.length];
	private Label[] KeyFonctionName = new Label[OptionsMenu.keyFunctionName.length];

	//HUD
	public static Label rubys = new Label();
	private ImageView ruby = new ImageView();
	private ImageView equipSlots = new ImageView();
	private ImageView leftItemEquip = new ImageView();
	private ImageView rightItemEquip = new ImageView();

	//Inventory
	private ImageView inventorySelector= new ImageView();
	private Image inventorySelector1;
	private Image inventorySelector2;
	private Image inventorySelector3;
	private ImageView inventoryTypeSelector = new ImageView();
	private Label selectedName = new Label();

	public static ImageView pickupItem = new ImageView();

	@FXML
	private Pane PaneConsomables;
	@FXML
	private Pane PaneItems;
	@FXML
	private Pane PaneWeapons;

	@FXML
	private Button buttonReprendre;

	@FXML
	public static ImageView player;

	@Override
	public void initialize(URL location, ResourceBundle resources) {	
		//chargement des options
		loadOptions();

		//chagement des menus
		loadMenus();

		//Chargement dans la memoire de toutes les textures
		textureLoading();	

		//Listener si un monde est charger
		World.isWorldLoaded.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

				if(newValue) {
					homeMenu.setOpacity(0);
					System.out.println("Loading save");
					loadMapTexture();
					World.addKeyGameLoop(y -> GraphiqueLoop());

				}else {
					System.out.println("Return to menu");
					PaneGround.getChildren().clear();
					PaneSolid.getChildren().clear();
					PaneTop.getChildren().clear();
					EntityPane.getChildren().clear();
					ArmePane.getChildren().clear();
					PlayerPane.getChildren().clear();
					PaneHUD.getChildren().clear();
					coeurs.clear();
					homeMenu.setOpacity(1);
					PaneMenu.setOpacity(0);
					Menu.currentMenu.set(Menu.MainMenuID);
				}
			}});	
	}

	public void loadMenus() {
		inventoryTypeSelector.relocate(652, 382);

		selectedName.relocate(550, 298);
		selectedName.setFont(Font.font("Impact",25));
		selectedName.setStyle("-fx-font-weight: bold;");

		inventoryMenu.getChildren().add(inventorySelector);
		inventoryMenu.getChildren().add(inventoryTypeSelector);
		inventoryMenu.getChildren().add(selectedName);

		//Creation et binding des Labels des touches
		for(int key=0; key < KeyName.length;key++) {
			KeyName[key] = new Label();
			if(key<4)
				KeyName[key].relocate(350, 170+(key*70));
			else
				KeyName[key].relocate(650, 170+((key-4)*70));
			KeyName[key].setMinWidth(150);
			KeyName[key].setMinHeight(50);
			KeyName[key].setStyle("-fx-font-weight: bold;");
			KeyName[key].setTextFill(Paint.valueOf("WHITE"));
			KeyName[key].setFont(Font.font("Impact",22));
			KeyName[key].textProperty().bind(OptionsMenu.keyName[key]);
			PaneOptions.getChildren().add(KeyName[key]);
		}

		//Creation des Labels des noms des touches
		for(int keyName=0; keyName < KeyFonctionName.length;keyName++) {
			KeyFonctionName[keyName] = new Label();
			String name = OptionsMenu.keyFunctionName[keyName];
			KeyFonctionName[keyName].setText(name);
			KeyFonctionName[keyName].setMinWidth(200);
			KeyFonctionName[keyName].setMinHeight(50);
			KeyFonctionName[keyName].setTextFill(Paint.valueOf("WHITE"));

			if(OptionsMenu.keyFunctionName[keyName].equals("Reinitializer")) {
				KeyFonctionName[keyName].setFont(Font.font("Impact",22));
				KeyFonctionName[keyName].relocate(690, 553);
			}else {
				KeyFonctionName[keyName].setStyle("-fx-font-weight: bold;");
				KeyFonctionName[keyName].setFont(Font.font("Impact",22));
				if(keyName<4)
					KeyFonctionName[keyName].relocate(200, 170+(keyName*70));
				else
					KeyFonctionName[keyName].relocate(450, 170+((keyName-4)*70));
			}
			PaneOptions.getChildren().add(KeyFonctionName[keyName]);
		}

		//Listener de la selection des boutons des Menus en x
		Menu.selectedButtonX.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				menuSelection();
			}});

		//Listener de la selection des boutons des Menus en y
		Menu.selectedButtonY.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				menuSelection();	
			}});



		//Listener de changement de menu
		Menu.currentMenu.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

				selectorMain.relocate(playImg.getLayoutX(), playImg.getLayoutY()+120*Menu.selectedButtonY.intValue());

				switch(newValue.intValue()) {

				case Menu.MainMenuID:
					Menu.selectedButtonY.set(0);
					PaneOptions.setOpacity(0);
					break;

				case Menu.OptionsMenuID:
					selectorInOption.relocate(200, 170+70*Menu.selectedButtonY.intValue());
					Menu.selectedButtonY.set(0);
					PaneOptions.setOpacity(1);
					PaneMenu.setOpacity(0);
					break;

				case Menu.InGameMenuID:
					selectorPauseInGame.relocate(optionInGameImg.getLayoutX(), optionInGameImg.getLayoutY()+120*Menu.selectedButtonY.intValue());
					Menu.selectedButtonY.set(0);
					PaneOptions.setOpacity(0);
					PaneMenu.setOpacity(1);
					break;

				case Menu.InventoryMenuID:
					inventorySelector.setImage(inventorySelector2);
					inventorySelector.relocate(652, 382);
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
				if(newValue)			
					temoinAssignation.setOpacity(1);
				else
					temoinAssignation.setOpacity(0);


			}});

		//Listener de l'ajout d'un item
		InventoryMenu.newItem.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {	
					int i = World.player.nbWeapon;
					PaneWeapons.getChildren().add(createItemView(World.player.weapons[i].getItemName(), 10 + 73 * (i%8) , 14 + 70 * Math.abs(i/8), 50, 50));
					InventoryMenu.newItem.set(false);

				}			
			}
		}
				);


		//Listener changement de pane dans le menu item
		InventoryMenu.InventoryZone.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				PaneWeapons.setOpacity(0);
				PaneItems.setOpacity(0);
				PaneConsomables.setOpacity(0);
				inventoryTypeSelector.relocate(652, 382+70*newValue.intValue());

				switch(newValue.intValue()) {
				case 0:
					PaneConsomables.setOpacity(1);
					break;

				case 1:
					PaneItems.setOpacity(1);
					break;

				case 2:
					PaneWeapons.setOpacity(1);
					break;
				}
			}});
	}

	private void menuSelection() {
		int x = Menu.selectedButtonX.intValue();
		int y = Menu.selectedButtonY.intValue();

		switch (Menu.currentMenu.get()) {

		case Menu.MainMenuID:
			selectorMain.relocate(playImg.getLayoutX(), playImg.getLayoutY()+120*y);
			break;

		case Menu.InGameMenuID:
			selectorPauseInGame.relocate(optionInGameImg.getLayoutX(), optionInGameImg.getLayoutY()+120*y);
			break;

		case Menu.OptionsMenuID:

			if(Menu.selectedButtonX.get() > 0 && Menu.selectedButtonY.get() < 4)
				selectorInOption.setFitWidth(255);
			else
				selectorInOption.setFitWidth(220);

			if(y == Menu.OptionsMenuHeight-1) {
				selectorInOption.relocate(640, 553);
			}else{
				selectorInOption.relocate(175+x*250, 170+70*y);
			}
			break;

		case Menu.InventoryMenuID:
			selectedName.setText("");
			if(x+y==0) {
				inventorySelector.setImage(inventorySelector3);
				inventorySelector.relocate(75, 75);
			}else if(x == 8) {
				inventorySelector.setImage(inventorySelector2);
				inventorySelector.relocate(652, 382+70*(y-2));
			}else if(y<2) {
				inventorySelector.setImage(inventorySelector1);
				inventorySelector.relocate(349+76*(x-4), 130+76*y);
			}else if(x<8) {
				inventorySelector.setImage(inventorySelector1);
				inventorySelector.relocate(60+73*x, 365+73*(y-2));

				selectedName.setText(World.player.nbWeapon>x+8*(y-2)?
						World.player.weapons[x+8*(y-2)].getItemName():"");

			}

			break;
		}
	}

	private void loadOptions() {

		try {
			BufferedReader optionsData = new BufferedReader(new FileReader(new File("saves/options.cfg").getAbsolutePath()));
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
		
		//Affichage des touches dans la console
		System.out.println("Avancer : "+Interaction.AVANCER);
		System.out.println("Reculer : "+Interaction.RECULER);
		System.out.println("Gauche : "+Interaction.GAUCHE);
		System.out.println("Droite : "+Interaction.DROITE);
		System.out.println("Valider dans les menus : Enter");
		System.out.println("Sortir menu/ouvrire menu en jeu : Echap");
		System.out.println("Interagire : "+Interaction.INTERACT);
		System.out.println("Ouvrire l'inventaire : "+Interaction.INVENTAIRE);
		System.out.println("Utiliser/equiper Item Gauche : "+Interaction.UseLeftItem);
		System.out.println("Utiliser/equiper Item Droite : "+Interaction.UseRightItem);
	}

	public void loadMapTexture() {				
		//Affichage de toutes les couches de la map
		printCalqueTile(PaneGround,PaneSolid,PaneTop);

		//Chargement des entites
		affichageEntitys();

		//Chargement de l'HUD
		hudLoading();

		//Ajout de l'affichage des ombres si la map est a l'interieur
		affichageOmbres();

		//Ajout d'un Listener si la map change
		World.currentMap.getNameProperty().addListener(new ChangeListener<String>(){

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				for(int i=0;i < entityListener.size();i++) {
					entityProperty.get(i).removeListener(entityListener.get(i));

				}
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

		//Affichage des items dans l'inventaire


		for(int i = 0; i < World.player.nbWeapon; i++) {
			PaneWeapons.getChildren().add(createItemView(World.player.weapons[i].getItemName(), 10 + 73 * (i%8) , 14 + 70 * Math.abs(i/8), 50, 50));
			InventoryMenu.newItem.set(false);
		}



	}


	private void hudLoading() {
		//Rubies(argent)
		rubys.setText(Player.rupees.get()+"");
		rubys.setFont(Font.font("Impact",20));
		rubys.setTextFill(Paint.valueOf("WHITE"));
		rubys.setStyle("-fx-font-weight: bold;");
		rubys.relocate(800, 605);
		ruby.relocate(765, 600);
		PaneHUD.getChildren().add(rubys);
		PaneHUD.getChildren().add(ruby);

		//Slot item equiper

		equipSlots.relocate(732, 0);
		leftItemEquip.relocate(752, 16);
		rightItemEquip.relocate(818, 84);
		PaneHUD.getChildren().add(equipSlots);
		PaneHUD.getChildren().add(leftItemEquip);
		PaneHUD.getChildren().add(rightItemEquip);

		//Coeurs
		for(int numCoeur=World.player.getMaxPv().intValue()/4;numCoeur>0;numCoeur--){
			coeurs.add(new ImageView(dicoImageItemTextureMap.get(2)));
		}

		updateHearts();

		for(ImageView coeur:coeurs){
			PaneHUD.getChildren().add(coeur);
		}

		World.player.getMaxPv().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				for(int numCoeur=(World.player.getMaxPv().intValue()-newValue.intValue())/4;numCoeur>0;numCoeur--){
					ImageView newHeart = new ImageView(dicoImageItemTextureMap.get(2));
					coeurs.add(newHeart);
					PaneHUD.getChildren().add(newHeart);
				}
				updateHearts();
			}
		});
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
		try {
			dicoImageTileTextureMap = new HashMap<>();
			dicoImageItemTextureMap = new HashMap<>();
			dicoImageTileEntityMap = new HashMap<>();
			dicoImageAnimationPlayer = new HashMap<>();
			dicoImageAnimationEntity = new HashMap<>();
			dicoImageProjectileMap = new HashMap<>();
			dicoShadow = new HashMap<>();

			LoadDicoMap(dicoImageTileTextureMap,32,32,16,16,"TileTextureMap");
			LoadDicoMap(dicoImageItemTextureMap,32,32,16,16,"ItemTextureMap");
			LoadDicoMap(dicoImageTileEntityMap,32,32,16,16,"TileEntityTextureMap");

			loadEntityAnimation();
			loadProjectileTexture();
			loadAnimationPlayer(dicoImageAnimationPlayer, 28, 17);

			coeurs = new ArrayList<>();

			inventorySelector1 = SwingFXUtils.toFXImage( ImageIO.read(new File("ressources/textures/gui/inventory/Selector1.png").toURI().toURL()), null);
			inventorySelector2 = SwingFXUtils.toFXImage( ImageIO.read(new File("ressources/textures/gui/inventory/Selector2.png").toURI().toURL()), null);
			inventorySelector3 = SwingFXUtils.toFXImage( ImageIO.read(new File("ressources/textures/gui/inventory/Selector3.png").toURI().toURL()), null);
			equipSlots.setImage(SwingFXUtils.toFXImage( ImageIO.read(new File("ressources/textures/gui/equipSlots.png").toURI().toURL()), null));
			inventoryTypeSelector.setImage(SwingFXUtils.toFXImage( ImageIO.read(new File("ressources/textures/gui/inventory/selectorInventoryType.png").toURI().toURL()), null));

			ruby.setImage(dicoImageItemTextureMap.get(7));

			for(int i = 0; i < 16;i++)
				dicoShadow.put(i, EntityLivingTexture.getEntityTexture("darkness",32,32,i,0));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadProjectileTexture() {
		try {
			dicoImageProjectileMap.put(0, SwingFXUtils.toFXImage( ImageIO.read(new File("ressources/textures/Arrow.png").toURI().toURL()), null));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void GraphiqueLoop() {

		//Clignotement des entity si degats
		if(croissant) {
			clignotement.set(clignotement.get()+1);
			if(clignotement.get()>=opacityVariationMax)
				croissant = false;
		}else {
			clignotement.set(clignotement.get()-1);
			if(clignotement.get()<=0)
				croissant = true;
		}

		//Changement de la valeur des rubies
		if(Player.rupees.get() < Integer.parseInt(rubys.getText()))
			rubys.setText(Integer.parseInt(rubys.getText())-1+"");
		else if(Player.rupees.get() > Integer.parseInt(rubys.getText()))
			rubys.setText(Integer.parseInt(rubys.getText())+1+"");
		if(Integer.parseInt(rubys.getText()) == Player.maxRupees) {
			rubys.setFont(new Font(Font.getFontNames().get(154),30));
			rubys.setTextFill(Paint.valueOf("#00B200"));
		}else {
			rubys.setFont(Font.font("Impact",20));
			rubys.setTextFill(Paint.valueOf("WHITE"));
		}

		//Animation pickupItem
		if(pickupItem.getImage() != null) {
			pickupItem.relocate(World.player.coordonnes.getX()*32-16, (World.player.coordonnes.getY()*32-56)-pickupItemAnim/3);
			pickupItemAnim++;
			if(pickupItemAnim > 30) {
				pickupItemAnim=0;
				pickupItem.setImage(null);
			}
		}

	}

	private void LoadDicoMap(Map<Integer,Image> dico,int imageWidthPixels, int imageHeightPixels, int imageWidth, int imageHeight, String textureMapName) {
		for(int y = 0; y < imageHeight; y++)
			for(int x = 0; x < imageWidth; x++)
				dico.put(x+y*imageWidth+1,TextureLoader.getTextureMapImage(textureMapName,imageWidthPixels,imageHeightPixels,x,y));
	}

	private Map<Integer,Image> LoadDicoMapAnimation(int imageWidthPixels, int imageHeightPixels, int imageWidth, int imageHeight, String textureMapName) {
		Map<Integer,Image> images = new HashMap<>();
		for(int y = 0; y < imageHeight; y++)
			for(int x = 0; x < imageWidth; x++)
				images.put(x+y*imageWidth,TextureLoader.getTextureMapImage(textureMapName,imageWidthPixels,imageHeightPixels,x,y));
		return images;
	}

	private void loadAnimationPlayer(Map<Integer,Image> dico, int frame, int animation) {
		for(int x = 0;x < frame;x++)
			for(int y = 0;y < animation;y++)
				dico.put(x + frame * y,EntityLivingTexture.getEntityTexture("Player", 24, 32, x, y));		
	}

	private void loadEntityAnimation() {
		dicoImageAnimationEntity.put("TikiTorchSmall", LoadDicoMapAnimation(32,112,5,1,"TikiTorchSmall"));
		dicoImageAnimationEntity.put("Zombie", LoadDicoMapAnimation(32,48,4,4,"Zombie"));
		dicoImageAnimationEntity.put("White Sheep", LoadDicoMapAnimation(48,48,4,4,"White Sheep"));
		dicoImageAnimationEntity.put("Chest", LoadDicoMapAnimation(32, 32, 3, 3, "coffres"));
		
		//Boss
		dicoImageAnimationEntity.put("FinManBody",LoadDicoMapAnimation(256, 180, 1, 1,"mobs/boss/FinManBody"));
		dicoImageAnimationEntity.put("FinManHead",LoadDicoMapAnimation(96, 110, 5, 1,"mobs/boss/FinManHead"));
		dicoImageAnimationEntity.put("FinManArm",LoadDicoMapAnimation(105, 152, 6, 2,"mobs/boss/FinManArm"));
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
		listEntityView.put(e,i);
		i.setId(""+e.primaryKey);
		i.setX(World.player.coordonnes.getX());
		i.setY(World.player.coordonnes.getY());

		if(e instanceof EntityLiving) {
			((EntityLiving)e).isDamaged.addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					ColorAdjust  color = new ColorAdjust();
					ImageView entityImg = getEntityImageView(e);
					if(newValue) {
						color.setSaturation(1);
						entityImg.opacityProperty().bind(clignotement.add(opacityVariationMax/4).divide(opacityVariationMax));

					}else {
						color.setSaturation(0);
						entityImg.opacityProperty().unbind();
						entityImg.setOpacity(1);
					}
					entityImg.setEffect(color);
				}

			});
		}

		if(e.getId().equals("Player")) {
			Player theplayer = (Player)e;
			i.setFitWidth(32);
			i.setFitHeight(64);
			i.xProperty().bind(theplayer.coordonnes.getXpro().multiply(32).subtract(16));
			i.yProperty().bind(theplayer.coordonnes.getYpro().multiply(32).subtract(48));
			i.setImage(EntityLivingTexture.getEntityTexture(theplayer.getId(), 24, 32, 0, 2));
			paneGame.layoutXProperty().bind(theplayer.coordonnes.getXpro().multiply(-32).add(432));
			paneGame.layoutYProperty().bind(theplayer.coordonnes.getYpro().multiply(-32).add(320));
			MenuControler.player = i;
			PlayerPane.getChildren().add(i);
			carriableEntity.setId("CarriableEntity");
			PlayerPane.getChildren().add(carriableEntity);
			PlayerPane.getChildren().add(pickupItem);
		}else {
			if(e instanceof Boss){
				Boss theBoss = (Boss)e;
				
				
				if(theBoss.getId().equals("FinMan")) {
					//i.setImage(dicoImageAnimationEntity.get("FinManArm").get(0));
					i.setImage(dicoImageAnimationEntity.get(theBoss.getId()+"Body").get(0));
					i.xProperty().bind(e.coordonnes.getXpro().multiply(32).subtract(i.getImage().getWidth()/2));
					i.yProperty().bind(e.coordonnes.getYpro().multiply(32).subtract(i.getImage().getHeight()/2));
					
					}
				
				EntityPane.getChildren().add(i);

				for(int x=0; x<theBoss.bodyPart.length;x++) {
					MemberPart part = theBoss.bodyPart[x];
					ImageView bodyPart = new ImageView();
					bodyPart.setId(theBoss.primaryKey+"");
					String id = part.getId().equals(theBoss.getId()+"LeftArm")
							||part.getId().equals(theBoss.getId()+"RightArm")?theBoss.getId()+"Arm":part.getId();
					bodyPart.setImage(dicoImageAnimationEntity.get(id).get(part.getId().equals(theBoss.getId()+"RightArm")?6:0));
					//bodyPart.setImage(dicoImageAnimationEntity.get("FinManArm").get(0));
					bodyPart.xProperty().bind(part.coordonnes.getXpro().multiply(32).subtract(i.getImage().getWidth()/2));
					bodyPart.yProperty().bind(part.coordonnes.getYpro().multiply(32).subtract(i.getImage().getHeight()/2));
					EntityPane.getChildren().add(bodyPart);
					listEntityView.put(theBoss,bodyPart);
				}
				
			}else {
			if(e instanceof Chest) {
				Chest chest = (Chest)e;
				i.setFitWidth(32);
				i.setFitHeight(32);
				i.xProperty().bind(e.coordonnes.getXpro().multiply(32));
				i.yProperty().bind(e.coordonnes.getYpro().multiply(32));
				if(chest.getEtat()) {
					i.setImage(dicoImageTileEntityMap.get( EntityImageValue.getEntityNum(e.getId()) ) );
				}else {
					switch(chest.getId()) {
					case "Gold Chest":
						i.setImage(dicoImageAnimationEntity.get("Chest").get(2));
						break;
					case "Iron Chest":
						i.setImage(dicoImageAnimationEntity.get("Chest").get(8));
						break;
					case "Wood Chest":
						i.setImage(dicoImageAnimationEntity.get("Chest").get(5));
						break;
					}
				}
			}else if(e.getId().equals("ItemOnGround")) {
				i.setFitWidth(32);
				i.setFitHeight(32);
				i.xProperty().bind(e.coordonnes.getXpro().multiply(32));
				i.yProperty().bind(e.coordonnes.getYpro().multiply(32));
				i.setImage(dicoImageItemTextureMap.get(ItemImageValue.getValue(((EntityItemOnGround)e).item.name)));
				
			}else if(e instanceof Projectile) {
				if(e.getId().equals("Arrow")) {
					i.setFitWidth(32);
					i.setFitHeight(32);
					i.xProperty().bind(e.coordonnes.getXpro().multiply(32));
					i.yProperty().bind(e.coordonnes.getYpro().multiply(32));
					i.setImage(dicoImageProjectileMap.get(0));
					switch (e.direction.getDirection()) {
					case Direction.South:
						i.setRotate(180);
						break;
					case Direction.East:
						i.setRotate(90);
						break;
					case Direction.West:
						i.setRotate(270);
						break;

					}
				}else
					System.out.println("MISSING TEXTURE !!! "+e.getId());
				
			}else if(e instanceof EntityLiving) {
				i.setFitWidth(32);
				i.setFitHeight(64);
				i.xProperty().bind(e.coordonnes.getXpro().multiply(32).subtract(16));
				i.yProperty().bind(e.coordonnes.getYpro().multiply(32).subtract(48));
				i.setImage(dicoImageAnimationEntity.get(e.getId()).get(0));

			}else if(e instanceof EntityLight) {
				i.setFitWidth(32);
				i.setFitHeight(64);
				i.xProperty().bind(e.coordonnes.getXpro().multiply(32));
				i.yProperty().bind(e.coordonnes.getYpro().multiply(32).subtract(48));
				i.setImage(dicoImageAnimationEntity.get(e.getId()).get(( (TileEntity) e).getEtat()?1:0));

			}else if(e instanceof TileEntity) {
				i.setFitWidth(32);
				i.setFitHeight(32);
				i.xProperty().bind(e.coordonnes.getXpro().multiply(32));
				i.yProperty().bind(e.coordonnes.getYpro().multiply(32));
				i.setImage(dicoImageTileEntityMap.get(EntityImageValue.getEntityNum(e.getId())) );
			}
			EntityPane.getChildren().add(i);
			}
		}

	}

	public static void playerAnimation() { //deplacement standard du joueur
		switch(World.player.direction.getDirection()) {

		case Direction.North:
			player.setImage(dicoImageAnimationPlayer.get(( World.player.etatDeplacement.getValue().intValue() / 3)));
			break;
		case Direction.East:
			player.setImage(dicoImageAnimationPlayer.get(( World.player.etatDeplacement.getValue().intValue() / 3)+28));
			break;
		case Direction.South:
			player.setImage(dicoImageAnimationPlayer.get(( World.player.etatDeplacement.getValue().intValue() / 3)+56));
			break;
		case Direction.West:
			player.setImage(dicoImageAnimationPlayer.get(( World.player.etatDeplacement.getValue().intValue() / 3)+84));
			break;
		}
	}

	public static void playerRaiseWalkAnimation() {
		switch(World.player.direction.getDirection()) {
		case Direction.North:
			player.setImage(dicoImageAnimationPlayer.get((World.player.etatDeplacement.getValue().intValue() / 8)+11+28*9));
			break;

		case Direction.East:
			player.setImage(dicoImageAnimationPlayer.get((World.player.etatDeplacement.getValue().intValue() / 8)+11+28*10));
			break;

		case Direction.South:
			player.setImage(dicoImageAnimationPlayer.get((World.player.etatDeplacement.getValue().intValue() / 8)+11+28*11));
			break;

		case Direction.West:
			player.setImage(dicoImageAnimationPlayer.get((World.player.etatDeplacement.getValue().intValue() / 8)+11+28*12));
			break;
		}
	}
	
	public static void playerPlaceAnimation() {
		switch(World.player.direction.getDirection()) {
		case Direction.North:
			player.setImage(dicoImageAnimationPlayer.get((World.player.etatDeplacement.getValue().intValue() / 10)+22+28*9));
			break;

		case Direction.East:
			player.setImage(dicoImageAnimationPlayer.get((World.player.etatDeplacement.getValue().intValue() / 10)+22+28*10));
			break;

		case Direction.South:
			player.setImage(dicoImageAnimationPlayer.get((World.player.etatDeplacement.getValue().intValue() / 10)+22+28*11));
			break;

		case Direction.West:
			player.setImage(dicoImageAnimationPlayer.get((World.player.etatDeplacement.getValue().intValue() / 10)+22+28*12));
			break;
		}
	}
	
	private ImageView getEntityImageView(Entity e) {
		ImageView img = null;

		if(e.getId().equals("Player")) {
			img = player;
		}else {
			for(ImageView imageView:listEntityView.values())
				if(imageView.getId().equals(""+e.primaryKey))
					return imageView;
		}
		return img;
	}

	private void affichageEntitys() {

		//Cree les imageviews des entites
		for(Entity entity:World.currentMap.getEntity()) {
			if(entity != null) {
				loadAnimationEntity(new ImageView(), entity);

				if(entity instanceof TileEntity) {
					TileEntity tileEntity = (TileEntity) entity;
					tileEntity.getEtatProperty().addListener(new ChangeListener<Boolean>() {
						@Override
						public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
							ImageView entityImg = listEntityView.get(tileEntity);
							if(tileEntity.getEtat() && entityImg != null) {
								if(tileEntity instanceof CarriableEntity)
									entityImg.setImage(dicoImageTileEntityMap.get(EntityImageValue.getEntityNum(tileEntity.getId())));
								else if(!(tileEntity instanceof Chest)) {
									entityImg.setImage(EntityLivingTexture.getEntityTexture(tileEntity.getId(), 32, 112, 1, 0));
								}
							}

						}

					});
				}
			}
		}
		//Supprime et ajoute les entiter qui on etais modiffier dans le monde
		World.currentMap.entity.addListener(new ListChangeListener<Entity>(){
			@Override
			public void onChanged(Change<? extends Entity> c) {
				ArrayList<ImageView> toRemove = new ArrayList<>();

				c.next();
				for(Entity entity:c.getRemoved()) {
					if(entity != World.player.carriedEntity)//Pour pas avoir de probleme
						listEntityView.remove(entity);
					for(Node img:EntityPane.getChildren())
						if(img instanceof ImageView)
							if(((ImageView) img).getId().equals(""+entity.primaryKey))
								toRemove.add((ImageView)img);			
				}
				for(ImageView img:toRemove) {
					EntityPane.getChildren().remove(img);
				}

				toRemove.clear();

				for(Entity entity:c.getAddedSubList()) {
					ImageView img = new ImageView();
					listEntityView.put(entity,img);
					loadAnimationEntity(img, entity);
				}

			}
		});
	}

	public void loadAnimationEntity(ImageView img, Entity addEntity) {

		if(addEntity instanceof Player) {
			Player theplayer = (Player)addEntity;
			theplayer.isCarriedSomething.addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> observable,
						Boolean oldValue, Boolean newValue) {
					if(newValue.booleanValue()) {
						carriableEntity.setImage( listEntityView.get(World.player.carriedEntity).getImage());
						carriableEntity.setOpacity(1);
						carriableEntity.xProperty().bind(World.player.coordonnes.getXpro().multiply(32).subtract(16));
						carriableEntity.yProperty().bind(World.player.coordonnes.getYpro().multiply(32).subtract(48));

					}else if(!newValue.booleanValue()) {

						carriableEntity.setOpacity(0);
						carriableEntity.xProperty().unbind();
						carriableEntity.yProperty().unbind();
					}
				}
			});

			theplayer.action.addListener(new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue,
						Number newValue) {
					theplayer.etatDeplacement.unbind();
					System.out.println(newValue.intValue());
					if(Actions.walk.get() == newValue.intValue())
						theplayer.etatDeplacement.addListener(new ChangeListener<Number>() {
							@Override
							public void changed(ObservableValue<? extends Number> observable, Number oldValue,Number newValue)
							{
								playerAnimation();
							}
						});
					else if(Actions.raise.get() == newValue.intValue())
						theplayer.etatDeplacement.addListener(new ChangeListener<Number>() {
							@Override
							public void changed(ObservableValue<? extends Number> observable, Number oldValue,Number newValue)
							{
								playPlayerAnimation();
							}
						});
					else if(Actions.walkAndRaise.get() == newValue.intValue())
					{
						theplayer.etatDeplacement.addListener(new ChangeListener<Number>() {
							@Override
							public void changed(ObservableValue<? extends Number> observable, Number oldValue,Number newValue)
							{
								playerRaiseWalkAnimation();
							}});
					}else if(Actions.place.get() == newValue.intValue())
					{
						theplayer.etatDeplacement.addListener(new ChangeListener<Number>() {
							@Override
							public void changed(ObservableValue<? extends Number> observable, Number oldValue,Number newValue)
							{
								playerPlaceAnimation();
							}});
					}
					else if(Actions.push.get() == newValue.intValue()) 
					{
						switch(theplayer.direction.getDirection()) {
						
						case Direction.North:
							theplayer.etatDeplacement.addListener(new ChangeListener<Number>() {
								@Override
								public void changed(ObservableValue<? extends Number> observable, Number oldValue,Number newValue)
								{
									player.setImage(dicoImageAnimationPlayer.get((World.player.etatDeplacement.getValue().intValue() / 14)+28*13+3));
								}});
							break;
						
						case Direction.South:
							theplayer.etatDeplacement.addListener(new ChangeListener<Number>() {
								@Override
								public void changed(ObservableValue<? extends Number> observable, Number oldValue,Number newValue)
								{
									player.setImage(dicoImageAnimationPlayer.get((World.player.etatDeplacement.getValue().intValue() / 14)+28*15+3));
								}});
							break;
							
						case Direction.East:
							theplayer.etatDeplacement.addListener(new ChangeListener<Number>() {
								@Override
								public void changed(ObservableValue<? extends Number> observable, Number oldValue,Number newValue)
								{
									player.setImage(dicoImageAnimationPlayer.get((World.player.etatDeplacement.getValue().intValue() / 14)+28*14+3));
								}});
							break;
							
						case Direction.West:
							theplayer.etatDeplacement.addListener(new ChangeListener<Number>() {
								@Override
								public void changed(ObservableValue<? extends Number> observable, Number oldValue,Number newValue)
								{
									player.setImage(dicoImageAnimationPlayer.get((World.player.etatDeplacement.getValue().intValue() / 14)+28*16+3));
								}});
							break;
						}
						
						
						
					}else if(Actions.die.get() == newValue.intValue()){						
						
						theplayer.etatDeplacement.addListener(new ChangeListener<Number>() {
							@Override
							public void changed(ObservableValue<? extends Number> observable, Number oldValue,Number newValue)
							{
								player.setImage(dicoImageAnimationPlayer.get((int)(World.player.etatDeplacement.getValue().intValue() / 16.6)+28*4+2));
								System.out.println((int)(World.player.etatDeplacement.getValue().intValue() / 17)+28*4+2);
							}});
					
					}else if(Actions.useWeapon.get() == newValue.intValue()) 
					
					{
						//
					}
				}
			});
			theplayer.action.set(0);

			theplayer.havePickupItem.addListener(new ChangeListener<Boolean>() {

				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					pickupItem.setImage(dicoImageItemTextureMap.get(ItemImageValue.getValue(World.player.pickupItem.getItemName())));
				}
			
			});

			theplayer.haveLeftItemEquip.addListener(new ChangeListener<Boolean>() {

				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					if(newValue) {
						Weapon item = (Weapon)World.player.LeftItemEquip;
						leftItemEquip.setImage(dicoImageItemTextureMap.get(ItemImageValue.getValue(item.name)));
					}else {
						leftItemEquip.setImage(null);
					}
				}				
			});

			theplayer.haveRightItemEquip.addListener(new ChangeListener<Boolean>() {

				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					if(newValue) {
						Item item = (Weapon)World.player.RightItemEquip;
						rightItemEquip.setImage(dicoImageItemTextureMap.get(ItemImageValue.getValue(item.name)));
					}else {
						rightItemEquip.setImage(null);
					}
				}	
			});
		}

		affichageEntity(listEntityView.get(addEntity),addEntity);

		if(!addEntity.getId().equals("Player")){

			//AJOUT DU LISTENER POUR L'ANIMATION DE L'ENTITE
			addEntity.etatDeplacement.addListener( new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue,Number newValue) {

					switch(addEntity.direction.getDirection()) {
					case Direction.North:
						listEntityView.get(addEntity).setImage(
								dicoImageAnimationEntity.get(addEntity.getId()).get((int)(observable.getValue().intValue() / 21 + 12)));
						break;
					case Direction.East:
						listEntityView.get(addEntity).setImage(
								dicoImageAnimationEntity.get(addEntity.getId()).get((int)(observable.getValue().intValue() / 21 + 4)));
						break;
					case Direction.South:
						listEntityView.get(addEntity).setImage(
								dicoImageAnimationEntity.get(addEntity.getId()).get((int)(observable.getValue().intValue() / 21 + 0)));
						break;
					case Direction.West:
						listEntityView.get(addEntity).setImage(
								dicoImageAnimationEntity.get(addEntity.getId()).get((int)(observable.getValue().intValue() / 21 + 8)));
						break;
					}

				}});
			if(addEntity instanceof Chest) {
				Chest c = (Chest)addEntity;
				ImageView ivItem;
				if(c.itemInside != null) {
					String containItemName = c.itemInside.getItemName();
					ivItem = createItemView(containItemName, (int)c.coordonnes.getX()*32, (int)c.coordonnes.getY()*32, 32, 32);
				}else {
					ivItem = new ImageView();
				}
				DoubleProperty coordY = new SimpleDoubleProperty( ((int)c.coordonnes.getY()*32));
				ImageView EntityImg = getEntityImageView(c);
				int chestType;
				if(c.getId().equals("Wood Chest")) {
					chestType = 1;
				}else if(c.getId().equals("Iron Chest")) {
					chestType = 2;
				}else {//Gold Chest
					chestType = 0;
				}

				ChangeListener<Number> etatListener = new ChangeListener<Number>() {
					@Override
					public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
						ImageView item = ivItem;

						if(!oldValue.equals(newValue)) {

							EntityImg.setImage(dicoImageAnimationEntity.get("Chest").get((observable.getValue().intValue() / 15)+3*chestType));
							if(observable.getValue().intValue() == 1) {						
								ArmePane.getChildren().add(item);
							}else {
								coordY.set(coordY.get()-observable.getValue().intValue()/10);
								item.setLayoutY(coordY.get());
							}
							if(observable.getValue().intValue() >= 30) {
								ArmePane.getChildren().remove(item);
								coordY.set((int)c.coordonnes.getY()*32);
							}
						}
					}
				};
				entityProperty.add(c.etatAnim);
				entityListener.add(etatListener);
				c.etatAnim.addListener(etatListener);
			}

		}

	}

	public static void playPlayerAnimation() {//Les animations special
		int animDivideur = 3;
		Player thePlayer = World.player;
		switch(thePlayer.direction.getDirection()) {
		case Direction.North:
			player.setImage(dicoImageAnimationPlayer
					.get((thePlayer.etatDeplacement.get()/animDivideur)+28*9));
			break;

		case Direction.East:
			player.setImage(dicoImageAnimationPlayer
					.get((thePlayer.etatDeplacement.get()/animDivideur)+28*10));
			break;

		case Direction.South:
			player.setImage(dicoImageAnimationPlayer
					.get((thePlayer.etatDeplacement.get()/animDivideur)+28*11));
			break;

		case Direction.West:
			player.setImage(dicoImageAnimationPlayer
					.get((thePlayer.etatDeplacement.get()/animDivideur)+28*12));
			break;
		}
	}
	
	public ImageView createItemView(String name, int layoutX, int layoutY, int width, int height) {
		ImageView v = new ImageView();
		v.setId(name);
		v.setImage(dicoImageItemTextureMap.get(ItemImageValue.getValue(name)));
		v.relocate(layoutX, layoutY);
		v.setFitHeight(height);
		v.setFitWidth(width);
		return v;
	}	

	private void initialiseAnimItemEnMain(EntityLiving e) {
		ImageView itemEnMainView = createItemView(e.itemsEnMain.get(0).name, 11, 14, 30 ,30);
		itemEnMainView.setId(e.primaryKey+"");
		ArmePane.getChildren().add(itemEnMainView);
		itemEnMainView.xProperty().bind((e.coordonnes.getXpro().multiply(32).subtract(17)));
		itemEnMainView.yProperty().bind((e.coordonnes.getYpro().multiply(32).subtract(38)));
	}

	private void changeImageViewItemDirection(Direction d, EntityLiving el) {

		for(int j = 0; j < ArmePane.getChildren().size() ; j++){
			if(ArmePane.getChildren().get(j).getId().equals(""+el.primaryKey)) {
				ImageView i = (ImageView)ArmePane.getChildren().get(j);
				switch(d.getDirection()) {
				case Direction.North :
					i.setRotate(-45);
					break;

				case Direction.South :
					i.setRotate(135);
					break;

				case (Direction.West) :

					if(el.getId() == "Player") {
						i.setLayoutX(-10);

					}else {
						//i.setLayoutX(playImg.layoutYProperty().getValue()-1);
					}
				i.setRotate(-90);
				break;

				case (Direction.East) :
					i.setRotate(0);
				if(el.getId() == "Player") {
					i.setLayoutX(20);
				}
				else
					//i.setLayoutX(playImg.layoutYProperty().getValue()+60);
					break;

				}
				return;
			}
		}
	}
}
