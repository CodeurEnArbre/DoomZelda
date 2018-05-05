package game;

import java.io.File;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


	@Override
	public void start(Stage primaryStage) {
		try {
		System.out.println("Loading game");
		FXMLLoader loader = new FXMLLoader();
		URL url = new File("src/game/vue/menu.fxml").toURI().toURL();
		loader.setLocation(url);
		Parent root = FXMLLoader.load(url);
     	Scene scene = new Scene(root,600,450);
		primaryStage.setScene(scene);
		primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
