package model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import model.entity.PC;

public class Arrows extends TakeableItem {

	public Arrows() {
		super("Arrows");
	}

	public void use(PC pc) {
		//view.Console.getInstance().writeLine("You have picked up Arrows.");
	}
}
