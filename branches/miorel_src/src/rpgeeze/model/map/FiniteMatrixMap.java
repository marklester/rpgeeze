package rpgeeze.model.map;

import rpgeeze.model.Entity;
import rpgeeze.model.Tile;
import rpgeeze.model.terrain.GrassTerrain;
import rpgeeze.model.terrain.WaterTerrain;

public class FiniteMatrixMap extends Map {
	private Tile[][] matrix;
	private int xOffset;
	private int yOffset;
	
	public FiniteMatrixMap() {
		matrix = new Tile[3][3];
		xOffset = -1;
		yOffset = -1;
		for(int i = 0; i <= 2; ++i) {
			for(int j = 0; j <= 2; ++j) {
				matrix[i][j] = new Tile(GrassTerrain.getInstance(), i + xOffset, j + yOffset);		
			}
		}
		matrix[0][0].setEntity(new Entity());
	}
	
	public Tile defaultTile(int x, int y) {
		return new Tile(WaterTerrain.getInstance(), x, y);
	}
	
	public Tile getTile(int x, int y) {
		int rx = x - xOffset;
		int ry = y - yOffset;
		Tile ret;
		if(ry < 0 || ry >= matrix.length || rx < 0 || rx >= matrix[0].length)
			ret = defaultTile(x, y);
		else
			ret = matrix[ry][rx];
		return ret;
	}
}
