package rpgeeze.model.item;

import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.model.Tile;
import rpgeeze.model.entity.Entity;


/**
 * An Item that is added to an Entity's Inventory on touch.
 */

public abstract class CloseWeapon extends TakeableItem implements EquippableItem{	
	public CloseWeapon(String name) {
        super(name);
	}

	public abstract void use(Entity entity);
	public CloseWeapon clone() {
		return (CloseWeapon) super.clone();
	}
	public void equip(Entity entity){
		if(entity.getOccupation().getName().compareTo("Smasher")==0){
			entity.unequipAuxiliary();
			entity.equipWeapon(this);
			LogManager.getInstance().log(getName()+" has been equipped.", "", Message.Type.GAME);	
		}
	}
	
	public void unequip(Entity entity){
		entity.addItemSilently(this);
		entity.unequipWeapon();
		LogManager.getInstance().log(getName()+" has been unequipped.", "", Message.Type.GAME);
	}
}
