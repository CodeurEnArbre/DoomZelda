package game.vue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class TextureLoader {
	
	public static Image getTextureMapImage(String textureMapName, int textureWidth, int textureHeight, int x, int y) {
		BufferedImage tileMap;
		Image tileTexture = null;
		try {
			tileMap = ImageIO.read(new File("ressources/textures/"+textureMapName+".png").toURI().toURL());
			tileTexture = SwingFXUtils.toFXImage(tileMap.getSubimage(textureWidth*x,textureHeight*y,textureWidth,textureHeight), null);
		} catch (MalformedURLException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
		return tileTexture;
	}
}
