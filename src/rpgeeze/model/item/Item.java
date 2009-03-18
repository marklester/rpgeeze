package rpgeeze.model.item;

import rpgeeze.model.Entity;
import rpgeeze.view.Drawable;

public abstract class Item implements Drawable {
	public abstract void activate(Entity entity);
}
