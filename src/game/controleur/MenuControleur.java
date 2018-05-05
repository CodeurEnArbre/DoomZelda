package game.controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class MenuControleur implements Initializable {
	
	@FXML
    private Canvas canvas;
	
	@FXML
    void option(ActionEvent event) {

    }

    @FXML
    void play(ActionEvent event) {
    	canvas.setVisible(true);
    	canvas.getGraphicsContext2D().fillRect(0, 0, 32, 32);
		
    }

    @FXML
    void quitter(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		canvas.setVisible(false);
				
	}

}
