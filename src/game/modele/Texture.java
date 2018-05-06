package game.modele;

import java.awt.image.BufferedImage;

public class Texture {
	
	private BufferedImage textureFile;
	private int x,y;
	private int width,height;
	
	public Texture(BufferedImage textureFile,int width, int height, int x, int y) {
		this.textureFile=textureFile;
		this.width=width;
		this.height=height;
		this.x=x;
		this.y=y;
	}
	
	public BufferedImage getTexture() {
		return textureFile.getSubimage(this.x*width, this.y*height, this.width, this.height);
	}
	
}
