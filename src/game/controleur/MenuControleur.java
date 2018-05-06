package game.controleur;

import java.net.URL;
import java.util.ResourceBundle;

import game.controleur.world.WorldLoader;
import game.modele.TileTexture;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class MenuControleur implements Initializable {
	
	@FXML
    private TilePane TilePaneGround;

    @FXML
    private TilePane TilePaneSolid;

    @FXML
    private TilePane TilePaneTop;
	
	@FXML
    void option(ActionEvent event) {

    }

    @FXML
    void play(ActionEvent event) {
    	
		
    }

    @FXML
    void quitter(ActionEvent event) {

    }
    
    private void printCalque(TilePane pane,int calque) {
    	
    			for(int y=0;y<WorldLoader.currentMap.getHeight();y++) {
    				for(int x=0;x<WorldLoader.currentMap.getWidth();x++) {
    					
    						ImageView texture = new ImageView();
    						switch (calque){
    			    		case 0:
    			    			if(WorldLoader.currentMap.getTileTerrain(x, y)!=null) {
    			    				texture.setImage(SwingFXUtils.toFXImage(TileTexture.getTileTexture(WorldLoader.currentMap.getTileTerrain(x, y).getId()-1).getTexture(), null));      						
    			    				pane.getChildren().add(texture);
    			    			}
    			    			break;
    			    		case 1:
    			    			if(WorldLoader.currentMap.getTile(x, y)!=null) {
    			    				texture.setImage(SwingFXUtils.toFXImage(TileTexture.getTileTexture(WorldLoader.currentMap.getTile(x, y).getId()-1).getTexture(), null));      						
    			    				pane.getChildren().add(texture);
    			    			}
    			    			break;
    			    		case 2:
    			    			if(WorldLoader.currentMap.getTileTop(x, y)!=null) {
    			    				texture.setImage(SwingFXUtils.toFXImage(TileTexture.getTileTexture(WorldLoader.currentMap.getTileTop(x, y).getId()-1).getTexture(), null)); 
    			    				pane.getChildren().add(texture);
    			    			}
    			    			break;
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
		
		printCalque(TilePaneGround,0);
		printCalque(TilePaneSolid,1);
		printCalque(TilePaneTop,2);
	}

}
