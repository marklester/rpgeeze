package rpgeeze.model;

import rpgeeze.model.Map;
import rpgeeze.model.Tile;
import rpgeeze.model.entity.Entity;
import rpgeeze.model.entity.IllegalMoveException;
import rpgeeze.model.entity.PC;
import rpgeeze.model.Visitor;
import rpgeeze.model.Location;
import rpgeeze.model.ae.AreaEffect;
import rpgeeze.model.decal.Decal;
import rpgeeze.model.item.Item;
import rpgeeze.model.terrain.Terrain;

public class Tile implements Cloneable, Visitable {
	private Terrain terrain;
	private Entity entity;
	private Item item;
	private AreaEffect ae;
	private Decal decal;
	private Location location;
	private Map map;
	
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
	
	public void setMap(Map map) {
		this.map = map;
	}

	public Location getLocation() {
		return this.location;
	}
	
	public int getX() {
		return location.getX();
	}

	public int getY() {
		return location.getY();
	}
	
	public Terrain getTerrain() {
		return terrain;
	}
	
	public void setTerrain(Terrain newTerrain) {
		this.terrain = newTerrain;
	}
	
	public Entity getEntity() {
		return entity;
	}
	
	public void setEntity(Entity entity) {
		if(this.entity != null && entity != null) {
			throw new IllegalMoveException("Destination tile already occupied");
		}
		else if(!this.terrain.isPassable(entity)) {
			throw new IllegalMoveException("Entity may not traverse destination terrain");
		}
		if(item != null)
			item.activate(entity, this);
		this.entity = entity;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item newItem) {
		item = newItem;
	}
	
	public boolean hasItem() {
		return getItem() != null;
	}

	public boolean hasAE() {
		return getAE() != null;
	}
	
	public AreaEffect getAE() {
		return ae;
	}

	public void setAreaEffect(AreaEffect newAreaEffect) {
		ae = newAreaEffect;
	}

	public void setDecal(Decal newDecal) {
		decal = newDecal;
	}
	
	public void accept(Visitor visitor) {
		visitor.visitTile(this);
		if(terrain != null)
			terrain.accept(visitor);
		if(ae != null)
			ae.accept(visitor);
		if(decal != null)
			decal.accept(visitor);
		if(item != null)
			item.accept(visitor);
		if(entity != null)
			entity.accept(visitor);
	}
	
	public Tile getAbsoluteTile(Location l) {
		return map.getTile(l.getX(), l.getY());
	}
	
	public Tile getAbsoluteTile(int x, int y)	{
		return map.getTile(x, y);
	}
	
	public Tile getRelativeTile(Location l) {
		return getRelativeTile(l.getX(), l.getY());
	}
	
	public Tile getRelativeTile(int x, int y) {
		return map.getTile(location.getX() + x, location.getY() + y);
	}
	
	public String toString() {
		return "Tile at " + this.location+"["+this.terrain+","+this.decal
		+","+this.item+","+this.ae+"]";
	}

	public Tile clone() {
		Tile tile = new Tile(
			terrain,
			location,
			decal == null ? null: decal.clone(),
			item == null ? null : item.clone(),
			ae == null ? null : ae.clone()
		);
		tile.entity = (entity == null ? null : entity.clone());
		return tile;
	}
}
