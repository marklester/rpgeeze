package view;

import java.awt.*;
import javax.imageio.*;
import java.io.IOException;

import util.*;
import model.*;

import java.util.Hashtable;

public class Drawer {
	
	//Drawer Singleton
	private static Drawer drawerInstance;
	
	private static Image grassTerrain, mountainTerrain, waterTerrain;
	private static Image goldStar, redCross, skullAndCrossbones;
	private static Image boulder,sword;


	private static Hashtable<Direction,Image> avatar = new Hashtable<Direction,Image>();
	
	private static boolean loaded = false;
	
	private Graphics graphics;
	private Location cursor = null;
	
	private Drawer() {
			ClassLoader loader = getClass().getClassLoader();
			try {
				grassTerrain = ImageIO.read(loader.getResourceAsStream("res/img/grass.png"));
				mountainTerrain = ImageIO.read(loader.getResourceAsStream("res/img/mountain.png"));
				waterTerrain = ImageIO.read(loader.getResourceAsStream("res/img/water.png"));

				goldStar = ImageIO.read(loader.getResourceAsStream("res/img/goldenstar.png"));
				redCross = ImageIO.read(loader.getResourceAsStream("res/img/redcross.png"));
				//skullAndCrossbones = ImageIO.read(loader.getResourceAsStream("res/img/skull.png"));
				
		        sword = ImageIO.read(loader.getResourceAsStream("res/img/sword.png")); 
				boulder = ImageIO.read(loader.getResourceAsStream("res/img/boulder.png"));

				avatar.put(Direction.NORTH, ImageIO.read(loader.getResourceAsStream("res/img/avatar_n.png")));
				avatar.put(Direction.SOUTH, ImageIO.read(loader.getResourceAsStream("res/img/avatar_s.png")));
				avatar.put(Direction.EAST, ImageIO.read(loader.getResourceAsStream("res/img/avatar_e.png")));
				avatar.put(Direction.WEST, ImageIO.read(loader.getResourceAsStream("res/img/avatar_w.png")));
			}
			catch(IOException e) {}
	}
	
	public static Drawer getInstance()
	{
		if (drawerInstance == null)
			drawerInstance = new Drawer();
		return drawerInstance;
	}
	
	public void doDraw(Graphics g, Map map, Entity avatar, int width, int height) {
		this.graphics = g;
		int tileHeight = grassTerrain.getHeight(null);
		int tileWidth = grassTerrain.getWidth(null);
		int horizOffset = (width - tileWidth) / 2 - avatar.getTile().getLocation().getX() * tileWidth;
		int vertOffset = (height - tileHeight) / 2 - avatar.getTile().getLocation().getY() * tileHeight;
		
		int horizTiles = width / tileWidth + 3;
		int vertTiles = height / tileHeight + 3;
		
		int minX = avatar.getTile().getLocation().getX() - horizTiles / 2;
		int minY = avatar.getTile().getLocation().getY() - vertTiles / 2;
		int maxX = avatar.getTile().getLocation().getX() + horizTiles / 2;
		int maxY = avatar.getTile().getLocation().getY() + vertTiles / 2;
		
		Iterator<Tile> iter = map.getTiles(minX, minY, maxX, maxY);
		for(iter.reset(); !iter.isDone(); iter.advance()) {
			Tile tile = iter.current();
			cursor = new Location(
				tile.getLocation().getX() * tileWidth + horizOffset,
				tile.getLocation().getY() * tileHeight + vertOffset
			);
			tile.draw(this);
		}
	}
	
	public void drawGrassTerrain(GrassTerrain terrain) {
		graphics.drawImage(grassTerrain, cursor.getX(), cursor.getY(), null);
	}
	
	public void drawMountainTerrain(MountainTerrain terrain) {
		graphics.drawImage(mountainTerrain, cursor.getX(), cursor.getY(), null);
	}
	
	public void drawWaterTerrain(WaterTerrain terrain) {
		graphics.drawImage(waterTerrain, cursor.getX(), cursor.getY(), null);
	}
	
	
	public void drawEntity(Entity entity) {
		graphics.drawImage(avatar.get(entity.getFacingDirection()), cursor.getX(), cursor.getY(), null);
	}
	
	public void drawGoldStarDecal(GoldStar decal) {
		graphics.drawImage(goldStar, cursor.getX() + 20, cursor.getY() + 20, null);
	}
	
	public void drawRedCrossDecal(RedCross decal) {
		graphics.drawImage(redCross, cursor.getX() + 20, cursor.getY() + 20, null );
	}
	
	public void drawSkullAndCrossbonesDecal(SkullAndCrossbones decal) {
		graphics.drawImage(skullAndCrossbones, cursor.getX() + 5, cursor.getY() + 10, null);	
	}
	
	public void drawSword(Sword item) {
		graphics.drawImage(sword, cursor.getX(), cursor.getY(), null);
	}
	
	public void drawBoulder(Boulder item) {
		graphics.drawImage(boulder, cursor.getX(), cursor.getY(), null);
	}	
	
	public void drawBoulder(Sword item) {
		graphics.drawImage(sword, cursor.getX(), cursor.getY(), null);
	}
	
	
}

