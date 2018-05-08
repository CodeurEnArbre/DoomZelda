package game.vue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;

public class TextureLoader {
	
	public static Texture getTextureMapImage(String textureMapName, int textureWidth, int textureHeight, int textureMapWidth, int textureMapHeight, int id) {
		BufferedImage tileMap;
		Texture tileTexture=null;
		try {
			tileMap = ImageIO.read(new File("ressources/textures/"+textureMapName+".png").toURI().toURL());
			
			tileTexture= new Texture(tileMap, textureWidth, textureHeight, id%textureMapWidth, id/textureMapHeight);
		} catch (MalformedURLException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
		return tileTexture;
	}
}
