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

public class Helmet extends TakeableItem implements EquippableItem{
	public Helmet() {
		super("Helmet");
	}

	public void equip(Entity entity){
		
	}
	
	public void unequip(Entity entity){
		
	}
	
	public void use(Entity entity) {
		entity.equipHead(this);
		LogManager.getInstance().log("Helmet has been equipped", "", Message.Type.GAME);
	}

	public Helmet clone(){
		return (Helmet) super.clone();
	}
}
