package rpgeeze.model.item;

import rpgeeze.model.Entity;
import rpgeeze.view.Drawable;

public interface Item extends Drawable {
	public void activate(Entity entity);
}
