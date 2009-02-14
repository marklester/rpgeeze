package model;

import model.items.Item;
import model.items.Obstacle;
import view.*;

public class Tile implements Cloneable{

	private Location location;
	private Item item;
	private Terrain terrain;
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
	
	public void accept(Entity e) {
		if(getTerrain().isPassable(e) && !(getItem() instanceof Obstacle)) {
			System.out.println("moved from tile: " + e.getTile());
			e.getTile().setEntity(null);
			System.out.println(this.entity == null);
			this.setEntity(e);
			e.setTile(this);
			System.out.println("moved to tile: " + e.getTile());
		}
	}
	public void releaseEntity()
	{
		this.entity = null;
	}
	
	public Object clone() throws CloneNotSupportedException
	{
		
		Tile t = (Tile)super.clone();
		if(t.location != null) t.location = (Location)this.location.clone();
		if(t.item != null) t.item = (Item)this.item.clone();
		//t.terrain = (Terrain)this.terrain.clone();
		if(t.ae != null) t.ae = (AreaEffect)this.ae.clone();
		if(t.decal != null) t.decal = (Decal)this.decal.clone();
		if(t.entity != null) t.entity = (Entity)this.entity.clone();
		return t;
		
	}
	
	public String toString() {
		return "Tile at " + location;
	}
}

