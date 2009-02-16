package model;

/*
 * An inventory is a set of items carried by an entity. 
 * Items may be added to or dropped from the inventory 
 * and items in the inventory may be equipped.
 *  
 */

//import util.*;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.item.Item;
import view.Drawer;
import util.Iterator;

public class Inventory implements Cloneable {

	public static final int INV_SUCCESS = 0;
	public static final int INV_FULL = -1;
	public static final int INV_MAX_SIZE = 100;

	private List<Item> items;

	public Inventory() {
		this.items = new LinkedList<Item>();
	}

	public int addItem(Item i) {
		if(this.items.size() <= INV_MAX_SIZE) {
			this.items.add(i);
			return INV_SUCCESS;
		}
		return INV_FULL;
	}

	public Item removeItemAt(int i) {
		return this.items.remove(i);
	}
	public void removeItem(Item i)
	{
		items.remove(i);
	}

	// Should this return the Item object... It may need to be re-drawn on the
	// map once dropped.
	// Also, what should it receive? Controller will receive the "command" to
	// drop a certain
	// item in the inventory. This command is somehow sent to the Model, who
	// says, "Hey Entity
	// X, go ahead and drop item i from your inventory"... I think we're going
	// to need a way
	// for the Controller to tell the Entity WHICH SPECIFIC item in the
	// inventory needs to
	// be dropped
	public boolean isEmpty() {
		return this.items.isEmpty();
	}

	public int count()
	{
		return items.size();
	}
	
	public synchronized Inventory clone() {
		Inventory clone = new Inventory();
		for(Item i: items) clone.addItem(i.clone());
		return clone;
	}

	public Iterator<Item> iterator() {
		final List<Item> list;
		synchronized(this) {
			list = this.clone().items;
		}
		return new Iterator<Item>() {
			private int cursor = 0;
			
			public boolean isDone() {
				return cursor >= list.size();
			}
			
			public Item current() {
				return list.get(cursor);
			}
			
			public void reset() {
				cursor = 0;
			}
			
			public void advance() {
				++cursor;
			}
		};
	}
}
