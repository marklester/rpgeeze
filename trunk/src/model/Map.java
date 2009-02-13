package model;

import java.io.*;
import java.util.*;

public class Map {
	private Tile[][] matrix;
	
	public Map(InputStream stream) {
		Scanner s = new Scanner(stream);
		List<Tile[]> list = new ArrayList<Tile[]>();
		while(s.hasNextLine()) {
			String line = s.nextLine();
			Tile[] arr = new Tile[line.length()];
			// parse the line and set Tile in arr
			list.add(0, arr);
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

