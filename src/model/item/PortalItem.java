package model.item;

import util.ResourceLoader;
import view.Drawer;
import model.*;

public class PortalItem extends InteractiveItem {
	
	Location where;
	
	
	public PortalItem()
	{
		super("Portal");
	}
	
//	public PortalItem(Location loc, Location where) {
//		//super("Portal Item", loc);
//		this.where = where;
//	}
	
	
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
