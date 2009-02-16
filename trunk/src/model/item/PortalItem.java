package model.item;

import util.ResourceLoader;
import view.Drawer;
import model.Entity;
import model.Location;

public class PortalItem extends InteractiveItem {
	private Location where;
	
	public PortalItem() {
		super("Portal Item");
		this.where = new Location(10, 10);
	}
	
	public void activate(Entity e) {
		use(e);
	}
	
	public void use(Entity e) {
		e.getTile().releaseEntity();
		ResourceLoader.getInstance().playAudioClip(this.name);
		e.move(where);
	}
	
	public void draw(Drawer d) {
		d.drawPortal(this);
	}
	
}
