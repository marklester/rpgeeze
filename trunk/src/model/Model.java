package model;

import java.awt.Point;
//import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import controller.WelcomeScreen;

import util.Observer;
import util.ResourceLoader;
import view.Console;
import view.Time;
import view.View;

public class Model implements util.Subject {
	protected final Queue<Command> commands = new LinkedList<Command>();
	protected final List<Observer> observers = new LinkedList<Observer>();

	private Map.Matrix snapshot;

	private Entity avatar;
	private Map map;
	private final Location EntityStartLoc;
	private boolean isPaused;

	public Model(Map map, Entity avatar) {
		// create map
		// create tiles and encompassing stuff
		// create entity
		// create task queue
		// --Jose
		this.map = map;
		this.avatar = avatar;
		isPaused = false;
		// The following code should probably be moved elsewhere. -- Miorel
		Scanner scanner = ResourceLoader.getInstance().getScanner("entities.txt");
		int x = scanner.nextInt();
		int y = scanner.nextInt();
		EntityStartLoc = new Location(x,y);
		Tile tile = map.getTile(x, y);
		tile.setEntity(avatar);
		avatar.setTile(tile);
	}

	public Map getMap() {
		return this.map;
	}

	public synchronized void invoke(Command cmd) {
		this.commands.add(cmd);
	}

	public void update() {
		if (!isPaused ){
			// read task queue
			Queue<Command> tempQ = null;
			synchronized(this) {
				tempQ = (Queue<Command>) ((LinkedList) this.commands).clone();
				this.commands.clear();
			}
			while(!tempQ.isEmpty())
				tempQ.remove().execute(this);
	
			this.avatar.update();
			updateStatusOfAvatar();
			try {
				this.snapshot = this.map.getMatrix().clone();
			}catch(CloneNotSupportedException ce)
			{
				this.snapshot = this.map.getMatrix();
			}
			updateObservers();
	
			// update map
			// update items
			// apply AoE's
			// update NPC's
			// update entity
			// --Jose
		}else
			commands.clear();
	}
	
	public void updateStatusOfAvatar() {
		//He's dead, so drop a life and respawn
		if (!avatar.isAlive()) {
			avatar.decALife();
			//Now check if there are any lives remaining
			int numOfLivesLeft = avatar.getStats().getPrimary().livesLeft; 
			if (numOfLivesLeft == 0)
				endGame();
			else
				respawn(numOfLivesLeft);
		}
		//Is the avatar now on a AE? If so, apply it!
		else if (avatar.getTile().hasAE())
			avatar.getTile().getAE().applyEffect(avatar);
			
	}
	

	// Observer stuff
	public void register(Observer o) {
		this.observers.add(o);
	}

	public void unregister(Observer o) {
		this.observers.remove(o);
	}

	public void updateObservers() {
		for(Observer obs: this.observers)
			obs.update(this);
	}

	public Map.Matrix publishState() {
		return this.snapshot;
	}

	// MoveCommand
	public Entity getAvatar() {
		return this.avatar;
	}

	public void moveAvatarRequest(Direction d) {
		if(this.avatar.canMove())
			moveAvatar(d);
	}

	private void moveAvatar(Direction d) {
		Tile from = this.avatar.getTile();
		if (from.hasAE())
			from.getAE().setMessageFlag(false);
		int newX = from.getLocation().getX() + d.getX();
		int newY = from.getLocation().getY() + d.getY();
		Tile to = this.map.getTile(newX, newY);

		// watch out for race conditions here
		to.accept(this.avatar);
		this.avatar.setFacingDirection(d);
	}

	// MouseMoveCommand
	public void mouseOnscreenAt(int x, int y) {
		// Drawer.getTileFromPosition(x,y) or isOver(someMenu/Inventory)
		// Make some high level decision about pressing on screen buttons
	}

	// MouseClickedCommand
	// public void mouseClickedAt(int x, int y)
	// {
	// if(avatar.getInventory().isVisible() &&
	// avatar.getInventory().isOnInventory(x, y)) {}
	// //avatar.getInventory().click(x, y);
	// //System.out.println("clicked inventory space");
	// }
	
	// Why is Model dealing with mouse clicks?

	
//	public void equipItem(int index) {
//		this.avatar.equipItem(index);
//	}

//	public void uneqipItem(int where) {
//		this.avatar.unequipItem(where);
//	}

	public void dropItem() {
		this.avatar.dropItem();
	}
	
	public boolean isPaused(){
		return isPaused;
	}
	
	public void endGame() {
		isPaused = true;
		WelcomeScreen welcome = new WelcomeScreen();
		
		synchronized(welcome) {
			try {
				welcome.wait();
			}
			catch(InterruptedException e) {}
		}
		avatar.getTile().releaseEntity();
		
		if(welcome.getAction().equals("New")) {
			restartGame();
			this.update();
		}
		else if(welcome.getAction().equals("Quit"))
			System.exit(0);
	}
	
	private void restartGame() {
		//ASSUMPTION: this.isPaused == true
		Entity avatar = new Entity(this.avatar.getOccupation());
		avatar.setTile((map.getTile(this.EntityStartLoc)));
		//Map map = Map.fromStream(ResourceLoader.getInstance().getStream("map.xml"));
		this.avatar = avatar;
		this.map = map;
		isPaused = false; 

	}
	
	public void respawn(int numOfLives) {
		avatar.getTile().setEntity(null); //Set current tile's entity ref to null
		avatar.setTile(map.getTile(EntityStartLoc)); // Set avatar's ref to the orig tile
		map.getTile(EntityStartLoc).setEntity(avatar); // Set orig tile's ref to avatar
		avatar.resetStats(numOfLives); //reset all stats, except num of lives - that shouldn't be reset!
	}
}