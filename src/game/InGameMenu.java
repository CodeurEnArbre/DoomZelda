package game;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.input.KeyCode;

import java.util.Scanner;

public class InGameMenu {
	
	
	public static StringProperty bind1 = new SimpleStringProperty();
	public static StringProperty bind2 = new SimpleStringProperty();
	public static StringProperty bind3 = new SimpleStringProperty();
	public static StringProperty bind4 = new SimpleStringProperty();
	public static IntegerProperty selectedButtonInGame=new SimpleIntegerProperty(0);
	public static IntegerProperty selecterInOption=new SimpleIntegerProperty(0);
	public static BooleanProperty enterMenu=new SimpleBooleanProperty(false);
	public static BooleanProperty enterOption=new SimpleBooleanProperty(false);
	private static Scanner sc = new Scanner(System.in);

	
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
		if(selecterInOption.get() < 3)
			selecterInOption.set(selecterInOption.get()+1);	
		
	}
	
	public static void selectionUpOption() {
		if(selecterInOption.get() > 0)
			selecterInOption.set(selecterInOption.get()-1);			
	}

	public static void bindTouche() {
		String newBind = "";
		while(newBind.length() != 1) {
			newBind = sc.next();
			System.out.println(newBind);
		}
		sc.close();
	}

	public static void setBind(KeyCode k) {
		switch(selecterInOption.get()) {
			case 0: bind1.setValue(k.toString());
		//System.out.println(bind1.get());
		}
		
	}
}
