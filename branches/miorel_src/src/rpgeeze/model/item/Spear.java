package rpgeeze.model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.model.entity.Entity;


public class Spear extends CloseWeapon{
	public Spear() {
		super("Spear");
		setEffectiveness(50);
	}

	public Spear clone(){
		return (Spear) super.clone();
	}
}
