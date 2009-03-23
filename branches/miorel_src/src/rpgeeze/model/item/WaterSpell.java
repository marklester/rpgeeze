package rpgeeze.model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.model.entity.Entity;


public class WaterSpell extends SpellWeapon{
	public WaterSpell() {
		super("Water Spell");
	}

	public WaterSpell clone(){
		return (WaterSpell) super.clone();
	}
}
