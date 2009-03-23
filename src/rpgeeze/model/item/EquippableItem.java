package rpgeeze.model.item;

import rpgeeze.model.entity.Entity;

public interface EquippableItem extends Cloneable {
	
	public void equip(Entity entity);
	
	public void unequip(Entity entity);
	
	public EquippableItem clone();
}
