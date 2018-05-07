package game.controler;

import java.net.URL;
import java.util.ResourceBundle;

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

	private void printCalqueTile(TilePane pane) {

		for(int y=0;y<WorldLoader.currentMap.getHeight();y++) {
			for(int x=0;x<WorldLoader.currentMap.getWidth();x++) {

				ImageView texture = new ImageView();

				if(WorldLoader.currentMap.getTile(y, x)!=null) {
					int i = WorldLoader.currentMap.getTile(y, x).getId();
					i = i == 0 ? 0 : i -1;
					
					texture.setImage(SwingFXUtils.toFXImage(TileTexture.getTileTexture(i).getTexture(), null));      						
					pane.getChildren().add(texture);
				}
			}
		}
	}

	private void printCalqueTerrain(TilePane pane) {

		for(int y=0;y<WorldLoader.currentMap.getHeight();y++) {
			for(int x=0;x<WorldLoader.currentMap.getWidth();x++) {

				ImageView texture = new ImageView();

				if(WorldLoader.currentMap.getTileTerrain(y, x)!=null) {
					int i = WorldLoader.currentMap.getTileTerrain(y, x).getId();
					i = i == 0 ? 0 : i -1;
					texture.setImage(SwingFXUtils.toFXImage(TileTexture.getTileTexture(i).getTexture(), null));      						
					pane.getChildren().add(texture);
				}
			}
		}
	}
	
	private void printCalqueTop(TilePane pane) {

		for(int y=0;y<WorldLoader.currentMap.getHeight();y++) {
			for(int x=0;x<WorldLoader.currentMap.getWidth();x++) {

				ImageView texture = new ImageView();

				if(WorldLoader.currentMap.getTileTop(y, x)!=null) {
					int i = WorldLoader.currentMap.getTileTop(y,x).getId();
					i = i == 0 ? 0 : i -1;
					texture.setImage(SwingFXUtils.toFXImage(TileTexture.getTileTexture(i).getTexture(), null));      						
					pane.getChildren().add(texture);
				}
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

		printCalqueTerrain(TilePaneGround);
		printCalqueTile(TilePaneSolid);
		WorldLoader.loadPlayer();
		player=new ImageView();



		player.setImage(SwingFXUtils.toFXImage(EntityLivingTexture.getEntityTexture("player", 24, 64, 0, 2).getTexture(), null));
		player.setFitWidth(32);
		player.setFitHeight(64);
		player.setX(WorldLoader.player.getCoordoner().getX());
		player.setX(WorldLoader.player.getCoordoner().getY());
		EntityPane.getChildren().add(player);
		printCalqueTop(TilePaneTop);

		Thread javafx = Thread.currentThread();

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
					
					player.setY(WorldLoader.player.getCoordoner().getX()*32-32);
					player.setX(WorldLoader.player.getCoordoner().getY()*32-16);


					if(!javafx.isAlive()){
						System.exit(0);
					}}}}.start();

	}

}
