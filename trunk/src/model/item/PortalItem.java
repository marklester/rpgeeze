package model.item;

import java.util.Hashtable;

import util.ResourceLoader;
import view.Drawer;
import view.Console;
import model.Location;
import model.entity.Entity;

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
		
		e.getTile().releaseEntity();
		ResourceLoader.getInstance().playAudioClip(this.name);
		if(where != null) {
			e.move(where);
			Console.getInstance().writeLine("A strange force sweeps you off your feet.");
		}
	}
	
	protected void setAttributesFromXml(String xml) {
		if(xml.length() != 0)
			this.where = Location.fromXml(xml);
	}
	
	public String toXml(String indent) {
		StringBuilder sb = new StringBuilder();
		sb.append(indent + "<item>\n");
		sb.append(indent + "\t<type>" + name + "</type>\n");
		sb.append(where == null ? "" : where.toXml("\t" + indent));
		sb.append(indent + "</item>");
		return sb.toString();
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
