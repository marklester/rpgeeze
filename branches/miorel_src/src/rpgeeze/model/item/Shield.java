package rpgeeze.model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import rpgeeze.model.entity.Entity;


public class Shield extends EquipmentAuxiliary {
	public Shield() {
		super("Shield");
		setPrice(20);
	}

	public Shield clone(){
		return (Shield) super.clone();
	}
}
