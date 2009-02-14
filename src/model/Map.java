package model;

import java.io.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import util.*;

public class Map {
	public static final int NUM_OF_CHARS_REPRESENTING_A_TILE = 5;
	public Tile[][] matrix;
	
	public Map(InputStream stream) {
		Scanner s = new Scanner(stream);
		List<Tile[]> list = new ArrayList<Tile[]>();
		for(int r = 0; s.hasNextLine(); ++r) {
			String line = s.nextLine();
			Tile[] arr = new Tile[line.length() / NUM_OF_CHARS_REPRESENTING_A_TILE];
			
			for(int c = 0; c != arr.length; ++c) {
				Terrain ter = null;
				switch(line.charAt(NUM_OF_CHARS_REPRESENTING_A_TILE * c)) {
				case 'G': ter = GrassTerrain.getInstance(); break;
				case 'M': ter = MountainTerrain.getInstance(); break;
				case 'W': ter = WaterTerrain.getInstance(); break;
				default: throw new RuntimeException("Bad map - Terrain");
				}
				
				Decal dec = null;
				switch(line.charAt(NUM_OF_CHARS_REPRESENTING_A_TILE * c + 1)) {
				case '+': dec = RedCross.getInstance(); break;
				case '*': dec = GoldStar.getInstance(); break;
				case 'X': dec = SkullAndCrossbones.getInstance(); break;
				case ' ': break;
				default: throw new RuntimeException("Bad map - Decal");
				}
				
				Item item = null;
				switch(line.charAt(NUM_OF_CHARS_REPRESENTING_A_TILE * c + 2)) {
				case 'S': item = new Sword(); break;
				case 'B': item = new Boulder(); break;
				case ' ': break;
				//case 'L': item = new PotionLife(); break;
				//case 'm': item = new Mana(); break;
				//case 'C': item = new Crossbow(); break;
				//case 'A': item = new Arrows(); break;
				//case 't': item = new Staff(); break;
				}
				
				AreaEffect ae = null;
				switch(line.charAt(NUM_OF_CHARS_REPRESENTING_A_TILE * c + 3)) {
				case 'H': ae = new HealDamage(); break;
				case 'l': ae = new LevelUp() ; break;
				case 'd': ae = new TakeDamage(); break;
				case 'x': ae = new InstantDeath(); break;
				case ' ': break;
				default: throw new RuntimeException("Bad map - Area Effect");
				}
				
				arr[c] = new Tile(ter, new Location(c, r), dec, item, ae);
			}
			list.add(arr);
		}
		matrix = list.toArray(new Tile[0][]);
	}
	
	public Tile getTile(int x, int y) {
		Tile ret = null;
		if(y >= 0 && y < matrix.length && x >= 0 && x < matrix[y].length)
			ret = matrix[y][x];
		else
			ret = new Tile(MountainTerrain.getInstance(), new Location(x, y), null, null, null);
		return ret;
	}
	
	public Iterator<Tile> getTiles(final int minX, final int minY, final int maxX, final int maxY) {
    	return new Iterator<Tile>() {
    		private int x;
    		private int y;
                
    		private Tile[][] mapCopy = matrix.clone();
    		
    		public void reset() {
    			x = minX;
    			y = minY;
    		}
                
    		public boolean isDone() {
    			return y > maxY;
    		}
                
    		public Tile current() {
    			
    			//return mapCopy[x][y];
    			return Map.this.getTile(x, y);
    		}

    		public void advance() {
    			++x;
    			if(x > maxX) {
    				++y;
    				x = minX;
    			}
    		}
    	};
	}
	
    public Iterator<Tile> getTiles() {
    	return getTiles(0, 0, matrix[0].length - 1, matrix.length - 1);
    }
}