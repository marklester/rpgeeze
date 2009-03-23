package rpgeeze.model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.model.entity.Entity;


public class Boots extends EquipmentBoots {
	public Boots() {
		super("Boots");
		setPrice(100);
	}
	
	public Boots clone(){
		return (Boots) super.clone();
	}
}