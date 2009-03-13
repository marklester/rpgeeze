package model;

import view.Drawer;

public class GrassTerrain extends Terrain {
	private static GrassTerrain instance = null;

	private GrassTerrain() {
		super("Grass Terrain");
	}

	public static GrassTerrain getInstance() {
		if(instance == null)
			instance = new GrassTerrain();
		return instance;
	}

}
