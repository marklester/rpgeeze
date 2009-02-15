package view;

import java.awt.*;

import javax.imageio.*;
import java.io.IOException;

import util.*;
import model.*;
import model.items.*;

import java.util.Hashtable;
import java.util.Queue;
import java.util.LinkedList;

public class Drawer implements Observer{
	
	public class Constraint {
		public int minX;
		public int minY;
		public int maxX;
		public int maxY;
	}
	
	//Drawer Singleton
	private static Drawer drawerInstance;
	
	private static Image grassTerrain, mountainTerrain, waterTerrain;
	private static Image goldStar, redCross, skullAndCrossbones;
	private static Image boulder,sword,potionlife,crossbow;
	private static StatView statsView;
	private static InventoryView inventoryView;

	private static Hashtable<Direction,Iterator<Image>> avatar = new Hashtable<Direction,Iterator<Image>>();
	private final java.util.Queue<Map.Matrix> mapStateQueue = new LinkedList<Map.Matrix>(); 
	
	private Graphics2D graphics;//For Transparency
	private Location cursor = null;

	private Drawer() {
		ClassLoader loader = getClass().getClassLoader();
		try {
			grassTerrain = ImageIO.read(loader.getResourceAsStream("res/img/terrain20px/GrassTerrain.png"));
			mountainTerrain = ImageIO.read(loader.getResourceAsStream("res/img/terrain20px/MountainTerrain.png"));
			waterTerrain = ImageIO.read(loader.getResourceAsStream("res/img/terrain20px/WaterTerrain.png"));

			goldStar = ImageIO.read(loader.getResourceAsStream("res/img/goldenstar.png"));
			redCross = ImageIO.read(loader.getResourceAsStream("res/img/redcross.png"));
			skullAndCrossbones = ImageIO.read(loader.getResourceAsStream("res/img/skullandcrossbones.png"));
				
	        sword = ImageIO.read(loader.getResourceAsStream("res/img/sword.png")); 
			boulder = ImageIO.read(loader.getResourceAsStream("res/img/terrain20px/Boulder.png"));
			potionlife = ImageIO.read(loader.getResourceAsStream("res/img/potionlife.png"));
			crossbow = ImageIO.read(loader.getResourceAsStream("res/img/crossbow.png"));
				
			statsView = new StatView(ImageIO.read(loader.getResourceAsStream("res/img/statsviewbg.jpg")));
			inventoryView = new InventoryView();

			avatar.put(Direction.NORTH, new ContinuousIterator<Image>(
				ImageIO.read(loader.getResourceAsStream("res/img/smasher/smasherWalkNorth1.png")),
				ImageIO.read(loader.getResourceAsStream("res/img/smasher/smasherWalkNorth2.png"))
			));
				
			avatar.put(Direction.SOUTH, new ContinuousIterator<Image>(
				ImageIO.read(loader.getResourceAsStream("res/img/smasher/smasherWalkSouth1.png")),
				ImageIO.read(loader.getResourceAsStream("res/img/smasher/smasherWalkSouth2.png"))
			));
				
			avatar.put(Direction.EAST, new ContinuousIterator<Image>(
				ImageIO.read(loader.getResourceAsStream("res/img/smasher/smasherWalkEast1.png")),
				ImageIO.read(loader.getResourceAsStream("res/img/smasher/smasherWalkEast2.png"))
			));
				
			avatar.put(Direction.WEST, new ContinuousIterator<Image>(
				ImageIO.read(loader.getResourceAsStream("res/img/smasher/smasherWalkWest1.png")),
				ImageIO.read(loader.getResourceAsStream("res/img/smasher/smasherWalkWest2.png"))
			));
		}
		catch(IOException e) {}
	}
	
	public static Drawer getInstance() {
		if (drawerInstance == null)
			drawerInstance = new Drawer();
		return drawerInstance;
	}
	
	public void doDraw(Graphics g,Model model,int width, int height) {
		Entity avatar = model.getAvatar();
		//Map map = model.getMap();
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
		
		//Tile entityTile = null;
		
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
		if(model.getAvatar().getStats().isVisible()){
			statsView.drawStatsView(graphics, avatar, width, height);
			Console.getInstance().drawConsoleView(graphics, width, height);
		}
		//Inventory Stuff
		if(model.getAvatar().getInventory().isVisible()){ 
			inventoryView.drawInventoryView(graphics, model.getAvatar().getInventory().clone(), width, height);
		}
	}
	
	public void update(Subject s) 	{
		if(s instanceof Model)
			this.holdSnapshot(((Model)s).publishState());
	}
	
	public synchronized void holdSnapshot(Map.Matrix matrix) {
		mapStateQueue.add(matrix);
	}
	
	private synchronized Map.Matrix getLatestState() 	{
		while(mapStateQueue.size() > 1)
			mapStateQueue.poll();
		return mapStateQueue.poll();
	}
	
	private void doDrawImage(Image img) {
		graphics.drawImage(img, cursor.getX(), cursor.getY(), null);
	}
	
	public void drawGrassTerrain(GrassTerrain terrain) {
		doDrawImage(grassTerrain);
	}
	
	public void drawMountainTerrain(MountainTerrain terrain) {
		doDrawImage(mountainTerrain);
	}
	
	public void drawWaterTerrain(WaterTerrain terrain) {
		doDrawImage(waterTerrain);
	}	
	
	public void drawEntity(Entity entity) {
		Iterator<Image> iter = avatar.get(entity.getFacingDirection());
		doDrawImage(iter.current());
		iter.advance();
	}
	
	public void drawGoldStarDecal(GoldStar decal) {
		doDrawImage(goldStar);
	}
	
	public void drawRedCrossDecal(RedCross decal) {
		doDrawImage(redCross);
	}
	
	public void drawSkullAndCrossbonesDecal(SkullAndCrossbones decal) {	
		doDrawImage(skullAndCrossbones);
	}
	
	public void drawSword(Sword item) {
		doDrawImage(sword);
	}
	
	public void drawBoulder(Boulder item) {
		doDrawImage(boulder);
	}	
	
	public void drawCrossBow(CrossBow item) {
		doDrawImage(crossbow);
	}	
	
	public void drawPotionLife(PotionLife item) {
		doDrawImage(potionlife);
	}
	
	public boolean isOnInventory(Point p)
	{
		return inventoryView.isOnInventory(p);
	}
	public void rightClickInventory(Point p)
	{
		inventoryView.rightClick(p);
	}
	public void leftClickInventory(Point p)
	{
		inventoryView.leftClick(p);
	}
}

