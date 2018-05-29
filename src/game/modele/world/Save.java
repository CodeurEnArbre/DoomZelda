package game.modele.world;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.regex.Pattern;

import game.modele.item.loot.Loot;
import game.modele.item.special.Special;
import game.modele.item.usable.Usable;
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
			entitysData.write("TinyMap,14,10,2,16,13,23");
			entitysData.close();
		} catch (IOException e) {
			System.out.println("No options found");
		}
		return true;
	}
	
	public static void loadSave(String name) {
		try {
			saveName = name;
			BufferedReader playerData = new BufferedReader(new FileReader(new File("saves/"+name+"/player")));
			Pattern pat = Pattern.compile(",");
			String[] valueData = pat.split(playerData.readLine());
			World.initWorldSave(valueData[0],
					new Coordonnees(Double.parseDouble(valueData[1]),Double.parseDouble(valueData[2])), //Coordonnees
					new Direction(Integer.parseInt(valueData[3])), 										//Direction
					Integer.parseInt(valueData[4]),														//maxPv
					Integer.parseInt(valueData[5]), 													//PV
					Integer.parseInt(valueData[6]), 													//Ruby
					new ArrayList<Loot>(),
					new ArrayList<Usable>(),
					new ArrayList<Weapon>(),
					new ArrayList<Special>());			
			playerData.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveSave() {
		//TODO un jour
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
