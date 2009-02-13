package view;

import java.awt.*;
import javax.imageio.*;
import java.io.IOException;

import model.*;

public class Drawer {
	private static Image grassTerrain;
	private static Image mountainTerrain;
	private static Image waterTerrain;
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
		for(Tile[] row: map.matrix) {
			for(Tile tile: row) {
				cursor = modelToView(tile.getLocation());
				tile.draw(this);
			}
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
	
	public void drawGoldStarDecal(GoldStar decal) {}
	public void drawRedCrossDecal(RedCross decal) {}
	public void drawSkullAndCrossbonesDecal(SkullAndCrossbones decal) {}
	
	public void drawTakeableItem(TakeableItem item) {}
	public void drawObstacle(Obstacle item) {}
	public void drawOneShotItem (OneShotItem item) {}
	public void drawInteractiveItem (InteractiveItem item) {}
}

