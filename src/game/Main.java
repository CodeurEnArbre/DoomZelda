package game;

import java.io.File;
import java.net.URL;

import game.modele.utils.Coordonnees;
import game.modele.utils.Orientation.Direction;
import game.modele.world.WorldLoader;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler<KeyEvent>{


	@Override
	public void start(Stage primaryStage)  {	
		try {
		System.out.println("Loading game");
		FXMLLoader loader = new FXMLLoader();
		URL url = new File("src/game/vue/game.fxml").toURI().toURL();
		loader.setLocation(url);
     	Scene scene = new Scene(FXMLLoader.load(url),480,320);
     	scene.setOnKeyPressed(this);//KEY
		primaryStage.setScene(scene);
		primaryStage.setTitle("Doom Zelda");
		primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void handle(KeyEvent event) {
		if(WorldLoader.player!=null) {
			
			if(event.getCode()== KeyCode.Z) {
				WorldLoader.player.setCoordoner(new Coordonnees(WorldLoader.player.getCoordoner().getX(),WorldLoader.player.getCoordoner().getY() - 0.4));
				WorldLoader.player.setDirection(Direction.NORTH);
			}else if(event.getCode()== KeyCode.S){
				WorldLoader.player.setCoordoner(new Coordonnees(WorldLoader.player.getCoordoner().getX(),WorldLoader.player.getCoordoner().getY() + 0.4));
				WorldLoader.player.setDirection(Direction.SOUTH);
			}
			
			if(event.getCode()== KeyCode.Q) {
				WorldLoader.player.setCoordoner(new Coordonnees(WorldLoader.player.getCoordoner().getX() - 0.4,WorldLoader.player.getCoordoner().getY()));
				WorldLoader.player.setDirection(Direction.EAST);
			}else if(event.getCode()== KeyCode.D) {
				WorldLoader.player.setCoordoner(new Coordonnees(WorldLoader.player.getCoordoner().getX() + 0.4,WorldLoader.player.getCoordoner().getY()));
				WorldLoader.player.setDirection(Direction.WEST);
			}
		}
	}
}
