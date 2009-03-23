package rpgeeze.model.item;

import java.util.HashMap;

import rpgeeze.model.Location;
import rpgeeze.model.Tile;
import rpgeeze.model.entity.Entity;
import rpgeeze.model.entity.PC;

//import java.util.Hashtable;

public class Portal extends InteractiveItem {
	private Location where;
	
	public Portal() {
		this(null);
	}
	
	public Portal(Location loc) {
		super("Portal Item");
		this.where = loc;
	}
	
	public void activate(PC pc) {
		use(pc);
	}
	

	public void use(PC pc) {
		pc.getTile().releaseEntity();		
		//AudioThread.getInstance().run(this.name, AudioThread.CLIP);
		if(where != null) {
			pc.move(where);
			Console.getInstance().writeLine("A strange force sweeps you off your feet.");
		}
	}
	
	protected void setAttributesFromXml(String xml) {
		if(xml.length() != 0)
			this.where = Location.fromXml(xml);
	}
	
	public void setProperties(HashMap<String, String> properties) {
		//Use Properties in Hash Map here
		super.setProperties(properties);//makes sure save can save properties
		System.out.println("Portal"+properties.get("location"));
		String[] loc = properties.get("location").split(",");
		int x = Integer.parseInt(loc[0]);
		int y = Integer.parseInt(loc[1]);
		where = new Location(x,y);
	}

	public void activate(Entity entity, Tile tile) {
		
	}

	public void use(PC pc) {
		// TODO Auto-generated method stub
		
	}
}
