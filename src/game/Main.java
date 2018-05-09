package game;

import java.io.File;
import java.net.URL;

import game.controler.MenuControler;
import game.modele.world.WorldLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	
	@Override
	public void start(Stage primaryStage)  {	
		try {
			System.out.println("Loading game... ");
			FXMLLoader loader = new FXMLLoader();
			URL url = new File("src/game/vue/game.fxml").toURI().toURL();
			loader.setLocation(url);
			Scene scene = new Scene(FXMLLoader.load(url),864,640);
			scene.setOnKeyPressed(event -> WorldLoader.KeyInteractDown(event.getCode()));//KEY
			scene.setOnKeyReleased(event ->WorldLoader.KeyInteractUp(event.getCode()));
			primaryStage.setScene(scene);
			primaryStage.setTitle("Doom Zelda");
			primaryStage.setResizable(false);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
