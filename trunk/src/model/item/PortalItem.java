package model.item;

import util.ResourceLoader;
import view.Drawer;
import view.Console;
import model.Entity;
import model.Location;

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
}
