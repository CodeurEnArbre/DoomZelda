package game.modele.menu;

import java.util.Scanner;

import game.controler.Interaction;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.input.KeyCode;

public class Options {
	
	public static StringProperty upKey = new SimpleStringProperty("Z");
	public static StringProperty downKey = new SimpleStringProperty("S");
	public static StringProperty rightKey = new SimpleStringProperty("D");
	public static StringProperty leftKey = new SimpleStringProperty("Q");
	
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
		switch(Menu.selectedButton.get()) {
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
					Interaction.GAUCHE = k;
					rightKey.setValue(k.toString());
				}
			break;
			
			case 3: 
				if(verifBind(k)) {
					Interaction.DROITE = k;
					leftKey.setValue(k.toString());
				}
			break;
		 
		}
		
	}
	
	public static void enterPressed() {
		if(Menu.selectedButton.get() == 4) {
			defaultReset();
		}else {
			enterOption.set(true);
		}
	}
	
	private static void defaultReset() {
		Interaction.AVANCER = KeyCode.Z;
		upKey.setValue("Z");
		Interaction.RECULER = KeyCode.S;
		downKey.setValue("S");
		Interaction.DROITE = KeyCode.D;
		rightKey.setValue("D");
		Interaction.GAUCHE = KeyCode.Q;
		leftKey.setValue("Q");
	}
	
	private static boolean verifBind(KeyCode k) {
		if(k.toString().equals(upKey.getValue()) ||
			k.toString().equals(downKey.getValue()) ||
			k.toString().equals(rightKey.getValue()) ||
			k.toString().equals(leftKey.getValue())) {
				System.out.println("TOUCHE DEJA ASSIGNEE");
				return false;		
		}else {
			return true;
		}
	}
	
	public static void Save(String name) {
		//TODO
	}

	public static void validate() {
		// TODO Auto-generated method stub
		
	}
	
}
