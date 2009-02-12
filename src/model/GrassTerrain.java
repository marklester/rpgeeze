package model;

import view.Drawer;

public class GrassTerrain extends Terrain {

	public GrassTerrain() {
		super("Grass Terrain");
	}
	
	public void draw(Drawer d) {
		d.drawGrassTerrain(this);
	}
}

