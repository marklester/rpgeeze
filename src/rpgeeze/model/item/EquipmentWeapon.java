package rpgeeze.model.item;

import rpgeeze.model.entity.Entity;

public class EquipmentWeapon extends EquippableItem {
	private int effectiveness;
	protected EquipmentWeapon(String name) {
		super(name);
	}

	protected EquipmentWeapon(String name, String regex) {
		super(name, regex);
	}
	
	protected boolean doEquip(Entity entity) {
		entity.removeItem(this);
		EquippableItem old = entity.getEquipment().getWeapon();
		if(old != null)
			old.unequip(entity);
		entity.getEquipment().setWeapon(this);
		entity.getStats().calculateOffensiveRating(this.effectiveness);
		return entity.getEquipment().getWeapon() == this;
	}

	protected boolean doUnequip(Entity entity) {
		if(entity.addItem(this))
			entity.getEquipment().setWeapon(null);
		return entity.getEquipment().getWeapon() != this;
	}
	
	public void setEffectiveness(int ef) {
		this.effectiveness = ef;
	}
	
	public int getEffectiveness() {
		return effectiveness;
	}
}
