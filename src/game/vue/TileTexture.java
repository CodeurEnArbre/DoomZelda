package game.vue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;

public class TileTexture {
	
	public static Texture getTileTexture(int id) {
		BufferedImage tileMap;
		Texture tileTexture=null;
		try {
			tileMap = ImageIO.read(new File("ressources/textures/textureMap.png").toURI().toURL());
			
			tileTexture= new Texture(tileMap, 32, 32, id%16, id/16);
		} catch (MalformedURLException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
		return tileTexture;
	}
}
