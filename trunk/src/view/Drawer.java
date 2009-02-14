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
	
	private Graphics2D graphics;//For Transparency
	private Location cursor = null;
	
	private Drawer() {
			ClassLoader loader = getClass().getClassLoader();
			try {
				grassTerrain = ImageIO.read(loader.getResourceAsStream("res/img/grass.png"));
				mountainTerrain = ImageIO.read(loader.getResourceAsStream("res/img/mountain.png"));
				waterTerrain = ImageIO.read(loader.getResourceAsStream("res/img/water.png"));

				goldStar = ImageIO.read(loader.getResourceAsStream("res/img/goldenstar.png"));
				redCross = ImageIO.read(loader.getResourceAsStream("res/img/redcross.png"));
				skullAndCrossbones = ImageIO.read(loader.getResourceAsStream("res/img/skullandcrossbones.png"));
				
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
	
	public void doDraw(Graphics g, Map map, Entity avatar, int width, int height,boolean show_menu) {
		this.graphics = (Graphics2D)g;
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
		//Menu Stuff
		if(show_menu){
			this.drawMenu(avatar, width, height);
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
		graphics.drawImage(skullAndCrossbones, cursor.getX() + 20, cursor.getY() + 20, null);	
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
	//Not visitor like but whatev
	public void drawMenu(Entity entity, int width, int height){
		int menu_width = width/3;
		int menu_height = height/3;
		
		graphics.setColor(Color.black);
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .3f));
		graphics.fillRect (width-menu_width, height-menu_height, menu_width, menu_height);
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		graphics.setColor(Color.white);
		graphics.setFont(new Font("SansSerif", Font.BOLD, 16));
		StringBuffer stats = new StringBuffer();
		stats.append("Your Stats:\n");
		stats.append("Life:"+entity.getStats().getLivesLeft());
		//Text Formatting Numbers
		int left_indent = menu_width/10;
		int top_indent = menu_height/8;
		int text_width = width - menu_width + left_indent; 
		int text_height = height - menu_height + top_indent;
		//Freaking ugly tedious code
		int current_line=0;
		graphics.drawString("Your Stats", text_width, text_height);
		current_line+=18;
		graphics.drawString("Lives Left:"+entity.getStats().getLivesLeft(), text_width, text_height+current_line);
		current_line+=18;
		graphics.drawString("Strength:"+entity.getStats().getStrength(), text_width, text_height+current_line);
		current_line+=18;
		graphics.drawString("Agility:"+entity.getStats().getAgility(), text_width, text_height+current_line);
		current_line+=18;
		graphics.drawString("Experience:"+entity.getStats().getExperience(), text_width, text_height+current_line);
		current_line+=18;
		graphics.drawString("Hardiness:"+entity.getStats().getHardiness(), text_width, text_height+current_line);
		current_line+=18;
		graphics.drawString("Intellect:"+entity.getStats().getIntellect(), text_width, text_height+current_line);
		current_line+=18;
		graphics.drawString("Movement:"+entity.getStats().getMovement(), text_width, text_height+current_line);
		current_line+=18;
		graphics.drawString("nanoTime:" + System.nanoTime(), text_width, text_height+current_line);
		//More Ugly Code for the Second Column of Stats this will show Derived Stats
		text_width = width - menu_width/2;
		current_line = 0;
		graphics.drawString("Derived Stats Go Here", text_width, text_height);
		current_line+=18;
		graphics.drawString("Level:"+entity.getStats().getLivesLeft(), text_width, text_height+current_line);
		current_line+=18;
		graphics.drawString("HP(Life):"+entity.getStats().getStrength(), text_width, text_height+current_line);
		current_line+=18;
		graphics.drawString("Mana:"+entity.getStats().getAgility(), text_width, text_height+current_line);
		current_line+=18;
		graphics.drawString("Offensive:"+entity.getStats().getExperience(), text_width, text_height+current_line);
		current_line+=18;
		graphics.drawString("Defensive:"+entity.getStats().getHardiness(), text_width, text_height+current_line);
		current_line+=18;
		graphics.drawString("Armor Rating:"+entity.getStats().getIntellect(), text_width, text_height+current_line);
		current_line+=18;
	}
	
}

