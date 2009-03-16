package rpgeeze.model.map;

import rpgeeze.dp.Iterator;
import rpgeeze.model.Tile;

public abstract class Map {
	public abstract Tile getTile(int x, int y);
	
	public final Iterator<Tile> getTiles(final int minX, final int maxX, final int minY, final int maxY) {
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
