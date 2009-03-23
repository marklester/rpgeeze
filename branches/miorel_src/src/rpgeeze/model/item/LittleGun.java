package rpgeeze.model.item;

import rpgeeze.model.entity.Entity;

public class LittleGun extends RangedWeapon {
	public LittleGun() {
		super("Little Gun");
		setPrice(75);
	}
	
	public LittleGun clone(){
		return (LittleGun) super.clone();
	}
	
	public void use(Entity entity) {
		equip(entity);	
	}
}
