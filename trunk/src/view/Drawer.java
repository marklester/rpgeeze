package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.Hashtable;
import java.util.LinkedList;

import model.Direction;
import model.Entity;
import model.decal.*;
import model.GrassTerrain;
import model.Location;
import model.Map;
import model.Model;
import model.MountainTerrain;
import model.Tile;
import model.WaterTerrain;
import model.item.Boulder;
import model.item.CrossBow;
import model.item.PotionLife;
import model.item.Sword;
import util.ContinuousIterator;
import util.Iterator;
import util.Observer;
import util.Subject;
import util.ResourceLoader;

public class Drawer implements Observer {

	public class Constraint {
		public int minX;
		public int minY;
		public int maxX;
		public int maxY;
	}

	// Drawer Singleton
	private static Drawer drawerInstance;

	private static Image grassTerrain, mountainTerrain, waterTerrain;
	private static Image goldStar, redCross, skullAndCrossbones;
	private static Image boulder, sword, potionlife, crossbow;
	private static StatView statsView;
	private static InventoryView inventoryView;

	private static Hashtable<Direction, Iterator<Image>> avatar = new Hashtable<Direction, Iterator<Image>>();
	private final java.util.Queue<Map.Matrix> mapStateQueue = new LinkedList<Map.Matrix>();

	private Graphics2D graphics;// For Transparency
	private Location cursor = null;
	
	public static View view;

	protected Drawer() {
		grassTerrain = ResourceLoader.getInstance().getImage("img/terrain20px/GrassTerrain.png");
		mountainTerrain = ResourceLoader.getInstance().getImage("img/terrain20px/MountainTerrain.png");
		waterTerrain = ResourceLoader.getInstance().getImage("img/terrain20px/WaterTerrain.png");

		goldStar = ResourceLoader.getInstance().getImage("img/goldenstar.png");
		redCross = ResourceLoader.getInstance().getImage("img/redcross.png");
		skullAndCrossbones = ResourceLoader.getInstance().getImage("img/skullandcrossbones.png");

		sword = ResourceLoader.getInstance().getImage("img/sword.png");
		boulder = ResourceLoader.getInstance().getImage("img/terrain20px/Boulder.png");
		potionlife = ResourceLoader.getInstance().getImage("img/potionlife.png");
		crossbow = ResourceLoader.getInstance().getImage("img/crossbow.png");

		statsView = new StatView(ResourceLoader.getInstance().getImage("img/statsviewbg.jpg"));
		inventoryView = new InventoryView();

		avatar.put(Direction.NORTH, new ContinuousIterator<Image>(
			ResourceLoader.getInstance().getImage("img/smasher/smasherWalkNorth1.png"),
			ResourceLoader.getInstance().getImage("img/smasher/smasherWalkNorth2.png")
		));

		avatar.put(Direction.SOUTH, new ContinuousIterator<Image>(
			ResourceLoader.getInstance().getImage("img/smasher/smasherWalkSouth1.png"),
			ResourceLoader.getInstance().getImage("img/smasher/smasherWalkSouth2.png")
		));
		
		avatar.put(Direction.EAST, new ContinuousIterator<Image>(
			ResourceLoader.getInstance().getImage("img/smasher/smasherWalkEast1.png"),
			ResourceLoader.getInstance().getImage("img/smasher/smasherWalkEast2.png")
		));

		avatar.put(Direction.WEST, new ContinuousIterator<Image>(
			ResourceLoader.getInstance().getImage("img/smasher/smasherWalkWest1.png"),
			ResourceLoader.getInstance().getImage("img/smasher/smasherWalkWest2.png")
		));
	}

	public static Drawer getInstance() {
		if(drawerInstance == null)
			drawerInstance = new Drawer();
		return drawerInstance;
	}

	public void doDraw(Graphics g, Model model, int width, int height) {
		Entity avatar = model.getAvatar();
		// Map map = model.getMap();
		this.graphics = (Graphics2D) g;
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

		// Tile entityTile = null;

		Map.Matrix m = getLatestState();
		Iterator<Tile> iter = m.getTiles(minX, minY, maxX, maxY);
		for(iter.reset(); !iter.isDone(); iter.advance()) {
			Tile tile = iter.current();
			this.cursor = new Location(tile.getLocation().getX() * tileWidth + horizOffset, tile.getLocation().getY() * tileHeight + vertOffset);
			tile.draw(this);
		}

		// Stats Stuff
		if(model.getAvatar().getStats().isVisible()) {
			statsView.drawStatsView(this.graphics, avatar, width, height);
			Console.getInstance().drawConsoleView(this.graphics, width, height);
		}
		// Inventory Stuff
		if(view.isInventoryVisible())
			inventoryView.drawInventoryView(this.graphics, model.getAvatar().getInventory().clone(), width, height);
	}

	public void update(Subject s) {
		if(s instanceof Model)
			holdSnapshot(((Model) s).publishState());
	}

	public synchronized void holdSnapshot(Map.Matrix matrix) {
		this.mapStateQueue.add(matrix);
	}

	private synchronized Map.Matrix getLatestState() {
		while(this.mapStateQueue.size() > 1)
			this.mapStateQueue.poll();
		return this.mapStateQueue.poll();
	}

	private void doDrawImage(Image img) {
		this.graphics.drawImage(img, this.cursor.getX(), this.cursor.getY(), null);
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

	public boolean isOnInventory(Point p) {
		return inventoryView.isOnInventory(p);
	}

	public void rightClickInventory(Point p) {
		inventoryView.rightClick(p);
	}

	public void leftClickInventory(Point p) {
		inventoryView.leftClick(p);
	}
}
