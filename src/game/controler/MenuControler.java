package game.controler;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import game.modele.utils.Direction;
import game.modele.world.WorldLoader;
import game.vue.EntityLivingTexture;
import game.vue.TileTexture;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class MenuControler implements Initializable{

	
	Map<Integer,Image> dicoImage;
	
	
	@FXML
    private Pane paneWindow;
	
	@FXML
	private Pane EntityPane;

	@FXML
	private Pane PaneGround;

	@FXML
	private Pane PaneSolid;

	@FXML
	private Pane PaneTop;
	
	@FXML 
	private Pane PaneHUD;

	private ImageView player;
		

	@FXML
	void option(ActionEvent event) {

	}

	@FXML
	void play(ActionEvent event) {


	}

	@FXML
	void quitter(ActionEvent event) {
		
	}
	
	
	private void printCalqueTile(Pane paneTerrain,Pane paneTile,Pane paneTop) {
		
		for(int y=0;y<WorldLoader.currentMap.getHeight();y++) {
			for(int x=0;x<WorldLoader.currentMap.getWidth();x++) {

					ImageView tile = new ImageView(dicoImage.get(WorldLoader.currentMap.getTileTerrain(y, x).getId()));	
					tile.setX(x*32);
					tile.setY(y*32);
					paneTerrain.getChildren().add(tile);
					
					tile = new ImageView(dicoImage.get(WorldLoader.currentMap.getTile(y, x).getId()));
					tile.setX(x*32);
					tile.setY(y*32);
					paneTile.getChildren().add(tile);
					
					tile = new ImageView(dicoImage.get(WorldLoader.currentMap.getTileTop(y, x).getId()));
					tile.setX(x*32);
					tile.setY(y*32);
					paneTop.getChildren().add(tile);
					

			}
		}
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		dicoImage = new HashMap<>();
		LoadDicoMap(dicoImage);
		
		
		WorldLoader.loadWorld("Road");

		printCalqueTile(PaneGround,PaneSolid,PaneTop);
		
	
		player=new ImageView();
		
		WorldLoader.loadPlayer();
		
		player.setImage(SwingFXUtils.toFXImage(EntityLivingTexture.getEntityTexture("player", 24, 64, 0, 2).getTexture(), null));
		player.setFitWidth(32);
		player.setFitHeight(64);
		player.setX(WorldLoader.player.getCoordoner().getX());
		player.setY(WorldLoader.player.getCoordoner().getY());
		EntityPane.getChildren().add(player);
		
		paneWindow.layoutXProperty().bind(WorldLoader.player.getCoordoner().getXpro().multiply(-32).add(432));
		paneWindow.layoutYProperty().bind(WorldLoader.player.getCoordoner().getYpro().multiply(-32).add(320));
	
		player.xProperty().bind(WorldLoader.player.getCoordoner().getXpro().multiply(32).subtract(16));
		player.yProperty().bind(WorldLoader.player.getCoordoner().getYpro().multiply(32).subtract(48));
		
		
		
		ImageView imageCoeur1 = new ImageView();
		
		imageCoeur1.setImage(SwingFXUtils.toFXImage(EntityLivingTexture.getEntityTexture("CoeurPlein", 64, 64, 0, 0).getTexture(), null));
		imageCoeur1.setFitWidth(30);
		imageCoeur1.setFitHeight(30);
		imageCoeur1.setX(5);
		imageCoeur1.setY(5);
		PaneHUD.getChildren().add(imageCoeur1);
		
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
		
		WorldLoader.player.getPV().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	private void LoadDicoMap(Map<Integer,Image> dico) {
		for(int x = 0; x < 256; x++) {
				dico.put(x + 1,SwingFXUtils.toFXImage(TileTexture.getTileTexture(x).getTexture(), null));
		}
	}
	
	/*private void perdrePV() {
		WorldLoader.player.perdrePV();
	}
	
	private void gagnerPV() {
		WorldLoader.player.gagnerPV();
	}*/
}
