package rpgeeze.model.item;

import rpgeeze.model.Tile;
import rpgeeze.model.entity.Entity;


/**
 * An Item that is added to an Entity's Inventory on touch.
 */

public abstract class TakeableItem extends Item {
	protected int price;
	
	public TakeableItem(String name) {
        super(name);
	}

	public final void activate(Entity entity, Tile tile) {
		if(entity.pickUp(this)) {
			tile.setItem(null);
		}
	}
	
	public abstract void use(Entity entity);
	
	public TakeableItem clone() {
		return (TakeableItem) super.clone();
	}
	
	public void setPrice(int price){
		this.price = price;
	}
	
	public int getPrice(){
		return price;
	}
}
