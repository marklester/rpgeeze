package model;

/*
 * An inventory is a set of items carried by an entity. 
 * Items may be added to or dropped from the inventory 
 * and items in the inventory may be equipped.
 *  
 */
 
//import util.*;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import model.items.Item;
import view.*;

public class Inventory implements Cloneable {
	
	public static final int INV_SUCCESS = 0;
	public static final int INV_FULL = -1;
	public static final int INV_MAX_SIZE = 6;
	
	private boolean visible = true;
	
	private List<Item> items;
	
	public Inventory() {
		items = new ArrayList<Item>(INV_MAX_SIZE);
	}
	
	public int addItem(Item i) {
		if(items.size() < INV_MAX_SIZE) {
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
	public boolean isEmpty() {
		return items.isEmpty();		
	}
	public boolean isVisible()
	{
		return visible;
	}
	public void setVisible(boolean b)
	{
		visible = b;
	}
	
	
	public Inventory clone() {
		//shallow copy is already created from clone() on arraylist
		Inventory clone = new Inventory();
		clone.items = (List<Item>)((ArrayList<Item>)(this.items)).clone();
        //for(Item i: items) clone.addItem(i);
        return clone;
	}
	
	public List<Item> getItems() {
		return items;
	}
	public Iterator<Item> iterator()
	{
		return items.iterator();
	}
	
	public boolean isOnInventory(int x, int y)
	{
		return Drawer.getInstance().isOnInventory(x, y);
	}
	public void leftClick(int x, int y)
	{
		Drawer.getInstance().clickInventory(x, y);
	}
	public void rightClick(int x, int y)
	{
		Drawer.getInstance().clickInventory(x, y);
	}
}

