package model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */


import model.entity.PC;
import view.Console;

public class HealthPack extends OneShotItem {
	private int amount;
	public HealthPack() {
		super("Health Pack");
		this.amount= 10;
	}

	public void activate(PC pc){
//		int prev = pc.getStats().getLife();
//		pc.getStats().incLife(amount);
//		int end = pc.getStats().getLife() - prev;
		pc.addHealth(amount);
		Console.getInstance().writeLifeEvent("Gained " + amount + " Health");
//		if(end != 0)
//			
		//might not want to kill it if it doesn't gain health from pack
		// yeah you do, it's a one-shot item by definition
	}

}
