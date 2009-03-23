package rpgeeze.model.entity;

import model.item.*;

public interface Inventoryable {
	
	public Inventory getInventory();
	
	public void addItem(Item item);
	
	public void removeItem(Item item);
	
	public void dropActionAtIndex(int index);
}