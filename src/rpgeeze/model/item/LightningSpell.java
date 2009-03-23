package rpgeeze.model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.model.entity.Entity;


public class LightningSpell extends SpellWeapon {
	public LightningSpell() {
		super("Lightning Spell");
	}
	public LightningSpell clone(){
		return (LightningSpell) super.clone();
	}
}
