package rpgeeze.model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.model.entity.Entity;


public class RedArmor extends TakeableItem implements EquippableItem {
	public RedArmor() {
		super("Red Armor");
		setPrice(50);
	}

	public void use(Entity entity) {
		entity.equipArmor(this);
		LogManager.getInstance().log("Armor has been equipped", "", Message.Type.GAME);
	}
	
	public RedArmor clone(){
		return (RedArmor) super.clone();
	}
	
	public void unequip(Entity entity){
		
	}
	
	public void equip(Entity entity){
		
	}
}
