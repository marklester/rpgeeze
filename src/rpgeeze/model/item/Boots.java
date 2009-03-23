package rpgeeze.model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.model.entity.Entity;


public class Boots extends TakeableItem implements EquippableItem{
	public Boots() {
		super("Boots");
	}
	
	public void equip(Entity entity)
	{
		entity.addMovement(10);
	}
	
	public void unequip(Entity entity)
	{
		entity.addMovement(-10);
	}
	
	public Boots clone(){
		return (Boots) super.clone();
	}
	public void use(Entity entity) {
		entity.equipBoots(this);
		
		LogManager.getInstance().log("Boots have been equipped.", "", Message.Type.GAME);
	}
}