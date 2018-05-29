package game.modele.entity.living;

import game.modele.entity.Entity;
import game.modele.item.Item;
import game.modele.item.loot.Loot;
import game.modele.item.special.Special;
import game.modele.item.usable.Usable;
import game.modele.item.weapon.Weapon;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import game.modele.world.World;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class EntityLiving extends Entity{
	
	//La direction du regard
	protected IntegerProperty PV;
	protected IntegerProperty maxPv;
	public ObservableList<Item> itemsEnMain;
	
	public EntityLiving(String id,Coordonnees position, Direction direction) {
		super(id,position,direction);
		
		PV=new SimpleIntegerProperty(12);
		maxPv=new SimpleIntegerProperty(12);
		itemsEnMain = FXCollections.observableArrayList();
	}
	
	public EntityLiving(String id,Coordonnees position, Direction direction, int pv) {
		super(id,position,direction);
		this.direction=direction;
		PV.set(pv);
	}
	
	public void setDirection(Direction direction) {
		this.direction=direction;
	}
	
	public void perdrePV(int degats) {
		if(PV.getValue() > degats)
			PV.set(PV.get() - degats);
		else
			PV.set(0);
	}
	
	public void gagnerPV() {
		if(PV.getValue()<maxPv.getValue())
			PV.set(PV.get()+1);
	}
	
	public IntegerProperty getPV() {
		return PV;
	}
	
	public void addPermanentHeart() {
		this.maxPv.add(4);
	}
	
	public IntegerProperty getMaxPv() {
		return this.maxPv;
	}
	
	public void setWeaponEnMain(Weapon w) {
		int i = 0;
		while(World.player.weapons.get(i).getPrimaryKey() != w.getPrimaryKey()){
			i++;
		}
		this.itemsEnMain.add(w);
	}
	
	public void setUsableEnMain(Usable u) {
		int i = 0;
		while(World.player.usables.get(i).getPrimaryKey() != u.getPrimaryKey()) {
			i++;
		}
		itemsEnMain.add(u);
	}
	
	public void setLootEnMain(Loot l) {
		int i = 0;
		while(World.player.loots.get(i).getPrimaryKey() != l.getPrimaryKey()){
			i++;
		}
		itemsEnMain.add(l);
	}
	
	public void setSpecialnEnMain(Special s) {
		int i = 0;
		while(World.player.specials.get(i).getPrimaryKey() != s.getPrimaryKey()){
			i++;
		}
		itemsEnMain.add(s);
	}
	
	public void setAucunItemEnMain() {
		itemsEnMain.clear();
	}
	
	//deplacement
}
