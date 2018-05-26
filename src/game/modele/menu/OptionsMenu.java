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
	
	public static String[] keyFunctionName = {"Avancer","Bas","Droite","Gauche","Inventaire","Reinitializer"};
	public static StringProperty[] keyName = {new SimpleStringProperty("Z"),new SimpleStringProperty("S"),new SimpleStringProperty("D"),new SimpleStringProperty("Q"),new SimpleStringProperty("E")};
	
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
					keyName[Menu.selectedButtonY.get()].setValue(k.toString());
				}
			break;
			
			case 1:
				if(verifBind(k)) {
					Interaction.RECULER = k;
					keyName[Menu.selectedButtonY.get()].setValue(k.toString());
			}
			break;
			
			case 2: 
				if(verifBind(k)) {
					Interaction.DROITE = k;
					keyName[Menu.selectedButtonY.get()].setValue(k.toString());
				}
			break;
			
			case 3: 
				if(verifBind(k)) {
					Interaction.GAUCHE = k;
					keyName[Menu.selectedButtonY.get()].setValue(k.toString());
				}
			break;
			
			case 4: 
				if(verifBind(k)) {
					Interaction.INVENTAIRE = k;
					keyName[Menu.selectedButtonY.get()].setValue(k.toString());
				}
			break;
		 
		}
		SaveKeyBinding();
	}
	
	private static void defaultReset() {
		Interaction.AVANCER = KeyCode.Z;
		keyName[0].setValue(KeyCode.Z.getName());
		Interaction.RECULER = KeyCode.S;
		keyName[1].setValue(KeyCode.S.getName());
		Interaction.DROITE = KeyCode.D;
		keyName[2].setValue(KeyCode.D.getName());
		Interaction.GAUCHE = KeyCode.Q;
		keyName[3].setValue(KeyCode.Q.getName());
		Interaction.INVENTAIRE = KeyCode.E;
		keyName[4].setValue(KeyCode.E.getName());
		SaveKeyBinding();
	}
	
	private static boolean verifBind(KeyCode k) {
		if(k.toString().equals(keyName[0].getValue()) ||
			k.toString().equals(keyName[1].getValue()) ||
			k.toString().equals(keyName[2].getValue()) ||
			k.toString().equals(keyName[3].getValue()) ||
			k.toString().equals(keyName[4].getValue()) ){
				return false;		
		}else {
			return true;
		}
	}
	
	public static void SaveKeyBinding() {
		try {
			BufferedWriter optionsFile = new BufferedWriter(new FileWriter(new File("ressources/saves/options.cfg").getAbsolutePath()));
			optionsFile.write(Interaction.AVANCER+","+Interaction.RECULER+","+Interaction.DROITE+","+Interaction.GAUCHE+","+Interaction.INVENTAIRE);
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
