package rpgeeze.model;

import java.util.LinkedList;
import java.util.Queue;

import rpgeeze.model.entity.monster.MonsterSpawner;
import rpgeeze.model.entity.monster.*;

import rpgeeze.model.entity.*;
import rpgeeze.model.entity.merchant.*;

import rpgeeze.dp.Command;
import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.model.entity.Entity;

public class Model {
	private boolean paused;
	private boolean active;
	private Map map;
	private PC avatar;

	private Queue<Command> commands = new LinkedList<Command>();
	private HumanPlayerEntityManager mainPlayerManager;
	
	private LogManager lm;
	private MonsterSpawner spawner;
	public Model(Map map, PC avatar) {
		this.map = map;
		this.avatar = avatar;
		mainPlayerManager = new HumanPlayerEntityManager(this.avatar);
		EntityManagerCollection.getInstance().addManager(mainPlayerManager);
		
		Merchant m = new Merchant();
		avatar.getTile().getAbsoluteTile(3, 6).setEntity(m);
		MerchantManager man = new MerchantManager(m);
		EntityManagerCollection.getInstance().addManager(man);
		
		active = true;
		paused = true;
		spawner = new MonsterSpawner( avatar.getTile().getRelativeTile(0, 1), MonsterType.Soldier);
		lm = LogManager.getInstance();
		
		spawner.setTile(avatar.getTile().getAbsoluteTile(6, 21));
		
	}
	public PC getAvatar() {
		return this.avatar;
	}
	
	public synchronized boolean isPaused() {
		return paused;
	}

	public synchronized void togglePaused() {
		setPaused(!isPaused());
	}

	public synchronized void setPaused(boolean value) {
		lm.log("Setting model paused state to " + value, "MODEL");
		paused = value;
		if(!isPaused())
			notifyAll();
	}

	public synchronized boolean isActive() {
		return active;
	}

	public synchronized void stop() {
		lm.log("Stopping model", "MODEL");
		active = false;
	}

	public synchronized void start() {
		lm.log("Starting model", "MODEL");
		active = true;
	}

	public void update() {
		if(!isPaused()) {
			Queue<Command> q = new LinkedList<Command>();
			synchronized(commands) {
				q.addAll(commands);
				commands.clear();
			}
			for(Command cmd: q)
				cmd.execute();
			//mainPlayerManager.update();
			spawner.update();
			EntityManagerCollection.getInstance().update();
			
		}
	}

	public void queueCommand(Command cmd) {
		if(!isPaused()) {
			synchronized(commands) {
				commands.add(cmd);
			}
		}
		else
			LogManager.getInstance().log("Paused, refusing command", "MODEL", Message.Type.ERROR);
	}
	
	public Map getMap() {
		return map;
	}
	
	public void accept(Visitor visitor) {
		visitor.visitModel(this);
		map.accept(visitor);
	}
}

/*

	private Map.Matrix snapshot;

	private final Entity avatar;
	private Map map;
	private final Location avatarStart;
	private boolean paused;

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

	public Entity getAvatar() {
		return this.avatar;
	}

	public void moveAvatarRequest(Location l) {
		if(this.avatar.canMove())
			avatar.move(l);
	}

	public void endGame() {
		Thread.currentThread().interrupt();
	}

	private void respawn(int numOfLives) {
		Location from = avatar.getTile().getLocation();
		Location to = avatarStart;
		avatar.resetStats(numOfLives); //reset all stats, except num of lives - that shouldn't be reset!
		avatar.unequipAll();
		avatar.move(to.subtract(from));
	}
 */