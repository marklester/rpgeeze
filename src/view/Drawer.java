package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

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
import model.item.*;
import model.Occupation;
import model.Smasher;
import model.Sneak;
import model.Summoner;
import util.ContinuousIterator;
import util.Iterator;
import util.MultiplyIterator;
import util.Observer;
import util.Subject;
import util.Pair;
import util.ResourceLoader;

public class Drawer implements Observer {
	private final static int SLOW_DOWN_FACTOR = 2;
	
	// Drawer Singleton
	private static Drawer drawerInstance;
	private static StatView statsView;
	private static InventoryView inventoryView;
	private static Hashtable<Pair<Occupation, Direction>, Iterator<Image>> avatar = new Hashtable<Pair<Occupation, Direction>, Iterator<Image>>();
	private final Queue<Map.Matrix> mapStateQueue = new LinkedList<Map.Matrix>();

	private Graphics2D graphics;// For Transparency
	private Location cursor = null;	
	public static View view;
	
	private int tileWidth;
	private int tileHeight;
	
	protected Drawer() {

		tileWidth = ResourceLoader.getInstance().getTerrainWidth();
		tileHeight = ResourceLoader.getInstance().getTerrainHeight();		
		statsView = new StatView(ResourceLoader.getInstance().getImage("img/statsviewbg.jpg"));
	
		for(Occupation occ: new Occupation[] {new Smasher(), new Summoner(), new Sneak()}) {
			String s = occ.toString().toLowerCase();
			
			avatar.put(new Pair<Occupation, Direction>(occ, Direction.NORTH), new MultiplyIterator<Image>(new ContinuousIterator<Image>(
				ResourceLoader.getInstance().getImage("img/" + s + "/" + s + "WalkNorth1.png"),
				ResourceLoader.getInstance().getImage("img/" + s + "/" + s + "WalkNorth2.png")
			), SLOW_DOWN_FACTOR));
	
			avatar.put(new Pair<Occupation, Direction>(occ, Direction.SOUTH), new MultiplyIterator<Image>(new ContinuousIterator<Image>(
				ResourceLoader.getInstance().getImage("img/" + s + "/" + s + "WalkSouth1.png"),
				ResourceLoader.getInstance().getImage("img/" + s + "/" + s + "WalkSouth2.png")
			), SLOW_DOWN_FACTOR));
			
			avatar.put(new Pair<Occupation, Direction>(occ, Direction.EAST), new MultiplyIterator<Image>(new ContinuousIterator<Image>(
				ResourceLoader.getInstance().getImage("img/" + s + "/" + s + "WalkEast1.png"),
				ResourceLoader.getInstance().getImage("img/" + s + "/" + s + "WalkEast2.png")
			), SLOW_DOWN_FACTOR));
	
			avatar.put(new Pair<Occupation, Direction>(occ, Direction.WEST), new MultiplyIterator<Image>(new ContinuousIterator<Image>(
				ResourceLoader.getInstance().getImage("img/" + s + "/" + s + "WalkWest1.png"),
				ResourceLoader.getInstance().getImage("img/" + s + "/" + s + "WalkWest2.png")
			), SLOW_DOWN_FACTOR));
	
			avatar.put(new Pair<Occupation, Direction>(occ, Direction.NORTHEAST), avatar.get(new Pair<Occupation, Direction>(occ, Direction.NORTH)));
			avatar.put(new Pair<Occupation, Direction>(occ, Direction.NORTHWEST), avatar.get(new Pair<Occupation, Direction>(occ, Direction.NORTH)));
			avatar.put(new Pair<Occupation, Direction>(occ, Direction.SOUTHEAST), avatar.get(new Pair<Occupation, Direction>(occ, Direction.SOUTH)));
			avatar.put(new Pair<Occupation, Direction>(occ, Direction.SOUTHWEST), avatar.get(new Pair<Occupation, Direction>(occ, Direction.SOUTH)));
		}
	}

	public static Drawer getInstance() {
		if(drawerInstance == null)
			drawerInstance = new Drawer();
		return drawerInstance;
	}

	public void setInventoryView(InventoryView iv) {
		inventoryView = iv;
	}
	
	public void doDraw(Graphics g, Model model, int width, int height) {
		Entity avatar = model.getAvatar();
		this.graphics = (Graphics2D) g;
		int horizOffset = (width - tileWidth) / 2 - avatar.getTile().getLocation().getX() * tileWidth;
		int vertOffset = (height - tileHeight) / 2 - avatar.getTile().getLocation().getY() * tileHeight;

		int horizTiles = width / tileWidth + 3;
		int vertTiles = height / tileHeight + 3;

		int minX = avatar.getTile().getLocation().getX() - horizTiles / 2;
		int minY = avatar.getTile().getLocation().getY() - vertTiles / 2;
		int maxX = avatar.getTile().getLocation().getX() + horizTiles / 2;
		int maxY = avatar.getTile().getLocation().getY() + vertTiles / 2;

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
			inventoryView.drawInventoryView(this.graphics, model.getAvatar(), width, height);
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
	
	public void drawMe(String name) {
		doDrawImage(ResourceLoader.getInstance().getItemImage(name));
	}
			
	public void drawEntity(Entity entity) {
		Iterator<Image> iter = avatar.get(new Pair<Occupation, Direction>(entity.getOccupation(), entity.getFacingDirection()));
		doDrawImage(iter.current());
		iter.advance();
	}
	
	public boolean isOnInventory(Point p) {
		return inventoryView.isOnInventory(p);
	}
	
	
}
