package rpgeeze.model.entity;

/*
 * An inventory is a set of items carried by an entity. 
 * Items may be added to or dropped from the inventory 
 * and items in the inventory may be equipped.
 *  
 */

import java.util.ArrayList;

import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.model.item.Item;
import rpgeeze.util.ArrayIterator;
import rpgeeze.dp.Iterator;

public class Inventory {
	public static final int INV_MAX_SIZE = 100;

	private ArrayList<Item> items;

	public Inventory() {
		items = new ArrayList<Item>();
	}

	public boolean addItem(Item i, boolean announce) {
		LogManager lm = LogManager.getInstance();
		boolean ret;
		if(items.size() < INV_MAX_SIZE) {
			items.add(i);
			if(announce)
				lm.log(i.getName() + " has been added to your inventory.", "INVENTORY", Message.Type.GAME);
			ret = true;
		}
		else {
			lm.log("Inventory full!", "INVENTORY", Message.Type.GAME);
			ret = false;
		}
		return ret;
	}

	public Item removeItemAt(int i) {
		return this.items.remove(i);
	}

	public Item getItemAt(int i) {
		return items.get(i);
	}

	public void removeItem(Item i) {
		items.remove(i);
	}

	public boolean isEmpty() {
		return items.isEmpty();
	}

	public int itemCount() {
		return items.size();
	}

	public Iterator<Item> iterator() {
		return new ArrayIterator<Item>(items.toArray(new Item[0]));
	}
}
