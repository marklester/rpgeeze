package rpgeeze.model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.model.entity.Entity;


public class Sword extends CloseWeapon{
	public Sword() {
		super("Sword");
	}
	public void use(Entity entity) {
		entity.equipWeapon(this);
		LogManager.getInstance().log("Equipped " + this, "MODEL", Message.Type.GAME);
	}
	public Sword clone(){
		return (Sword) super.clone();
	}
}
