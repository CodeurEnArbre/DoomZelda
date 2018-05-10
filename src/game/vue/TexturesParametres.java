package game.vue;

public class TexturesParametres {
	protected String textureFile;
	protected int width, height, x, y;
	
	/*
	 * Cette classe permet de stocker plus facilement les parametres d'entrée des textures
	 * */
	public TexturesParametres(String textureFile, int width,int height,int x,int y) {
		this.textureFile = textureFile;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}
	
	public String getTextureFile() {
		return this.textureFile;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getheight() {
		return this.height;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setTextureFile(String s) {
		this.textureFile = s;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.width = height;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
}
