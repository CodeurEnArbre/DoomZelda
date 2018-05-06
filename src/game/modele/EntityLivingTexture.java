package game.modele;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;

public class EntityLivingTexture {
	
	public static Texture getEntityTexture(String textureFile, int width, int height, int x, int y) {
		BufferedImage tileMap;
		Texture tileTexture=null;
		try {
			tileMap = ImageIO.read(new File("ressources/textures/"+textureFile+".png").toURI().toURL());		
			tileTexture= new Texture(tileMap, width, height, x, y);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
		return tileTexture;
	}

}
