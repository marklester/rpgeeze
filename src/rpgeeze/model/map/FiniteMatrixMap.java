package rpgeeze.model.map;

import rpgeeze.math.Vector;
import rpgeeze.model.Entity;
import rpgeeze.model.Tile;
import rpgeeze.model.terrain.GrassTerrain;
import rpgeeze.model.terrain.Terrain;
import rpgeeze.model.terrain.WaterTerrain;

public class FiniteMatrixMap implements Map {
	private TileImpl[][] matrix;
	private Entity avatar;
	
	private class TileImpl extends Tile {
		private final int x, y, z;
		
		public TileImpl(Terrain terrain, int x, int y, int z) {
			setTerrain(terrain);
			this.x = x;
			this.y = y;
			this.z = z;
		}

		public Tile getTile(Vector offset) {
			int dx = (int) Math.floor(offset.getX());
			int dy = (int) Math.floor(offset.getY());
			int dz = (int) Math.floor(offset.getZ());
			return FiniteMatrixMap.this.getTile(x + dx, y + dy, z + dz);
		}
	}
	
	public FiniteMatrixMap() {
		matrix = new TileImpl[3][3];
		for(int i = 0; i <= 2; ++i) {
			for(int j = 0; j <= 2; ++j) {
				matrix[i][j] = new TileImpl(GrassTerrain.getInstance(), i, j, 0);		
			}
		}
		avatar = new Entity();
		matrix[0][0].setEntity(avatar);
		avatar.setTile(matrix[0][0]);
	}
	
	protected Tile defaultTile(int x, int y, int z) {
		return new TileImpl(WaterTerrain.getInstance(), x, y, z);
	}
	
	private Tile getTile(int x, int y, int z) {
		Tile ret;
		if(y < 0 || y >= matrix.length || x < 0 || x >= matrix[0].length || z != 0)
			ret = defaultTile(x, y, z);
		else
			ret = matrix[x][y];
		return ret;
	}

	public Entity getAvatar() {
		return avatar;
	}
}
