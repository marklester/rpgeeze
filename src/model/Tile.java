package model;

public class Tile {

	private Location location;
	private Item item;
	private Terrain terrain;
	private AreaEffect ae;
	private Decal decal;

	public Tile(Location location) {
		this.location = location;
	}
	
	public int getX(){
		return location.getX();
	}
	
	public int getY(){
		return location.getY();
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
	
}

