package model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import model.entity.PC;

public class Helmet extends TakeableItem {
	public Helmet() {
		super("Helmet");
	}
	
	public void activate(PC pc) {
		use(pc);
	}
	public void use(PC pc) {
		pc.equipHead(this);
		view.Console.getInstance().writeLine("Helmet has been equipped.");
	}
}
