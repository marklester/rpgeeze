package rpgeeze.model.item;

import rpgeeze.model.entity.Entity;

public class EquipmentBoots extends EquippableItem {
	protected EquipmentBoots(String name) {
		super(name);
	}

	protected EquipmentBoots(String name, String regex) {
		super(name, regex);
	}
	
	protected boolean doEquip(Entity entity) {
		entity.removeItem(this);
		EquippableItem old = entity.getEquipment().getBoots();
		if(old != null)
			old.unequip(entity);
		entity.getEquipment().setBoots(this);
		return entity.getEquipment().getBoots() == this;
	}

	protected boolean doUnequip(Entity entity) {
		if(entity.addItem(this))
			entity.getEquipment().setBoots(null);
		return entity.getEquipment().getBoots() != this;
	}
}
