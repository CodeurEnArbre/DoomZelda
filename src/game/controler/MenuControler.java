package game.controler;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.function.Predicate;

import game.modele.utils.Orientation.Direction;
import game.modele.world.WorldLoader;
import game.vue.EntityLivingTexture;
import game.vue.TileTexture;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class MenuControler implements Initializable{

	@FXML
	private Pane EntityPane;

	@FXML
	private TilePane TilePaneGround;

	@FXML
	private TilePane TilePaneSolid;

	@FXML
	private TilePane TilePaneTop;

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

	private void printCalqueTile(TilePane paneTerrain,TilePane paneTile,TilePane paneTop) {
		
		for(int y=0;y<WorldLoader.currentMap.getHeight();y++) {
			for(int x=0;x<WorldLoader.currentMap.getWidth();x++) {

					
					int i = WorldLoader.currentMap.getTileTerrain(y, x).getId();
					i = i == 0 ? 0 : i -1;
					     						
					paneTerrain.getChildren().add(new ImageView(SwingFXUtils.toFXImage(TileTexture.getTileTexture(i).getTexture(), null)));
					
					
					i = WorldLoader.currentMap.getTile(y, x).getId();
					i = i == 0 ? 0 : i -1;
					
					paneTile.getChildren().add(new ImageView(SwingFXUtils.toFXImage(TileTexture.getTileTexture(i).getTexture(), null)));
					
					
					i = WorldLoader.currentMap.getTileTop(y, x).getId();
					i = i == 0 ? 0 : i -1;
					
					paneTop.getChildren().add(new ImageView(SwingFXUtils.toFXImage(TileTexture.getTileTexture(i).getTexture(), null)));
					
			}
		}
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		WorldLoader.loadWorld("TinyMap");

		TilePaneGround.setMinSize(WorldLoader.currentMap.getWidth()*32, WorldLoader.currentMap.getHeight()*32);
		TilePaneGround.setMaxSize(WorldLoader.currentMap.getWidth()*32, WorldLoader.currentMap.getHeight()*32);
		TilePaneSolid.setMinSize(WorldLoader.currentMap.getWidth()*32, WorldLoader.currentMap.getHeight()*32);
		TilePaneSolid.setMaxSize(WorldLoader.currentMap.getWidth()*32, WorldLoader.currentMap.getHeight()*32);
		TilePaneTop.setMinSize(WorldLoader.currentMap.getWidth()*32, WorldLoader.currentMap.getHeight()*32);
		TilePaneTop.setMaxSize(WorldLoader.currentMap.getWidth()*32, WorldLoader.currentMap.getHeight()*32);
		
		printCalqueTile(TilePaneGround,TilePaneSolid,TilePaneTop);
		
	
		player=new ImageView();
		
		WorldLoader.loadPlayer();
		
		player.setImage(SwingFXUtils.toFXImage(EntityLivingTexture.getEntityTexture("player", 24, 64, 0, 2).getTexture(), null));
		player.setFitWidth(32);
		player.setFitHeight(64);
		player.setX(WorldLoader.player.getCoordoner().getX());
		player.setX(WorldLoader.player.getCoordoner().getY());
		EntityPane.getChildren().add(player);
		
		Thread javafx = Thread.currentThread();

		player.xProperty().bind(WorldLoader.player.getCoordoner().getXpro().multiply(32).subtract(16));
		player.yProperty().bind(WorldLoader.player.getCoordoner().getYpro().multiply(32).subtract(48));
		
		
		new Thread("Graphique Updateur"){
			@Override
			public void run(){
				int direction=2;
				while (true){
					//TODO A changer (binder)
					if(WorldLoader.player.getDirection() == Direction.NORTH && direction != 0) {
						player.setImage(SwingFXUtils.toFXImage(EntityLivingTexture.getEntityTexture("player", 24, 64, 0, 0).getTexture(), null));
						direction=0;
					}
					if(WorldLoader.player.getDirection() == Direction.WEST && direction != 1) {
						player.setImage(SwingFXUtils.toFXImage(EntityLivingTexture.getEntityTexture("player", 24, 64, 0, 1).getTexture(), null));
						direction=1;
					}
					if(WorldLoader.player.getDirection() == Direction.SOUTH && direction != 2) {
						player.setImage(SwingFXUtils.toFXImage(EntityLivingTexture.getEntityTexture("player", 24, 64, 0, 2).getTexture(), null));
						direction=2;
					}
					if(WorldLoader.player.getDirection() == Direction.EAST && direction != 3) {
						player.setImage(SwingFXUtils.toFXImage(EntityLivingTexture.getEntityTexture("player", 24, 64, 0, 3).getTexture(), null));
						direction=3;
					}
					
		

					if(!javafx.isAlive()){
						System.exit(0);
					}}}}.start();

	}

}
