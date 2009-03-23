package rpgeeze.model.item;

import rpgeeze.model.entity.Entity;

/** 
 *Equippable Item probably brings up defense
 */

public class Shield extends TakeableItem {
	public Shield() {
		super("Shield");
	}

	public void activate(Entity e) {
		use(e);
	}

	public void use(Entity e) {
		/*e.equipAuxiliary(this);
		if(e.getEquipment().auxiliary == this)
			view.Console.getInstance().writeLine("Shield has been equipped.");
		*/
	}
}
