package model.item;

import model.entity2.PC;
import view.Console;
import view.Drawer;

public class Trap extends OneShotItem {
	private int amount;
	private boolean isShowing = false;
	public Trap() {
		super("Trap");
		this.amount= -10;
	}

	public void activate(PC pc){
		pc.addHealth(amount);
		isShowing = true;
		Console.getInstance().writeLifeEvent("SURPRISE!! You just lost " + amount + " life!!");
	}
	
	public void draw(Drawer d) {
		if(isShowing)
			d.drawMe(name);
	}

}