package game.modele.entity.living;

import game.modele.entity.Entity;
import game.modele.item.Item;
import game.modele.item.weapon.Weapon;
import game.modele.menu.Menu;
import game.modele.utils.Coordonnees;
import game.modele.utils.Direction;
import game.modele.utils.ActionConsumer.CountActionConsumer;
import game.modele.utils.ActionConsumer.InfiniteActionConsumer;
import game.modele.utils.ActionConsumer.Function.Function;
import game.modele.utils.ActionConsumer.Function.FunctionDamage;
import game.modele.utils.ActionConsumer.Function.FunctionRigidbody;
import game.modele.world.Save;
import game.modele.world.World;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class EntityLiving extends Entity{

	//La direction du regard
	protected IntegerProperty PV;
	protected IntegerProperty maxPv;
	public ObservableList<Item> itemsEnMain;
	public IntegerProperty action = new SimpleIntegerProperty(-1);
	public BooleanProperty isRaising = new SimpleBooleanProperty(false);
	public BooleanProperty isInvulnerable = new SimpleBooleanProperty(false);
	public BooleanProperty isDamaged = new SimpleBooleanProperty(false);



	public EntityLiving(String id,Coordonnees position, Direction direction) {
		super(id,position,direction);
		addAction(new InfiniteActionConsumer(new FunctionRigidbody()));
		PV = new SimpleIntegerProperty(12);
		maxPv = new SimpleIntegerProperty(12);
		itemsEnMain = FXCollections.observableArrayList();
	}

	public EntityLiving(String id,Coordonnees position, Direction direction, int pv) {
		this(id, position, direction);
		PV.set(pv);
	}

	public void setDirection(Direction direction) {
		this.direction=direction;
	}

	public void perdrePV(int degats) {
		if(!isInvulnerable.get() && !isDamaged.get()) {
			addAction(new CountActionConsumer(20,new FunctionDamage()));
			if(PV.getValue() > degats)
				PV.set(PV.get() - degats);
			else
				PV.set(0);
			if(!isDamaged.get())
				isDamaged.set(true);
		}
		
		if(PV.get()<=0) {
			Menu.currentMenu.set(Menu.DeathMenuID);
			action.set(Actions.die.get());
			if(id.equals("Player")) {
				this.clearAction();
				this.isDamaged.set(false);
				this.addAction(new CountActionConsumer(120,new Function() {
					int animDeath = 0;
					
					@Override
					public void Action(Entity e) {
						System.out.println("act :"+etatDeplacement.get());
						if(animDeath<80 && animDeath>20) {
							etatDeplacement.set(animDeath+1-20);
						}
						animDeath++;
						
					}
					
					@Override
					public void finishAction(Entity e) {
						System.out.println("Loading save");
						
						Save.loadSave(Save.saveName);
						Menu.currentMenu.set(Menu.NoMenuID);
					}
					
				}));
				
			}
		}
		
	}

	public void gagnerPV(int pv) {
		if(PV.getValue()+pv < maxPv.getValue())
			PV.set(PV.get()+pv);
		else
			PV.set(maxPv.getValue());
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
		for(int i = 0; World.player.weapons[i].getPrimaryKey() != w.getPrimaryKey();i++);
	
		this.itemsEnMain.add(w);
	}

	public void setAucunItemEnMain() {
		itemsEnMain.clear();
	}

	@Override
	public void incAnim() {
		this.etatDeplacement.set(
				this.etatDeplacement.get()
				+ (this.etatDeplacement.get() < 83 ? 1 : -83));
	}
}
