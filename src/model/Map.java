package model;

import java.io.*;
import java.util.*;

public class Map {
	public static final int NUM_OF_CHARS_REPRESENTING_A_TILE = 5;
	public Tile[][] matrix;
	
	public Map(InputStream stream) {
		Scanner s = new Scanner(stream);
		List<Tile[]> list = new ArrayList<Tile[]>();
		for(int r = 0; s.hasNextLine(); ++r) {
			String line = s.nextLine();
			Tile[] arr = new Tile[line.length()/NUM_OF_CHARS_REPRESENTING_A_TILE];
			
			for(int c = 0; c != line.length(); ++c) {
				Terrain ter = null;
				switch(line.charAt(c)) {
				case 'G': ter = GrassTerrain.getInstance(); break;
				case 'M': ter = MountainTerrain.getInstance(); break;
				case 'W': ter = WaterTerrain.getInstance(); break;
				default: throw new RuntimeException("Bad map - Terrain");
				}
				
				Decal dec = null;
				switch(line.charAt(++c)) {
				case '+': dec = RedCross.getInstance(); break;
				case '*': dec = GoldStar.getInstance(); break;
				case 'X': dec = SkullAndCrossbones.getInstance(); break;
				}
				
				Item item = null;
				switch(line.charAt(++c)) {
				case 'S': item = new Sword(); break;
				case 'B': item = new Bolder(); break;
				//case 'L': item = new PotionLife(); break;
				//case 'm': item = new Mana(); break;
				//case 'C': item = new Crossbow(); break;
				//case 'A': item = new Arrows(); break;
				//case 't': item = new Staff(); break;
				}
				
				AreaEffect ae = null;
				switch(line.charAt(++c)) {
				case 'H': ae = new HealDamage(); break;
				case 'l': ae = new LevelUp() ; break;
				case 'd': ae = new TakeDamage(); break;
				case 'x': ae = new InstantDeath(); break;
				}
				
				arr[c] = new Tile(ter, new Location(c, r), item, ae);
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
