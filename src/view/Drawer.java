package view;

import java.awt.*;
import javax.imageio.*;
import java.io.IOException;

import model.*;

public class Drawer {
	private Image grassTerrain;
	private Image mountainTerrain;
	private Image waterTerrain;
	
	public Drawer() {
		ClassLoader loader = getClass().getClassLoader();
		try {
			grassTerrain = ImageIO.read(loader.getResourceAsStream("res/img/grass.png"));
			mountainTerrain = ImageIO.read(loader.getResourceAsStream("res/img/mountain.png"));
			waterTerrain = ImageIO.read(loader.getResourceAsStream("res/img/water.png"));			
		}
		catch(IOException e) {}
	}
	
	public void doDraw(Map map, Graphics g) {
		int height = grassTerrain.getHeight(null);
		int width = grassTerrain.getWidth(null);
		int x = 0;
		int y = 0;
		for(Tile[] row: map.matrix) {
			x = 0;
			for(Tile tile: row) {
				Image img = null;
				if(tile.getTerrain().toString().contains("Grass")) img = grassTerrain;
				else if(tile.getTerrain().toString().contains("Mountain")) img = mountainTerrain;
				else if(tile.getTerrain().toString().contains("Water")) img = waterTerrain;
				g.drawImage(img, x, y, null);
				x += width;
			}
			y += height;
		}
	}
	
	public void drawGrassTerrain(GrassTerrain terrain) {
		
	}
	
	public void drawMountainTerrain(MountainTerrain terrain) {
		
	}
	
	public void drawWaterTerrain(WaterTerrain terrain) {
		
	}
	
	public void drawGoldStarDecal(GoldStar decal) {}
	public void drawRedCrossDecal(RedCross decal) {}
	public void drawSkullAndCrossbonesDecal(SkullAndCrossbones decal) {}
}

