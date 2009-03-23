package rpgeeze.model.item;

import rpgeeze.model.entity.Entity;

public class EquipmentAuxiliary extends EquippableItem {
	protected EquipmentAuxiliary(String name) {
		super(name);
	}

	protected EquipmentAuxiliary(String name, String regex) {
		super(name, regex);
	}
	
	protected boolean doEquip(Entity entity) {
		entity.removeItem(this);
		EquippableItem old = entity.getEquipment().getAuxiliary();
		if(old != null)
			old.unequip(entity);
		entity.getEquipment().setAuxiliary(this);
		return entity.getEquipment().getAuxiliary() == this;
	}

	protected boolean doUnequip(Entity entity) {
		if(entity.addItem(this))
			entity.getEquipment().setAuxiliary(null);
		return entity.getEquipment().getAuxiliary() != this;
	}
}
