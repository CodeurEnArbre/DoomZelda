package game.modele.menu;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import game.controler.Interaction;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.input.KeyCode;

public class OptionsMenu {
	
	public static StringProperty upKey = new SimpleStringProperty("Z");
	public static StringProperty downKey = new SimpleStringProperty("S");
	public static StringProperty rightKey = new SimpleStringProperty("D");
	public static StringProperty leftKey = new SimpleStringProperty("Q");
	public static StringProperty inventoryKey = new SimpleStringProperty("E");
	
	public static BooleanProperty enterOption=new SimpleBooleanProperty(false);
	
	public static void bindTouche() {
		Scanner sc = new Scanner(System.in);
		String newBind = "";
		while(newBind.length() != 1) {
			newBind = sc.next();
			System.out.println(newBind);
		}
		sc.close();
	}

	public static void setBind(KeyCode k) {
		switch(Menu.selectedButtonY.get()) {
			case 0: 
				if(verifBind(k)) {
					Interaction.AVANCER = k;
					upKey.setValue(k.toString());
				}
			break;
			
			case 1:
				if(verifBind(k)) {
					Interaction.RECULER = k;
					downKey.setValue(k.toString());
			}
			break;
			
			case 2: 
				if(verifBind(k)) {
					Interaction.DROITE = k;
					rightKey.setValue(k.toString());
				}
			break;
			
			case 3: 
				if(verifBind(k)) {
					Interaction.GAUCHE = k;
					leftKey.setValue(k.toString());
				}
			break;
			
			case 4: 
				if(verifBind(k)) {
					Interaction.INVENTAIRE = k;
					inventoryKey.setValue(k.toString());
				}
			break;
		 
		}
		SaveKeyBinding();
	}
	
	private static void defaultReset() {
		Interaction.AVANCER = KeyCode.Z;
		upKey.setValue(KeyCode.Z.getName());
		Interaction.RECULER = KeyCode.S;
		downKey.setValue(KeyCode.S.getName());
		Interaction.DROITE = KeyCode.D;
		rightKey.setValue(KeyCode.D.getName());
		Interaction.GAUCHE = KeyCode.Q;
		leftKey.setValue(KeyCode.Q.getName());
		SaveKeyBinding();
	}
	
	private static boolean verifBind(KeyCode k) {
		if(k.toString().equals(upKey.getValue()) ||
			k.toString().equals(downKey.getValue()) ||
			k.toString().equals(rightKey.getValue()) ||
			k.toString().equals(leftKey.getValue())) {
				return false;		
		}else {
			return true;
		}
	}
	
	public static void SaveKeyBinding() {
		try {
			BufferedWriter optionsFile = new BufferedWriter(new FileWriter(new File("ressources/saves/options.txt").getAbsolutePath()));
			optionsFile.write(Interaction.AVANCER+","+Interaction.RECULER+","+Interaction.DROITE+","+Interaction.GAUCHE);
			optionsFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void validate() {
		if(Menu.selectedButtonY.get() == 5) {
			defaultReset();
		}else {
			enterOption.set(true);
		}
		
	}
	
}
