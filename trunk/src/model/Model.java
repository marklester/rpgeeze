package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import util.Observer;
import util.ResourceLoader;

public class Model implements util.Subject {
	protected final Queue<Command> commands = new LinkedList<Command>();
	protected final List<Observer> observers = new LinkedList<Observer>();

	private Map.Matrix snapshot;

	private Entity avatar;
	private Map map;
	private final Location avatarStart;
	private boolean paused;

	public Model(Map map, Entity avatar) {
		this.map = map;
		this.avatar = avatar;
		this.paused = false;
		
		avatarStart = avatar.getTile().getLocation();
		Tile tile = map.getTile(avatarStart);
		tile.accept(avatar);
	}

	public Map getMap() {
		return this.map;
	}

	public synchronized void invoke(Command cmd) {
		this.commands.add(cmd);
	}

	public void update() {
		if(!paused ) {
			// read task queue
			Queue<Command> tempQ = null;
			synchronized(this) {
				tempQ = (Queue<Command>) ((LinkedList<Command>) this.commands).clone();
				this.commands.clear();
			}
			while(!tempQ.isEmpty())
				tempQ.remove().execute(this);
	
			this.avatar.update();
			updateStatusOfAvatar();
			this.snapshot = this.map.getMatrix().clone();
			updateObservers();
	
			// update map
			// update items
			// apply AoE's
			// update NPC's
			// update entity
			// --Jose
		}
		else
			commands.clear();
	}
	
	public void updateStatusOfAvatar() {
		//He's dead, so drop a life and respawn
		if(!avatar.isAlive()) {
			avatar.decALife();
			//Now check if there are any lives remaining
			int numOfLivesLeft = avatar.getStats().getPrimary().livesLeft; 
			if(numOfLivesLeft == 0)
				endGame();
			else
				respawn(numOfLivesLeft);
		}
		//Is the avatar now on a AE? If so, apply it!
		else if(avatar.getTile().hasAE())
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

	public void moveAvatarRequest(Location l) {
		if(this.avatar.canMove())
			avatar.move(l);
	}
	
	public boolean isPaused() {
		return paused;
	}
	public void pause()
	{
		paused = true;
	}
	public void unPause()
	{
		paused = false;
	}
	
	public void endGame() {
		Thread.currentThread().interrupt();
	}
	
	private void respawn(int numOfLives) {
		Location from = avatar.getTile().getLocation();
		Location to = avatarStart;
		avatar.resetStats(numOfLives); //reset all stats, except num of lives - that shouldn't be reset!
		avatar.move(to.subtract(from));
	}
}