package game.controler;

import java.net.URL;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class MenuControler implements Initializable{

	@FXML
	private Pane EntityPane;

	@FXML
	private Pane PaneGround;

	@FXML
	private Pane PaneSolid;

	@FXML
	private Pane PaneTop;

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

					
					int i = WorldLoader.currentMap.getTileTerrain(y, x).getId();
					i = i == 0 ? 0 : i -1;
					  
					ImageView tile = new ImageView(SwingFXUtils.toFXImage(TileTexture.getTileTexture(i).getTexture(), null));
					paneTerrain.getChildren().add(tile);
					tile.setX(x*32);
					tile.setY(y*32);
					
					i = WorldLoader.currentMap.getTile(y, x).getId();
					i = i == 0 ? 0 : i -1;
					
					tile = new ImageView(SwingFXUtils.toFXImage(TileTexture.getTileTexture(i).getTexture(), null));
					paneTile.getChildren().add(tile);
					tile.setX(x*32);
					tile.setY(y*32);
					
					i = WorldLoader.currentMap.getTileTop(y, x).getId();
					i = i == 0 ? 0 : i -1;
					
					tile = new ImageView(SwingFXUtils.toFXImage(TileTexture.getTileTexture(i).getTexture(), null));
					paneTop.getChildren().add(tile);
					tile.setX(x*32);
					tile.setY(y*32);
			}
		}
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		
		
		WorldLoader.loadWorld("TinyMap");

		PaneGround.setMinSize(WorldLoader.currentMap.getWidth()*32, WorldLoader.currentMap.getHeight()*32);
		PaneGround.setMaxSize(WorldLoader.currentMap.getWidth()*32, WorldLoader.currentMap.getHeight()*32);
		PaneSolid.setMinSize(WorldLoader.currentMap.getWidth()*32, WorldLoader.currentMap.getHeight()*32);
		PaneSolid.setMaxSize(WorldLoader.currentMap.getWidth()*32, WorldLoader.currentMap.getHeight()*32);
		PaneTop.setMinSize(WorldLoader.currentMap.getWidth()*32, WorldLoader.currentMap.getHeight()*32);
		PaneTop.setMaxSize(WorldLoader.currentMap.getWidth()*32, WorldLoader.currentMap.getHeight()*32);
		
		printCalqueTile(PaneGround,PaneSolid,PaneTop);
		
	
		player=new ImageView();
		
		WorldLoader.loadPlayer();
		
		player.setImage(SwingFXUtils.toFXImage(EntityLivingTexture.getEntityTexture("player", 24, 64, 0, 2).getTexture(), null));
		player.setFitWidth(32);
		player.setFitHeight(64);
		player.setX(WorldLoader.player.getCoordoner().getX());
		player.setX(WorldLoader.player.getCoordoner().getY());
		EntityPane.getChildren().add(player);

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
