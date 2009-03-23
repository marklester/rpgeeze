package rpgeeze.model.item;

import rpgeeze.model.entity.Entity;

public class Crossbow extends RangedWeapon {
	public Crossbow() {
		super("Crossbow");
		setPrice(75);
	}
	
	public Crossbow clone(){
		return (Crossbow) super.clone();
	}
	
	public void use(Entity entity) {
		equip(entity);	
	}
}
