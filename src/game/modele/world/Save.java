package game.modele.world;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Pattern;

import game.modele.entity.Entity;
import game.modele.entity.EntityItemOnGround;
import game.modele.entity.living.EntityLiving;
import game.modele.entity.living.Player;
import game.modele.entity.tileEntity.EntityTP;
import game.modele.entity.tileEntity.chest.Chest;
import game.modele.entity.tileEntity.chest.GoldChest;
import game.modele.entity.tileEntity.chest.IronChest;
import game.modele.entity.tileEntity.chest.WoodChest;
import game.modele.entity.tileEntity.light.TikiTorchSmall;
import game.modele.item.weapon.Weapon;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;

public class Save {
	
	public static String saveName = "default";
	
	public static boolean createSave(String name) {
		try {
			File file = new File("saves/");
			file.mkdir();
			file = new File("saves/"+name);
			if(!file.mkdir())
				return false;
			file = new File("saves/"+name+"/data/");
			file.mkdir();
			for(File fil:new File("ressources/map/").listFiles()) {
				if(fil.getName().contains(".entity"))
					Files.copy(fil.toPath(), new File(file.toPath().toString()+"/"+fil.getName()).toPath());
			}
			File player = new File("saves/"+name+"/player");
			player.createNewFile();
			BufferedWriter entitysData = new BufferedWriter(new FileWriter(player.getAbsolutePath()));
			//entitysData.write("Map1,54,56,0,16,13,10,,,");
			entitysData.write("Boss,20,17,0,16,13,10,,,");
			entitysData.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public static void loadSave(String name) {
		try {
			saveName = name;
			BufferedReader playerData = new BufferedReader(new FileReader(new File("saves/"+name+"/player")));
			Pattern pat = Pattern.compile(",");
			String[] valueData = pat.split(playerData.readLine());
			World.initWorldSave(valueData[0],															//Map
					new Coordonnees(Float.parseFloat(valueData[1]),Float.parseFloat(valueData[2])), //Coordonnees
					new Direction(Integer.parseInt(valueData[3])), 										//Direction
					Integer.parseInt(valueData[4]),														//maxPv
					Integer.parseInt(valueData[5]), 													//PV
					Integer.parseInt(valueData[6]), 													//Rubies
					new Weapon[24],
					null,
					null);
			playerData.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveSave() {
		try {
			BufferedWriter entitysData = new BufferedWriter(new FileWriter(new File("saves/"+saveName+"/data/"+World.currentMap.getName()+".entity")));
			BufferedWriter playerData = new BufferedWriter(new FileWriter(new File("saves/"+saveName+"/player")));
			
			for(Entity entity:World.currentMap.getEntity()) {
				if(!entity.getId().equals("Player")) {
					
					entitysData.write(entity.getId());entitysData.newLine();
					entitysData.write(""+entity.coordonnes.getX());
					entitysData.write(","+entity.coordonnes.getY());
					
					if(entity instanceof EntityLiving)
						entitysData.write(","+entity.direction.getDirection());
					else
					switch(entity.getId()) {

					case "EntityTP":
						EntityTP entityTP = (EntityTP)entity;
						entitysData.write(","+entityTP.getEtat());
						entitysData.write(","+entityTP.mapNameTp);
						entitysData.write(","+entityTP.tpCoordonnees.getX());
						entitysData.write(","+entityTP.tpCoordonnees.getY());
						break;

					case "Gold Chest":
						GoldChest goldChest = (GoldChest)entity;
						entitysData.write(","+itemInsideChest(goldChest));
						break;

					case "Iron Chest":
						IronChest ironChest = (IronChest)entity;
						entitysData.write(","+itemInsideChest(ironChest));
						break;

					case "Wood Chest":
						WoodChest woodChest = (WoodChest)entity;
						entitysData.write(","+itemInsideChest(woodChest));
						break;

					case "ItemOnGround":
						EntityItemOnGround entityItemOnGround = (EntityItemOnGround)entity;
						entitysData.write(","+entityItemOnGround.item.name);
						break;
						
					case "TikiTorchSmall":
						TikiTorchSmall tikiTorchSmall = (TikiTorchSmall)entity;
						entitysData.write(","+tikiTorchSmall.getEtat()+
								","+tikiTorchSmall.lightLvl);
						break;
					}
					entitysData.newLine();
				}
			}
			
			playerData.write(World.currentMap.getName()+
					","+World.player.coordonnes.getX()+
					","+World.player.coordonnes.getY()+
					","+World.player.direction.getDirection()+
					","+World.player.getMaxPv().get()+
					","+World.player.getPV().get()+
					","+Player.rupees.get()+
					","+
					","+
					","
					);
			
			entitysData.close();
			playerData.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String itemInsideChest(Chest chest) {
		if(chest.itemInside != null)
			return chest.itemInside.name;
		else
			return null;
	}
	
	public static void deleteSave(String name) {
		File saveFile = new File("saves/"+name);
		if(saveFile.exists())
			deepSupression(saveFile);
		saveFile.delete();
	}
	
	private static void deepSupression(File file) {
		for(File subFile:file.listFiles()) {
			if(subFile.isDirectory()) {
				deepSupression(subFile);
			}
		}
		for(File subFile:file.listFiles()) {
			subFile.delete();
		}
	}
}
