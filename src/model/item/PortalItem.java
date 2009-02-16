package model.item;

import util.ResourceLoader;
import view.Drawer;
import model.*;

public class PortalItem extends InteractiveItem {
	
	Location where;
	
	
	public PortalItem() {
		super("Portal Item");
		this.where = new Location(10,26);
	}
	
	
	public void activate(Entity e) {
		use(e);
	}
	
	public void use(Entity e) {
		e.getTile().releaseEntity();
		ResourceLoader.getInstance().playAudioClip(this.name);
		e.moveAvatar(where);
		
	}
	
	public void draw(Drawer d) {
		d.drawPortal(this);
	}
	
}