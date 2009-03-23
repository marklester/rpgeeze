package rpgeeze.model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.model.entity.Entity;


public class Axe extends CloseWeapon{
	public Axe() {
		super("Axe");
		this.setEffectiveness(30);
	}
}
