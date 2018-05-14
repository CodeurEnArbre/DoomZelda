package game.modele.entity;

import java.util.ArrayList;

import game.modele.entity.living.EntityLiving;
import game.modele.world.World;
import javafx.application.Platform;

public class EntityUpdate {
	
	public static void update() {
		ArrayList<Entity> mapEntity=World.currentMap.getEntity();
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
		if(World.player.isOnTileCoord(entity.coordonnes)) {
			Platform.runLater(new Runnable(){
				@Override
				public void run() {
					World.loadWorld(entity.getTPmapName(),entity.getTPmapName());
				}
				
			});
			World.player.forceTp(entity.getTPCoordonnees());
		}
	}
	private static void tileEntityUpdate(TileEntity entity) {
		//TODO
		
	}
	
	private static void entityLivingUpdate(EntityLiving entity) {
		//TODO
		
	}
}
