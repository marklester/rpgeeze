package rpgeeze.model;

import rpgeeze.model.terrain.GrassTerrain;
import rpgeeze.model.terrain.WaterTerrain;
import rpgeeze.dp.Iterator;
 
public class Map {
	private Tile[][] matrix;
	private int xOffset;
	private int yOffset;
	
	public Map() {
		matrix = new Tile[1][1];
		xOffset = 0;
		yOffset = 0;
		matrix[0][0] = new Tile(GrassTerrain.getInstance(), 0, 0);
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
	
	public Iterator<Tile> getTiles(final int minX, final int maxX, final int minY, final int maxY) {
		return new Iterator<Tile>() {
			private int curX;
			private int curY;
			
			public void advance() {
				++curX;
				if(curX > maxX) {
					curX = minX;
					++curY;
				}
			}

			public Tile current() {
				return getTile(curX, curY);
			}

			public boolean isDone() {
				return curY > maxY;
			}

			public void reset() {
				curX = minX;
				curY = minY;
			}
		};
	}
}
