package game.vue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class EntityLivingTexture {
	
	public static Image getEntityTexture(String textureFile, int width, int height, int x, int y) {
		BufferedImage tileMap;
		Image tileTexture = null;
		try {
			tileMap = ImageIO.read(new File("ressources/textures/"+textureFile+".png").toURI().toURL());		
			tileTexture = SwingFXUtils.toFXImage(tileMap.getSubimage(width*x, height*y, width, height), null);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
		return tileTexture;
	}

}
