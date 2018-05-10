package game.modele.entity;

import java.util.ArrayList;

import game.modele.world.WorldLoader;
import javafx.application.Platform;

public class EntityUpdate {
	
	public static void update() {
		ArrayList<Entity> mapEntity=WorldLoader.currentMap.getEntity();
		for(Entity entity:mapEntity) {
			if(entity instanceof TileEntityTP)
				tileEntityTpUpdate((TileEntityTP) entity);
			else if(entity instanceof TileEntity)
				tileEntityUpdate((TileEntity) entity);
			else
				entityLivingUpdate((EntityLiving) entity);
		}
	}
	
	private static void tileEntityTpUpdate(TileEntityTP entity) {
		if(WorldLoader.player.isOnTileCoord(entity.getCoordoner())) {
			Platform.runLater(new Runnable(){
				@Override
				public void run() {
					WorldLoader.loadWorld(entity.getTPmapName(),entity.getTPmapName());
				}
				
			});
			WorldLoader.player.forceTp(entity.getTPCoordonnees());
			
		}
	}
	
	private static void tileEntityUpdate(TileEntity entity) {
		//TODO
		
	}
	
	private static void entityLivingUpdate(EntityLiving entity) {
		//TODO
		
	}
}
