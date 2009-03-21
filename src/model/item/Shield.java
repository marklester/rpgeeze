package model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import model.entity.Entity;
import view.Drawer;

public class Shield extends TakeableItem {
	public Shield() {
		super("Shield");
	}

	public void activate(Entity e) {
		use(e);
	}

	public void use(Entity e) {
		e.equipAuxiliary(this);
		if(e.getEquipment().auxiliary == this)
			view.Console.getInstance().writeLine("Shield has been equipped.");
	}
}
