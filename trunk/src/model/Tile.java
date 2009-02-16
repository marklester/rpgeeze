package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.item.Item;
import model.decal.Decal;
import model.ae.AreaEffect;
import view.Drawer;

public class Tile implements Cloneable {
	private static final Pattern pattern = Pattern.compile("<tile>(<terrain>.*</terrain>)(<location>.*</location>)(<decal>.*</decal>|)(<item>.*</item>|)(<ae>.*</ae>|)</tile>");
	
	private final Terrain terrain;
	private Location location;
	private Decal decal;
	private Item item;
	private AreaEffect ae;
	private Entity entity;

	public Tile(Terrain terrain, Location location, Decal decal, Item item, AreaEffect ae) {
		this.terrain = terrain;
		this.location = location;
		this.decal = decal;
		this.item = item;
		this.ae = ae;
	}

	public Tile(Terrain terrain, Location location, Item item) {
		this(terrain, location, null, item, null);
	}
	
	public Tile(Terrain terrain, Location location) {
		this(terrain, location, null, null, null);
	}

	public Location getLocation() {
		return this.location;
	}

	public Item getItem() {
		return this.item;
	}

	public boolean hasItem() {
		return getItem() != null;
	}
	
	public boolean hasAE() {
		return getAE() != null;
	}

	// package level so that nobody outside Model can mess with this
	void setItem(Item item) {
		if(item != null) item.setLocation(getLocation());
		this.item = item;
	}

	public AreaEffect getAE() {
		return this.ae;
	}

	void setAreaEffect(AreaEffect ae) {
		this.ae = ae;
	}

	public Decal getDecal() {
		return this.decal;
	}

	void setDecal(Decal decal) {
		this.decal = decal;
	}

	public Terrain getTerrain() {
		return this.terrain;
	}

	public Entity getEntity() {
		return this.entity;
	}

	public boolean hasEntity() {
		return this.entity != null;
	}

	void setEntity(Entity entity) {
		this.entity = entity;
	}

	public void draw(Drawer d) {
		this.terrain.draw(d);
		if(this.decal != null)
			this.decal.draw(d);
		if(this.item != null)
			this.item.draw(d);
		if(this.entity != null)
			this.entity.draw(d);
	}

	public void accept(Entity e) {
		if(getTerrain().isPassable(e)) {
			if(hasItem() && getItem().isPassable() || !hasItem()) {
				e.getTile().setEntity(null);
				setEntity(e);
				e.setTile(this);
			}
		}
	}

	public void releaseEntity() {
		this.entity = null;
	}

	public Tile clone() throws CloneNotSupportedException {
		
		Tile t = (Tile) super.clone();
		
		if(t.item != null)
			t.item = this.item.clone();
		if(t.ae != null)
			t.ae = this.ae.clone();
		if(t.decal != null)
			t.decal = this.decal.clone();
		if(t.entity != null)
			t.entity = this.entity.clone();
		return t;

	}

	public String toString() {
		return "Tile at " + this.location;
	}
	
	public String toXml() {
		return toXml("");
	}
	
	public String toXml(String indent) {
		StringBuilder sb = new StringBuilder();		
		sb.append(indent + "<tile>\n");
		sb.append(terrain.toXml(indent + "\t") + "\n");
		sb.append(location.toXml(indent + "\t") + "\n");
		sb.append(decal == null ? "" : (decal.toXml(indent + "\t") + "\n"));
		sb.append(item == null ? "" : (item.toXml(indent + "\t") + "\n"));
		sb.append(ae == null ? "" : (ae.toXml(indent + "\t") + "\n"));
		sb.append(indent + "</tile>");
		return sb.toString();
	}
	
	public static Tile fromXml(String xml) {
		Matcher mat = pattern.matcher(xml);
		if(!mat.matches())
			throw new RuntimeException("Bad XML for Tile");
		Terrain terrain = Terrain.fromXml(mat.group(1));
		Location location = Location.fromXml(mat.group(2));
		Decal decal = mat.group(3).length() == 0 ? null : Decal.fromXml(mat.group(3));
		Item item = mat.group(4).length() == 0 ? null : Item.fromXml(mat.group(4));
		AreaEffect ae = mat.group(5).length() == 0 ? null : AreaEffect.fromXml(mat.group(5));
		return new Tile(terrain, location, decal, item, ae);
	}
}
