package model;

import java.io.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import model.items.Boulder;
import model.items.Item;
import model.items.PotionLife;
import model.items.Sword;

import util.*;

public class Map {
	public static final int NUM_OF_CHARS_REPRESENTING_A_TILE = 5;
	
	private volatile Matrix matrix;
	
	public class Matrix implements Cloneable
	{
		private volatile Tile[][] map;
		
		public Matrix(Tile[][] map)
		{
			this.map = map;
		}
		
		public Tile getTile(int x, int y) {
			return getTile(x, y, this.map);
		}
		private Tile getTile(int x, int y, Tile[][] matrix)
		{
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
	    		
	    		public void reset() {
	    			x = minX;
	    			y = minY;
	    		}
	                
	    		public boolean isDone() {
	    			return y > maxY;
	    		}
	                
	    		public Tile current() {
	    			return getTile(x, y, map);
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
	    	return getTiles(0, 0, map[0].length - 1, map.length - 1);
	    }	   
	
	    public Object clone() throws CloneNotSupportedException
	    {
	    	Matrix m = (Matrix)super.clone();
	    	m.map = this.map.clone();
	    	for(int i = 0; i < m.map.length; i++)
			{
				for (int j = 0; j < m.map[i].length; j++)
				{
					if( this.map[i][j] != null)
					{
						try{
							m.map[i][j] = (Tile)this.map[i][j].clone();
						}catch (CloneNotSupportedException e)
						{
							System.err.println("This should never occur.");
						}
					}else
						System.out.println("No Good.");
				}
			}
	    	return m;
	    }
	
	}
	
	public Map(InputStream stream) {
		Scanner s = new Scanner(stream);
		ArrayList<Tile[]> list = new ArrayList<Tile[]>();
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
				case 'S': item = new Sword(new Location(c,r)); break;
				case 'B': item = new Boulder(new Location(c,r)); break;
				case 'L': item = new PotionLife(new Location(c,r)); break;
				//case 'm': item = new Mana(); break;
				//case 'C': item = new Crossbow(); break;
				//case 'A': item = new Arrows(); break;
				//case 't': item = new Staff(); break;
				case ' ': break;
				default: throw new RuntimeException("Bad map - Item");
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
		matrix = new Matrix(list.toArray(new Tile[0][]));
	}
	
	public Tile getTile(int x, int y) {
		return matrix.getTile(x, y);
	}
	
	public Matrix getMatrix()
	{
		Matrix m = null;
		try{
			m = (Matrix)matrix.clone();
		}catch (CloneNotSupportedException e){}
		return m;
	}
	
	public Iterator<Tile> getTiles(final int minX, final int minY, final int maxX, final int maxY) {
		Matrix m = null;
		try
		{
			m = (Matrix)matrix.clone();
		}catch (CloneNotSupportedException e)
		{/* Should never occur*/}
    	return  m.getTiles(minX, minY, maxX, maxY);
		//return matrix.getTiles(minX, minY, maxX, maxY);
	}
	
    public Iterator<Tile> getTiles() { 
    	Matrix m = null;
		try
		{
			m = (Matrix)matrix.clone();
		}catch (CloneNotSupportedException e)
		{/* Should never occur*/}
//    	return  matrix.getTiles();
    	return matrix.getTiles();
    }
}
