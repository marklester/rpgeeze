package model;

/*
 * An inventory is a set of items carried by an entity. 
 * Items may be added to or dropped from the inventory 
 * and items in the inventory may be equipped.
 *  
 */
 
import java.util.*;

import model.items.Item;

public class Inventory implements Cloneable{
	
	public static final int INV_SUCCESS = 0;
	public static final int INV_FULL = -1;
	public static final int INV_MAX_SIZE = 6;
	
	private ArrayList<Item> items;
	
	
	public Inventory() {
		items = new ArrayList<Item>(INV_MAX_SIZE);
	}
	
	public int addItem(Item i) {
		if (items.size() < INV_MAX_SIZE) {
			items.add(i);
			return INV_SUCCESS;
		}
		return INV_FULL;
	}
	
	public Item removeItemAt(int i) {
		return items.remove(i);
	}
	
	//Should this return the Item object... It may need to be re-drawn on the map once dropped.
	//Also, what should it receive? Controller will receive the "command" to drop a certain 
	//item in the inventory. This command is somehow sent to the Model, who says, "Hey Entity 
	//X, go ahead and drop item i from your inventory"... I think we're going to need a way 
	//for the Controller to tell the Entity WHICH SPECIFIC item in the inventory needs to 
	//be dropped
	public boolean isEmtpy() {
		return items.isEmpty();		
	}
	
	public Inventory clone() throws CloneNotSupportedException 
	{
		Inventory i = (Inventory)super.clone();
        if(items != null)
        {        
        	i.items = (ArrayList<Item>)this.items.clone();
        	Iterator<Item> iter = i.items.iterator();
        	//System.out.println(Thread.currentThread() + " is cloning inventory.");
        	while(iter.hasNext())
        	{
        		i.addItem(iter.next().clone());
        	}        	        
		}
        return i;
	}
	
	public Iterator<Item> getItems() {
		return items.iterator();
	}
}

