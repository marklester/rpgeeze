package rpgeeze.model.item;

import rpgeeze.model.entity.Entity;

public class EquipmentHead extends EquippableItem {
	protected EquipmentHead(String name) {
		super(name);
	}

	protected EquipmentHead(String name, String regex) {
		super(name, regex);
	}
	
	protected boolean doEquip(Entity entity) {
		entity.removeItem(this);
		EquippableItem old = entity.getEquipment().getHead();
		if(old != null)
			old.unequip(entity);
		entity.getEquipment().setHead(this);
		return entity.getEquipment().getHead() == this;
	}

	protected boolean doUnequip(Entity entity) {
		if(entity.addItem(this))
			entity.getEquipment().setHead(null);
		return entity.getEquipment().getHead() != this;
	}
}
