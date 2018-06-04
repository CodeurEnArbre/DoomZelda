package game.vue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class ItemTexture {
	
	public static Image getItemTexture(int id) {
		BufferedImage tileMap;
		Image tileTexture=null;
		try {
			tileMap = ImageIO.read(new File("ressources/textures/ItemTextureMap.png").toURI().toURL());
			
			tileTexture = SwingFXUtils.toFXImage(tileMap.getSubimage(32, 32, id%16, id/16), null);
		} catch (MalformedURLException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
		return tileTexture;
	}
	
}
