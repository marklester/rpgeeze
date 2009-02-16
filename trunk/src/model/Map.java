package model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.Iterator;

public class Map {
	protected static final Pattern mapPattern = Pattern.compile("<map>(.*?)</map>");
	protected static final Pattern tilePattern = Pattern.compile("(<tile>.*?</tile>)");
	
	private final Matrix matrix;

	public class Matrix implements Cloneable {
		private volatile Tile[][] map;

		public Matrix(Tile[][] map) {
			this.map = map;
		}

		public Tile getTile(int x, int y) {
			return getTile(x, y, this.map);
		}
		
		public Tile getTile(Location l) {
			return getTile(l.getX(), l.getY(), this.map);
		}

		private Tile getTile(int x, int y, Tile[][] matrix) {
			Tile ret = null;
			if(y >= 0 && y < matrix.length && x >= 0 && x < matrix[y].length)
				ret = matrix[y][x];
			else
				ret = new Tile(WaterTerrain.getInstance(), new Location(x, y), null, null, null);
			return ret;
		}

		public Iterator<Tile> getTiles(final int minX, final int minY, final int maxX, final int maxY) {
			return new Iterator<Tile>() {
				private int x;
				private int y;

				public void reset() {
					this.x = minX;
					this.y = minY;
				}

				public boolean isDone() {
					return this.y > maxY;
				}

				public Tile current() {
					return getTile(this.x, this.y, Matrix.this.map);
				}

				public void advance() {
					++this.x;
					if(this.x > maxX) {
						++this.y;
						this.x = minX;
					}
				}
			};
		}

		public Iterator<Tile> getTiles() {
			return getTiles(0, 0, this.map[0].length - 1, this.map.length - 1);
		}

		public Matrix clone() throws CloneNotSupportedException {
			Matrix m = (Matrix) super.clone();
			m.map = this.map.clone();
			for(int i = 0; i < m.map.length; i++)
				for(int j = 0; j < m.map[i].length; j++)
					if(this.map[i][j] != null)
						try {
							m.map[i][j] = this.map[i][j].clone();
						}
						catch(CloneNotSupportedException e) {
							System.err.println("This should never occur.");
						}
					else
						System.out.println("No Good.");
			return m;
		}

	}

	private Map(Tile[][] matrix) {
		this.matrix = new Matrix(matrix);
	}
	
	public static Map fromStream(InputStream stream) {
		Scanner scan = new Scanner(stream);
		StringBuilder sb = new StringBuilder();
		while(scan.hasNextLine())
			sb.append(scan.nextLine());
		return fromXml(sb.toString());
	}
	
	public Tile getTile(int x, int y) {
		return this.matrix.getTile(x, y);
	}
	
	public Tile getTile(Location l) {
		return this.matrix.getTile(l);
	}

	public Matrix getMatrix() {
		// Matrix m = null;
		// try{
		// m = (Matrix)matrix.clone();
		// }catch (CloneNotSupportedException e){}
		return this.matrix;
	}

	// Not entirely thread safe, but better than before.
	public Iterator<Tile> getTiles(final int minX, final int minY, final int maxX, final int maxY) {
		Matrix m = null;
		try {
			m = this.matrix.clone();
		}
		catch(CloneNotSupportedException e) {/* Should never occur */
		}
		return m.getTiles(minX, minY, maxX, maxY);
		// return matrix.getTiles(minX, minY, maxX, maxY);
	}

	public Iterator<Tile> getTiles() {
		// Matrix m = null;
		// try
		// {
		// m = (Matrix)matrix.clone();
		// }catch (CloneNotSupportedException e)
		// {/* Should never occur*/}
		// return matrix.getTiles();
		return this.matrix.getTiles();
	}
	
	public String toXml() {
		return toXml("");
	}
	
	public String toXml(String indent) {
		StringBuilder sb = new StringBuilder();
		sb.append(indent + "<map>\n");
		Iterator<Tile> iter = getTiles();
		String innerIndent = indent + "\t";
		for(iter.reset(); !iter.isDone(); iter.advance()) {
			sb.append(iter.current().toXml(innerIndent));
			sb.append("\n");
		}
		sb.append(indent + "</map>");
		return sb.toString();
	}
	
	public static Map fromXml(String xml) {
		xml = xml.replaceAll("[\\t\\n]", "");
		Matcher mapMatcher = mapPattern.matcher(xml);
		if(!mapMatcher.matches())
			throw new RuntimeException("Bad XML for Map");
		Matcher tileMatcher = tilePattern.matcher(mapMatcher.group(1));		
		List<Tile> tiles = new ArrayList<Tile>();
		int maxX = 0;
		int maxY = 0;
		while(tileMatcher.find()) {
			Tile tile = Tile.fromXml(tileMatcher.group());
			tiles.add(tile);
			maxX = Math.max(maxX, tile.getLocation().getX());
			maxY = Math.max(maxY, tile.getLocation().getY());
		}
		Tile[][] matrix = new Tile[maxY + 1][maxX + 1];
		for(Tile tile: tiles) {
			Location loc = tile.getLocation();
			matrix[loc.getY()][loc.getX()] = tile;
		}
		System.out.println(new Map(matrix).toXml());
		return new Map(matrix);
	}
}
