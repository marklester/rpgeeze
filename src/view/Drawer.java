package view;

import java.awt.*;
import javax.imageio.*;
import java.io.IOException;

import util.*;
import model.*;

public class Drawer {
	private static Image grassTerrain, mountainTerrain, waterTerrain;
	private static Image goldStar,redCross,skullAndCrossbones;
	
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
				
				sprite = ImageIO.read(loader.getResourceAsStream("res/img/sprite_left.png"));
			}
			catch(IOException e) {}
		}
		this.graphics = g;
	}
	
	private Location modelToView(Location location) {
		int height = grassTerrain.getHeight(null);
		int width = grassTerrain.getWidth(null);
		return new Location(
			location.getX() * width,
			location.getY() * height
		);
	}
	
	public void doDraw(Map map) {
		Iterator<Tile> iter = map.getTiles();
		for(iter.reset(); !iter.isDone(); iter.advance()) {
			Tile tile = iter.current();
			cursor = modelToView(tile.getLocation());
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
	
	
	
	public void drawTakeableItem(TakeableItem item) {}
	public void drawObstacle(Obstacle item) {}
	public void drawOneShotItem (OneShotItem item) {}
	public void drawInteractiveItem (InteractiveItem item) {}
}

