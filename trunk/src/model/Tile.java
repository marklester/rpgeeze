package model;

import model.items.Item;
import view.Drawer;

public class Tile implements Cloneable {

	private Location location;
	private Item item;
	private final Terrain terrain;
	private AreaEffect ae;
	private Decal decal;
	private Entity entity;

	public Tile(Terrain terrain, Location location, Decal decal, Item item, AreaEffect ae) {
		this.terrain = terrain;
		this.location = location;
		this.decal = decal;
		this.item = item;
		this.ae = ae;
	}

	public Tile(Terrain terrain, Location location, Item item) {
		this.terrain = terrain;
		this.location = location;
		this.item = item;
	}

	public Tile(Terrain terrain, Location location) {
		this.terrain = terrain;
		this.location = location;
	}

	public Location getLocation() {
		return this.location;
	}

	public Item getItem() {
		return this.item;
	}

	public boolean hasItem() {
		return this.item != null;
	}
	
	public boolean hasAE() {
		return this.ae != null;
	}

	// package level so that nobody outside Model can mess with this
	void setItem(Item item) {
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
			if( this.hasAE() )
				ae.applyEffect(e);
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
}
