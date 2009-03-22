package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.entity.*;
import model.entity.Occupation;
import model.xml.GameVisitor;
import model.xml.ModelElement;
import util.Observer;

public class Model extends util.Subject implements ModelElement{
	private final static Pattern pattern = Pattern.compile("<model>(<map>.*</map>)(<entity>.*</entity>)</model>");

	protected final Queue<Command> commands = new LinkedList<Command>();
	protected final List<EntityEventManager> entities = new LinkedList<EntityEventManager>();
	
	private Map.Matrix snapshot;

	private PC avatar;
	private HumanPlayerEntityManager mainPlayerManager;
	
	private Map map;
	private final Location avatarStart;
	private boolean paused;

	public Model(Map map, PC mainCharacter) {
		this.map = map;
		this.avatar = mainCharacter;
		mainPlayerManager = new HumanPlayerEntityManager(mainCharacter);
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
				tempQ.poll().execute(this);

			//this.avatar.update();
			for(EntityEventManager eem : entities)
				eem.update();
			this.snapshot = this.map.getMatrix().clone();
			updateObservers();
	
			// update map
			// update items
			// apply AoE's
			// update NPC's
			// update entity
			// -Jose
		}
		else
			commands.clear();
	}
	
	public Map.Matrix publishState() {
		return this.snapshot;
	}

	public PC getAvatar() {
		return this.avatar;
	}

	public void moveAvatarRequest(Location loc) {
		mainPlayerManager.move(loc);
	}
	
	public boolean isPaused() {
		return paused;
	}	
	public void pause() {
		paused = true;
	}
	public void unPause() {
		paused = false;
	}	
	public void endGame() {
		Thread.currentThread().interrupt();
	}
	
	
//	public String toXml() {
//		return toXml("");
//	}
	
//	public String toXml(String indent) {
//		StringBuilder sb = new StringBuilder();
//		sb.append(indent + "<model>\n");
//		sb.append(map.toXml(indent + "\t") + "\n");
//		sb.append(avatar.toXml(indent + "\t") + "\n");
//		sb.append(indent + "</model>");
//		return sb.toString();
//	}
//	
	public static Model fromXml(Occupation occ, String xml) {
		Matcher mat = pattern.matcher(xml);
		if(!mat.matches())
			throw new RuntimeException("Bad XML for Model");
		Map map = Map.fromXml(mat.group(1));
		PC avatar = PC.fromXml(occ, map, mat.group(2));
		return new Model(map, avatar);
	}

	@Override
	public void accept(GameVisitor visitor) {
		visitor.visit(map);
		//Visit Entities here
		//foreach(entityhandler in entity handlerset)
		//visitor.visis(entityhandler)
	}
}