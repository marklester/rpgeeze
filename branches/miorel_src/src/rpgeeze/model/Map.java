package rpgeeze.model;

import rpgeeze.dp.Iterator;
import rpgeeze.model.terrain.MountainTerrain;
import rpgeeze.model.terrain.WaterTerrain;

public class Map {
	private Tile[][] matrix;

	public Map(Tile[][] matrix) {
		this.matrix = matrix;
	}
	
	public Tile getTile(int x, int y) {
		Tile ret = null;
		if(y < 0 || y >= matrix.length || x < 0 || x >= matrix[0].length)
			ret = new Tile(WaterTerrain.getInstance(), new Location(x, y), null);
		else
			ret = matrix[y][x];
		return ret;
	}

	public Iterator<Tile> getTiles() {
		return getTiles(0, 0, matrix[0].length - 1, matrix.length - 1);
	}
	
	public Iterator<Tile> getTiles(final int minX, final int minY, final int maxX, final int maxY) {
		return new Iterator<Tile>() {
			private int x, y;
			
			public void advance() {
				if(++x > maxX) {
					++y;
					x = minX;
				}
			}

			public Tile current() {
				return getTile(x, y);
			}

			public boolean isDone() {
				return y > maxY;
			}

			public void reset() {
				x = minX;
				y = minY;
			}
		};
	}
	
	public void accept(Visitor visitor) {
		visitor.visitMap(this);
		Iterator<Tile> iter = getTiles();
		for(iter.reset(); !iter.isDone(); iter.advance())
			iter.current().accept(visitor);
	}
}
