package rpgeeze.model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import rpgeeze.model.entity.Entity;


public class Shield extends TakeableItem implements EquippableItem{
	public Shield() {
		super("Shield");
	}


	public void use(Entity entity) {
		entity.equipAuxiliary(this);
	}
	
	public void equip(Entity entity){
		
	}
	
	public void unequip(Entity entity){
		
	}
	
	public Shield clone(){
		return (Shield) super.clone();
	}
}
