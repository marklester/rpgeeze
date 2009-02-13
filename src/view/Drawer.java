package view;

import java.awt.*;
import javax.imageio.*;
import java.io.IOException;

import util.*;
import model.*;

public class Drawer {
	private static Image grassTerrain, mountainTerrain, waterTerrain;
	private static Image goldStar, redCross, skullAndCrossbones;

	private static Image boulder;

	private static Image sword;

	
	private static Image sprite;
	
	private static boolean loaded = false;
	
	private Graphics graphics;
	private Location cursor = null;
	
	public Drawer(Graphics g) {
		if(!loaded) {
			ClassLoader loader = getClass().getClassLoader();
			try {
				grassTerrain = ImageIO.read(loader.getResourceAsStream("res/img/grass.png"));
				mountainTerrain = ImageIO.read(loader.getResourceAsStream("res/img/mountain.png"));
				waterTerrain = ImageIO.read(loader.getResourceAsStream("res/img/water.png"));

				goldStar = ImageIO.read(loader.getResourceAsStream("res/img/goldenstar.png"));
				redCross = ImageIO.read(loader.getResourceAsStream("res/img/redcross.png"));
				//skullAndCrossbones = ImageIO.read(loader.getResourceAsStream("res/img/skull.png"));
                //sword = ImageIO.read(loader.getResourceAsStream("res/img/sword.png"));
				//boulder = ImageIO.read(loader.getResourceAsStream("res/img/boulder.png"));

				
				sprite = ImageIO.read(loader.getResourceAsStream("res/img/sprite_left.png"));

			}
			catch(IOException e) {}
		}
		this.graphics = g;
	}
	
	public void doDraw(Map map, Entity avatar, int width, int height) {
		int tileHeight = grassTerrain.getHeight(null);
		int tileWidth = grassTerrain.getWidth(null);
		int horizOffset = (width - tileWidth) / 2 - avatar.getTile().getLocation().getX() * tileWidth;
		int vertOffset = (height - tileHeight) / 2 - avatar.getTile().getLocation().getY() * tileHeight;
		
		Iterator<Tile> iter = map.getTiles();
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
		graphics.drawImage(sprite, cursor.getX(), cursor.getY(), null);
	}
	
	
	
	
	public void drawGoldStarDecal(GoldStar decal) {
		graphics.drawImage(goldStar, cursor.getX() + 5, cursor.getY() + 10, null);
	}
	
	public void drawRedCrossDecal(RedCross decal) {
		graphics.drawImage(redCross, cursor.getX() + 5, cursor.getY() + 10, null );
	}
	
	public void drawSkullAndCrossbonesDecal(SkullAndCrossbones decal) {
		graphics.drawImage(skullAndCrossbones, cursor.getX() + 5, cursor.getY() + 10, null);	
	}
	
	public void drawSword(Sword item) {
		graphics.drawImage(sword, cursor.getX(), cursor.getY(), null);
	}
	
	public void drawBolder(Boulder item) {
		graphics.drawImage(boulder, cursor.getX(), cursor.getY(), null);
	}	
	
	public void drawBoulder(Boulder boulder) {
		
	}
}

