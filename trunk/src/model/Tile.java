package model;

import view.*;

public class Tile {

	private Location location;
	private Item item;
	private Terrain terrain;
	private AreaEffect ae;
	private Decal decal;
	private Entity entity;

	public Tile(Terrain terrain, Location location) {
		this.terrain = terrain;
		this.location = location;
	}
	
	public Location getLocation(){
		return location;
	}
	
	public Item getItem() {
		return item;
	}
	
	// package level so that nobody outside Model can mess with this
	void setItem(Item item) {
		this.item = item;
	}
	
	public AreaEffect getAE() {
		return ae;
	}
	
	void setAreaEffect(AreaEffect ae) {
		this.ae = ae;
	}
	
	public Decal getDecal() {
		return decal;
	}
	
	void setDecal(Decal decal) {
		this.decal = decal;
	}
	
	public Terrain getTerrain() {
		return terrain;
	}
	
	public Entity getEntity() {
		return entity;
	}
	
	void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	public void draw(Drawer d) {
		terrain.draw(d);
		if(decal!= null) decal.draw(d);
		if(item != null) item.draw(d);
		if(entity != null) entity.draw(d);
	}
}

