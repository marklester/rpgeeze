package rpgeeze.model.entity;

/*
 * An inventory is a set of items carried by an entity. 
 * Items may be added to or dropped from the inventory 
 * and items in the inventory may be equipped.
 *  
 */

//import util.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import rpgeeze.dp.Iterator;
import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.model.item.Item;
import rpgeeze.util.ListIterator;



public class Inventory implements Cloneable {
	public static final int INV_MAX_SIZE = 100;

	private ArrayList<Item> items;

	public Inventory() {
		items = new ArrayList<Item>();
	}

	public void addItem(Item i, boolean announce) {
		if(items.size() < INV_MAX_SIZE) {
			items.add(i);
			if(announce)
				LogManager.getInstance().log("Picked up " + i.getName() + ".", "MODEL", Message.Type.GAME);
		}
		else {
			if(announce)
				LogManager.getInstance().log("Inventory full!", "MODEL", Message.Type.GAME);
		}		
	}
	
	public Item removeItemAt(int i) {
		return items.remove(i);
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

	public int count() {
		return items.size();
	}
	
	public synchronized Inventory clone() {
		Inventory clone = new Inventory();
		clone.items.addAll(items);
		return clone;
	}

	public Iterator<Item> iterator() {
		return new ListIterator<Item>(clone().items);
	}
}
