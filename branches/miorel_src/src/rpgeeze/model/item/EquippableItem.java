package rpgeeze.model.item;

import rpgeeze.model.entity.Entity;

public abstract class EquippableItem extends TakeableItem {
	private String regex;
	
	protected EquippableItem(String name) {
		this(name, ".*");
	}
	
	protected EquippableItem(String name, String regex) {
		super(name);
		this.regex = regex;
	}

	public final boolean equip(Entity entity) {
		boolean ret = false;
		if(entity.getOccupation().getName().matches(regex))
			ret = doEquip(entity);
		return ret;
	}
	
	protected abstract boolean doEquip(Entity entity);
	protected abstract boolean doUnequip(Entity entity);
	
	public final boolean unequip(Entity entity) {
		boolean ret = false;
		if(entity.getOccupation().getName().matches(regex))
			ret = doUnequip(entity);
		return ret;
	}
	
	public EquippableItem clone() {
		return (EquippableItem) super.clone();
	}
	
	public final void use(Entity entity) {
		equip(entity);
	}
}
