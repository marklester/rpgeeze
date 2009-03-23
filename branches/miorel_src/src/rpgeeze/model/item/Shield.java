package rpgeeze.model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import model.entity.PC;
import view.Drawer;

public class Shield extends TakeableItem {
	public Shield() {
		super("Shield");
	}


	public void use(PC pc) {
		pc.equipAuxiliary(this);
	}
}
