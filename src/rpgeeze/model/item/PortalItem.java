package rpgeeze.model.item;

import java.util.Hashtable;

import rpgeeze.model.Entity;
import rpgeeze.model.Location;

public class PortalItem extends InteractiveItem {
	private Location where;
	
	public PortalItem() {
		this(null);
	}
	public PortalItem(Location loc) {
		super("Portal Item");
		this.where = loc;
	}
	
	public void activate(Entity e) {
		use(e);
	}
	
	public void use(Entity e) {
		
		//e.getTile().releaseEntity();
		//ResourceLoader.getInstance().playAudioClip(this.name);
		/*
		if(where != null) {
			e.move(where);
			Console.getInstance().writeLine("A strange force sweeps you off your feet.");
		}
		*/
	}
	
	@Override
	public void setProperties(Hashtable<String, String> properties) {
		//Use Properties in Hash Map here
		super.setProperties(properties);//makes sure save can save properties
		System.out.println("Portal"+properties.get("location"));
		String[] loc = properties.get("location").split(",");
		int x = Integer.parseInt(loc[0]);
		int y = Integer.parseInt(loc[1]);
		where = new Location(x,y);
	}
}