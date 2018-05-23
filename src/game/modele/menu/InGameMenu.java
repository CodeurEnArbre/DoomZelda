package game.modele.menu;

import java.util.Scanner;

import game.controler.Interaction;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.input.KeyCode;

public class InGameMenu {
	
	
	public static IntegerProperty selectedButtonInGame=new SimpleIntegerProperty(0);
	public static IntegerProperty selecterInOption=new SimpleIntegerProperty(0);
	public static BooleanProperty enterMenu=new SimpleBooleanProperty(false);
	public static BooleanProperty enterOption=new SimpleBooleanProperty(false);

	
	public static void Save(String name) {
		//TODO
	}

	public static void selectionDown() {
		if(selectedButtonInGame.get() < 2)
			selectedButtonInGame.set(selectedButtonInGame.get()+1);			
	}
	
	public static void selectionUp() {
		if(selectedButtonInGame.get() > 0)
			selectedButtonInGame.set(selectedButtonInGame.get()-1);			
	}

	public static void selectionDownOption() {
		if(selecterInOption.get() < 4)
			selecterInOption.set(selecterInOption.get()+1);	
		
	}
	
	public static void selectionUpOption() {
		if(selecterInOption.get() > 0)
			selecterInOption.set(selecterInOption.get()-1);			
	}


}
