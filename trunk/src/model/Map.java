package model;

import java.io.*;
import java.util.*;

public class Map {
	public Tile[][] matrix;
	
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
	
	public Tile getTile(int x, int y) {
		Tile ret = null;
		if(y >= 0 && y < matrix.length && x >= 0 && x < matrix[y].length)
			ret = matrix[y][x];
		return ret;
	}
}

