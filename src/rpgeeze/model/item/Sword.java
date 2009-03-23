package rpgeeze.model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
//import model.entity.PC;
import rpgeeze.model.entity.PC;

public class Sword extends TakeableItem {
	public Sword() {
		super("Sword");
	}

	public void use(PC pc) {
		//pc.equipWeapon(this);
		LogManager.getInstance().log("Equipped " + this, "MODEL", Message.Type.GAME);
	}
}
