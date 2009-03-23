package rpgeeze.model.item;

import rpgeeze.model.entity.Entity;

public class BigGun extends RangedWeapon {
	public BigGun() {
		super("Big Gun");
		setPrice(75);
	}
	
	public BigGun clone(){
		return (BigGun) super.clone();
	}
	
	public void use(Entity entity) {
		equip(entity);	
	}
}
