package model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import java.awt.Color;

import model.Entity;
import model.Location;
import view.Console;
import view.Drawer;

public class HealthPack extends OneShotItem {
	private int amount;
	public HealthPack() {
		super("Health Pack");
		this.amount= 10;
	}
	public void activate(Entity e){
		int prev = e.getStats().getLife();
		e.getStats().incLife(amount);
		int end = e.getStats().getLife()-prev;
		if(end!=0)
			Console.getInstance().writeLine("Gained "+end+"Health", Color.GREEN);
		//might not want to kill it if it doesn't gain health from pack
	}
	public void draw(Drawer d) {
		//d.drawHealthPack(this);
	}
}
