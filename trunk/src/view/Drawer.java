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
			statsView.drawStatsView(graphics, avatar, width, height);
			Console.getInstance().drawConsoleView(graphics, width, height);
		}
		//Inventory Stuff
		if(model.isInventoryUp()){ 
			this.drawInventoryView(avatar, width, height);
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
	
	public void drawConsoleView(int width, int height){
		Queue<String> messages = Console.getInstance().getStringList();//Messages to Show
		int stats_width=310;//only change this is stats window size is changed
		int console_width = 400; 
		int console_height = 100;
		int left_indent = 20;
		int top_indent = 20;
		int max_messages=4; //the max number of messages the Console can show at One Time;
		graphics.setColor(Color.black);
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .3f));
		graphics.fillRoundRect(width-(console_width+stats_width), height-console_height, console_width, console_height, 3, 3);
		//graphics.drawImage(statsView,width-menu_width, height-menu_height,null);
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		graphics.setColor(Color.white);
		graphics.setFont(new Font("SansSerif", Font.BOLD, 16));
		//Text Formatting Numbers
		int text_width = width-(console_width+stats_width) + left_indent; 
		int text_height = height - console_height + top_indent;
		//Draws Messages on Console
		int current_line = 0;
		if(messages != null) {
			while(!messages.isEmpty() && current_line < max_messages){
				graphics.drawString(messages.remove(), text_width, text_height+current_line*18);
				++current_line;
			}
		}
	}
	//Pure Hack Good Luck Understanding
	public void drawInventoryView(Entity avatar,int width,int height){
		java.util.Iterator<Item> items = avatar.getInventoryItems();
		Location mouse_clicked=new Location(35,255);
		int atHeight = 0;
		int inventory_width = 300; 
		int inventory_height = height;
		graphics.setColor(Color.black);
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .3f));
		graphics.fillRoundRect(0, 0, inventory_width,inventory_height, 3, 3);
		
		
		graphics.setColor(Color.RED);
		graphics.fillRect(0, 0, inventory_width, 200);
		atHeight=200;
		//graphics.drawImage(statsView,width-menu_width, height-menu_height,null);
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		graphics.setColor(Color.white);
		graphics.setFont(new Font("SansSerif", Font.BOLD, 24));
		graphics.drawString("Inventory",75 , atHeight+30);
		atHeight+=30;
		atHeight+=20;
		
		int ibox_size =30;
		for(int i=0;i<12;i++){
			if(i%2==0){
				graphics.setColor(Color.BLACK);
			}else{
				graphics.setColor(Color.GRAY);
			}
			for(int j=0;j<6;j++){
				Image img =null;
				if(items.hasNext()){
					Item citem = items.next();
					if(citem.toString().compareTo("Sword")==0){
						img = this.sword;
					}
					if(citem.toString().compareTo("Potion Life")==0){
						img = this.potionlife;
					}
				}
				int startx = (j*(ibox_size+10))+30;
				int starty = (i*(ibox_size+10))+atHeight;
				Color prev = graphics.getColor();
				if(mouse_clicked.getX() >= startx && mouse_clicked.getX()<=startx+ibox_size &&
					mouse_clicked.getY() >= starty && mouse_clicked.getY()<=starty+ibox_size){
					graphics.setColor(Color.YELLOW);
					//do Damage to Item
				}
				graphics.fillRoundRect(startx,starty , ibox_size,ibox_size, 3, 3);
				if(img != null){
					graphics.drawImage(img, startx, starty, null);
				}
				graphics.setColor(prev);
			}
		}
	}
}

