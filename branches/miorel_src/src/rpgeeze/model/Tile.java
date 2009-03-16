package rpgeeze.model;

import rpgeeze.model.terrain.Terrain;

public class Tile {
	private Terrain terrain;
	private int x;
	private int y;
	
	public Tile(Terrain terrain, int x, int y) {
		this.terrain = terrain;
		this.x = x;
		this.y = y;
	}
	
	public Terrain getTerrain() {
		return terrain;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
