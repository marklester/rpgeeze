package model.entity;

import model.item.Item;
import view.Drawer;

public class Merchant extends NPC implements Inventoryable{

	private Inventory inventory;
	
	public Merchant()
	{
		
	}
	
	@Override
	public void addItem(Item item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dropActionAtIndex(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Inventory getInventory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeItem(Item item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAlive() {
		return true;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Drawer d) {
		// TODO Auto-generated method stub		
	}

}
