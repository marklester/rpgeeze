package rpgeeze.model.item;

import rpgeeze.model.Tile;
import rpgeeze.model.entity.Entity;

/**
 * An Item that is activated and removed from the Map when touched by an
 * Entity.
 */

public abstract class OneShotItem extends Item {
	protected int price;
	
	public OneShotItem(String name) {
		super(name);
	}

	public final void activate(Entity entity, Tile tile) {
		use(entity, tile);
		tile.setItem(null);
	}
	
	protected abstract void use(Entity entity, Tile tile);
	
	public OneShotItem clone() {
		return (OneShotItem) super.clone();
	}
	
	public void setPrice(int price){
		this.price = price;
	}
	
	public int getPrice(){
		return price;
	}
}


