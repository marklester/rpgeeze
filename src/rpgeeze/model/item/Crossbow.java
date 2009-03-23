package rpgeeze.model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.model.entity.Entity;

public class Crossbow extends TakeableItem implements EquippableItem {
	public Crossbow() {
		super("Crossbow");
		setPrice(75);
	}

	public void equip(Entity entity){
		entity.unequipAuxiliary();
		
		entity.equipWeapon(this);
		LogManager.getInstance().log("Crossbow has been equipped.", "", Message.Type.GAME);	
	}
	
	public void unequip(Entity entity){
		
	}
	
	public Crossbow clone(){
		return (Crossbow) super.clone();
	}
	
	public void use(Entity entity) {
		equip(entity);	
	}
}
