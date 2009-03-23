package rpgeeze.model.item;

import rpgeeze.model.entity.Entity;

public class EquipmentArmor extends EquippableItem {
	protected EquipmentArmor(String name) {
		super(name);
	}

	protected EquipmentArmor(String name, String regex) {
		super(name, regex);
	}
	
	protected boolean doEquip(Entity entity) {
		entity.removeItem(this);
		EquippableItem old = entity.getEquipment().getArmor();
		if(old != null)
			old.unequip(entity);
		entity.getEquipment().setArmor(this);
		return entity.getEquipment().getArmor() == this;
	}

	protected boolean doUnequip(Entity entity) {
		if(entity.addItem(this))
			entity.getEquipment().setArmor(null);
		return entity.getEquipment().getArmor() != this;
	}
}
