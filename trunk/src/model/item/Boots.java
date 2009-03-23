package model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import model.entity2.PC;

public class Boots extends TakeableItem {
	public Boots() {
		super("Boots");
	}
	
	public void deActivate(PC pc)
	{
		pc.addMovement(-10);
	}
	
	public void use(PC pc) {
		pc.equipBoots(this);
		pc.addMovement(10);
		view.Console.getInstance().writeLine("Boots have been equipped.");
	}
}