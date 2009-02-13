package model;

import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import util.Iterator;

public class Map {
	private Tile[][] matrix;
	
	public Map(InputStream stream) {
		Scanner s = new Scanner(stream);
		List<Tile[]> list = new ArrayList<Tile[]>();
		for(int r = 0; s.hasNextLine(); ++r) {
			String line = s.nextLine();
			Tile[] arr = new Tile[line.length()];
			for(int c = 0; c != line.length(); ++c) {
				Terrain ter = null;
				switch(line.charAt(c)) {
				case 'G': ter = GrassTerrain.getInstance(); break;
				case 'M': ter = MountainTerrain.getInstance(); break;
				case 'W': ter = WaterTerrain.getInstance(); break;
				default: throw new RuntimeException("Bad map");
				}
				arr[c] = new Tile(ter, new Location(c, r));
			}
			list.add(arr);
		}
		matrix = list.toArray(new Tile[0][]);
	}
	
	public Iterator<Tile> getTiles() {
		return new Iterator<Tile>() {
			private int x;
			private int y;
			
			public void reset() {
				x = 0;
				y = 0;
			}
			
			public boolean isDone() {
				return y >= Map.this.matrix.length;
			}
			
			public Tile current() {
				return Map.this.matrix[x][y];
			}
			
			public void advance() {
				++x;
				if(x == Map.this.matrix[0].length) {
					++y;
					x = 0;
				}
			}
		};
	}
	
	public Tile getTile(int x, int y) {
		Tile ret = null;
		if(y >= 0 && y < matrix.length && x >= 0 && x < matrix[y].length)
			ret = matrix[y][x];
		return ret;
	}
}

