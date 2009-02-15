package view;

import java.awt.*;

import javax.imageio.*;
import java.io.IOException;

import util.*;
import model.*;
import model.items.*;

import java.util.Hashtable;

public class Drawer implements Observer{
	
	
	public class Constraint
	{
		public int minX;
		public int minY;
		public int maxX;
		public int maxY;
	}
	
	//Drawer Singleton
	private static Drawer drawerInstance;
	
	private static Image grassTerrain, mountainTerrain, waterTerrain;
	private static Image goldStar, redCross, skullAndCrossbones;
	private static Image boulder,sword,potionlife;
	private static Image statsView;


	private static Hashtable<Direction,Image> avatar = new Hashtable<Direction,Image>();
	private final java.util.Queue<Map.Matrix> mapStateQueue = new java.util.LinkedList<Map.Matrix>(); 
	
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
				potionlife = ImageIO.read(loader.getResourceAsStream("res/img/potionlife.png"));
				
				statsView=ImageIO.read(loader.getResourceAsStream("res/img/statsviewbg.jpg"));
				
					
				avatar.put(Direction.NORTH, ImageIO.read(loader.getResourceAsStream("res/img/smasher/smasherWalkNorth1.png")));
				avatar.put(Direction.SOUTH, ImageIO.read(loader.getResourceAsStream("res/img/smasher/smasherWalkSouth1.png")));
				avatar.put(Direction.EAST, ImageIO.read(loader.getResourceAsStream("res/img/smasher/smasherWalkEast1.png")));
				avatar.put(Direction.WEST, ImageIO.read(loader.getResourceAsStream("res/img/smasher/smasherWalkWest1.png")));
				avatar.put(Direction.NORTH1, ImageIO.read(loader.getResourceAsStream("res/img/smasher/smasherWalkNorth2.png")));
				avatar.put(Direction.SOUTH1, ImageIO.read(loader.getResourceAsStream("res/img/smasher/smasherWalkSouth2.png")));
				avatar.put(Direction.EAST1, ImageIO.read(loader.getResourceAsStream("res/img/smasher/smasherWalkEast2.png")));
				avatar.put(Direction.WEST1, ImageIO.read(loader.getResourceAsStream("res/img/smasher/smasherWalkWest2.png")));
			}
			catch(IOException e) {}
	}
	
	public static Drawer getInstance()
	{
		if (drawerInstance == null)
			drawerInstance = new Drawer();
		return drawerInstance;
	}
	
	public void doDraw(Graphics g, Model model,int width, int height) {
		Entity avatar = model.getAvatar();
		Map map = model.getMap();
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
		
		Tile entityTile = null;
		
		
		Map.Matrix m = getLatestState();
		Iterator<Tile> iter = m.getTiles(minX, minY, maxX, maxY);
		for(iter.reset(); !iter.isDone(); iter.advance()) {
			Tile tile = iter.current();		
			cursor = new Location(
				tile.getLocation().getX() * tileWidth + horizOffset,
				tile.getLocation().getY() * tileHeight + vertOffset
			);
			tile.draw(this);
		}		

		//Stats Stuff
		if(model.isStatsUp()){
			this.drawStatsView(avatar, width, height);
		}
		//Inventory Stuff
		if(model.isInventoryUp()){
			this.drawInventoryView(avatar, width, height);
		}
	}
	
	public void update(Subject s)
	{
		if(s instanceof Model)
		{
			this.holdSnapshot(((Model)s).publishState());
		}
	}
	
	public synchronized void holdSnapshot(Map.Matrix matrix)
	{
		mapStateQueue.add(matrix);
	}
	
	private synchronized Map.Matrix getLatestState()
	{
		while(mapStateQueue.size() > 1)
			mapStateQueue.poll();
		return mapStateQueue.poll();
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
		graphics.drawImage(sword, cursor.getX() + 1, cursor.getY() + 1, null);
	}
	
	public void drawBoulder(Boulder item) {
		graphics.drawImage(boulder, cursor.getX(), cursor.getY(), null);
	}	
	
	public void drawPotionLife(PotionLife item) {
		graphics.drawImage(potionlife, cursor.getX() + 1, cursor.getY() + 1, null);
	}
	//Not visitor like but whatev
	public void drawStatsView(Entity entity, int width, int height){
		int menu_width = 300;
		int menu_height = 300;
		int left_indent = 20;
		int top_indent = 80;
		graphics.setColor(Color.black);
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .9f));
		graphics.drawImage(statsView,width-menu_width, height-menu_height,null);
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		graphics.setColor(Color.black);
		graphics.setFont(new Font("SansSerif", Font.BOLD, 16));
		StringBuffer stats = new StringBuffer();
		//Text Formatting Numbers
		int text_width = width - menu_width + left_indent; 
		int text_height = height - menu_height + top_indent;
		//Freaking ugly tedious code
		int current_line=0;
		graphics.drawString("Primary Stats", text_width, text_height);
		current_line+=18;
		graphics.drawString("Lives Left:"+entity.getStats().getPrimary().getLivesLeft(), text_width, text_height+current_line);
		current_line+=18;
		graphics.drawString("Strength:"+entity.getStats().getPrimary().getStrength(), text_width, text_height+current_line);
		current_line+=18;
		graphics.drawString("Agility:"+entity.getStats().getPrimary().getAgility(), text_width, text_height+current_line);
		current_line+=18;
		graphics.drawString("Experience:"+entity.getStats().getPrimary().getExperience(), text_width, text_height+current_line);
		current_line+=18;
		graphics.drawString("Hardiness:"+entity.getStats().getPrimary().getHardiness(), text_width, text_height+current_line);
		current_line+=18;
		graphics.drawString("Intellect:"+entity.getStats().getPrimary().getIntellect(), text_width, text_height+current_line);
		current_line+=18;
		graphics.drawString("Movement:"+entity.getStats().getMovement(), text_width, text_height+current_line);
		current_line+=18;
		graphics.drawString("nanoTime:" + System.nanoTime(), text_width, text_height+current_line);
		//More Ugly Code for the Second Column of Stats this will show Derived Stats
		text_width = width - menu_width/2;
		current_line = 0;
		graphics.drawString("Derived Stats ", text_width, text_height);
		current_line+=18;
		graphics.drawString("Level:"+entity.getStats().getLevel(), text_width, text_height+current_line);
		current_line+=18;
		graphics.drawString("HP(Life):"+entity.getStats().getLife(), text_width, text_height+current_line);
		current_line+=18;
		graphics.drawString("Mana:"+entity.getStats().getMana(), text_width, text_height+current_line);
		current_line+=18;
		graphics.drawString("Offensive:"+entity.getStats().getOffensiveRating(), text_width, text_height+current_line);
		current_line+=18;
		graphics.drawString("Defensive:"+entity.getStats().getDefensiveRating(), text_width, text_height+current_line);
		current_line+=18;
		graphics.drawString("Armor Rating:"+entity.getStats().getArmorRating(), text_width, text_height+current_line);
		current_line+=18;
	}
	public void drawInventoryView(Entity avater,int width,int height){}
}

