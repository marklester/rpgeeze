package rpgeeze.model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.model.Tile;
import rpgeeze.model.entity.Entity;
import rpgeeze.model.entity.PC;

public class Crossbow extends TakeableItem implements EquippableItem {
	public Crossbow() {
		super("Crossbow");
	}

	public void equip(Entity entity){
		
	}
	
	public void unequip(Entity entity){
		
	}
	
	public Crossbow clone(){
		return (Crossbow) super.clone();
	}
	public void use(Entity entity) {
		//entity.equipWeapon(this);
		entity.unequipAuxiliary();
		LogManager.getInstance().log("Cross Bow has been equipped.", "", Message.Type.GAME);
	}
}
