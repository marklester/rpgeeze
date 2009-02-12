package model;

public class Tile {

	private Location location;
	private Item item;
	private Terrain terrain;
	private AreaEffect ae;
	private Decal decal;
	
	
	public int getX(){
		return location.getX();
	}
	
	public int getY(){
		return location.getY();
	}
	
	public Item getItem() {
		return item;
	}
	
	public AreaEffect getAE() {
		return ae;
	}
	
	public Decal getDecal() {
		return decal;
	}
	
	public Terrain getTerrain() {
		return terrain;
	}
	
}

