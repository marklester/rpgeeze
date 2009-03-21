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

	private static Image grassTerrain, mountainTerrain, waterTerrain;
	private static Image goldStar, redCross, skullAndCrossbones, fireDecal;
	private static Image boulder, sword, potionlife, crossbow, shield, redArmor, boots, arrows, mana;
	private static Image portal, healthPack, helmet;

	private static StatView statsView;
	private static InventoryView inventoryView;

	private static Hashtable<Pair<Occupation, Direction>, Iterator<Image>> avatar = new Hashtable<Pair<Occupation, Direction>, Iterator<Image>>();
//	private static Hashtable<Direction, Iterator<Image>> avatar = new Hashtable<Direction, Iterator<Image>>();
	private final Queue<Map.Matrix> mapStateQueue = new LinkedList<Map.Matrix>();

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
		fireDecal = ResourceLoader.getInstance().getImage("img/fire.png");

		sword = ResourceLoader.getInstance().getImage("img/sword.png");
		boulder = ResourceLoader.getInstance().getImage("img/terrain20px/Boulder.png");
		potionlife = ResourceLoader.getInstance().getImage("img/potionlife.png");
		crossbow = ResourceLoader.getInstance().getImage("img/crossbow.png");
		shield = ResourceLoader.getInstance().getImage("img/shield.png");
		redArmor = ResourceLoader.getInstance().getImage("img/redarmor.png");
		boots = ResourceLoader.getInstance().getImage("img/boots.png");
		arrows = ResourceLoader.getInstance().getImage("img/arrows.png");
		mana = ResourceLoader.getInstance().getImage("img/mana.png");
		helmet = ResourceLoader.getInstance().getImage("img/helmet.png");
		portal = ResourceLoader.getInstance().getImage("img/portal20px.png");
		healthPack = ResourceLoader.getInstance().getImage("img/healthpack20px.png");
		
		
		statsView = new StatView(ResourceLoader.getInstance().getImage("img/statsviewbg.jpg"));
		//inventoryView = new InventoryView();
	
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
		Iterator<Image> iter = avatar.get(new Pair<Occupation, Direction>(entity.getOccupation(), entity.getFacingDirection()));
		doDrawImage(iter.current());
		iter.advance();
	}

	public void drawGoldStar(GoldStar decal) {
		doDrawImage(goldStar);
	}

	public void drawRedCross(RedCross decal) {
		doDrawImage(redCross);
	}
	
	public void drawFireDecal(FireDecal fire) {
		doDrawImage(fireDecal);
	}

	public void drawSkullAndCrossbones(SkullAndCrossbones decal) {
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
	
	public void drawShield(Shield item) {
		doDrawImage(shield);
	}
	
	public void drawRedArmor(RedArmor item) {
		doDrawImage(redArmor);
	}
	
	public void drawBoots(Boots item) {
		doDrawImage(boots);
	}
	
	public void drawArrows(Arrows item) {
		doDrawImage(arrows);
	}

	public void drawMana(Mana item) {
		doDrawImage(mana);
	}
	
	
	public void drawPortal(PortalItem item) {
		doDrawImage(portal);
	}
	
	public void drawHealthPack(HealthPack item) {
		doDrawImage(healthPack);
	}
	
	public void drawHelmet(Helmet item) {
		doDrawImage(helmet);
	}
	
	public boolean isOnInventory(Point p) {
		return inventoryView.isOnInventory(p);
	}
}