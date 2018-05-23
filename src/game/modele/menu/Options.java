package game.modele.menu;

import java.util.Scanner;

import game.controler.Interaction;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.input.KeyCode;

public class Options {
	
	public static StringProperty bind1 = new SimpleStringProperty("Z");
	public static StringProperty bind2 = new SimpleStringProperty("S");
	public static StringProperty bind3 = new SimpleStringProperty("D");
	public static StringProperty bind4 = new SimpleStringProperty("Q");
	
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
					bind1.setValue(k.toString());
				}
			break;
			
			case 1:
				if(verifBind(k)) {
					Interaction.RECULER = k;
					bind2.setValue(k.toString());
			}
			break;
			
			case 2: 
				if(verifBind(k)) {
					Interaction.GAUCHE = k;
					bind3.setValue(k.toString());
				}
			break;
			
			case 3: 
				if(verifBind(k)) {
					Interaction.DROITE = k;
					bind4.setValue(k.toString());
				}
			break;
		 
		}
		
	}
	
	public static void enterPressed() {
		if(selecterInOption.get() == 4) {
			defaultReset();
		}else {
			enterOption.set(true);
		}
	}
	
	private static void defaultReset() {
		Interaction.AVANCER = KeyCode.Z;
		bind1.setValue("Z");
		Interaction.RECULER = KeyCode.S;
		bind2.setValue("S");
		Interaction.DROITE = KeyCode.D;
		bind3.setValue("D");
		Interaction.GAUCHE = KeyCode.Q;
		bind4.setValue("Q");
	}
	
	private static boolean verifBind(KeyCode k) {
		if(k.toString().equals(bind1.getValue()) ||
			k.toString().equals(bind2.getValue()) ||
			k.toString().equals(bind3.getValue()) ||
			k.toString().equals(bind4.getValue())) {
				System.out.println("TOUCHE DEJA ASSIGNEE");
				return false;		
		}else {
			return true;
		}
	}
	
	public static void Save(String name) {
		//TODO
	}
	
}
