package rpgeeze.model.entity;

/**
 * An inventory is a set of items carried by an entity. 
 * Items may be added to or dropped from the inventory 
 * and items in the inventory may be equipped.
 *  
 */

import java.util.ArrayList;

import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.model.item.Item;
import rpgeeze.model.item.TakeableItem;
import rpgeeze.util.ArrayIterator;
import rpgeeze.dp.Iterator;

public class Inventory implements Cloneable{
	public static final int INV_MAX_SIZE = 25;

	private final int maxSize;
	private ArrayList<TakeableItem> items;

	public Inventory() {
		this(INV_MAX_SIZE);
	}
	
	public Inventory(int maxSize) {
		items = new ArrayList<TakeableItem>();
		this.maxSize = maxSize;
	}

	public boolean addItem(TakeableItem item, boolean announce) {
		LogManager lm = LogManager.getInstance();
		boolean ret;
		if(items.size() < maxSize) {
			items.add(item);
			if(announce)
				lm.log(item.getName() + " has been added to your inventory.", "INVENTORY", Message.Type.GAME);
			ret = true;
		}
		else {
			lm.log("Inventory full!", "INVENTORY", Message.Type.GAME);
			ret = false;
		}
		return ret;
	}

	public Item removeItemAt(int i) {
		return items.remove(i);
	}

	public TakeableItem getItemAt(int i) {
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

	public Iterator<TakeableItem> iterator() {
		return new ArrayIterator<TakeableItem>(items.toArray(new TakeableItem[0]));
	}
	
	public Inventory clone()
	{
		Inventory i = null;
		try {
			i = (Inventory)super.clone();
		}catch(CloneNotSupportedException ce) {}		
		i.items = (ArrayList<TakeableItem>)items.clone();
		return i;
	}
}