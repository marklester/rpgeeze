package rpgeeze.view;

import java.util.HashMap;

import javax.media.opengl.GL;

import com.sun.opengl.util.texture.Texture;

import rpgeeze.GameProperties;
import rpgeeze.dp.Iterator;
import rpgeeze.gl.geom.TexturedRectangle;
import rpgeeze.log.LogManager;
import rpgeeze.model.Map;
import rpgeeze.model.Model;
import rpgeeze.model.Tile;
import rpgeeze.model.Visitor;
import rpgeeze.model.ae.AreaEffect;
import rpgeeze.model.decal.Decal;
import rpgeeze.model.item.Item;
import rpgeeze.model.entity.Occupation;
import rpgeeze.model.entity.Smasher;
import rpgeeze.model.entity.Sneak;
import rpgeeze.model.entity.Summoner;
import rpgeeze.model.skill.Skill;
import rpgeeze.model.terrain.Terrain;
import rpgeeze.model.entity.monster.*;
import rpgeeze.model.entity.*;
import rpgeeze.model.entity.merchant.*;
import rpgeeze.model.entity.villager.*;
import rpgeeze.util.ContinuousIteratorWithElements;
import rpgeeze.util.Direction;
import rpgeeze.util.MultiplyIterator;
import rpgeeze.util.Pair;
import rpgeeze.util.ResourceLoader;

public class MapDrawer implements Visitor {
	private GL gl;
	private double size;

	private HashMap<String, Texture> terrains = new HashMap<String, Texture>();
	private HashMap<String, Texture> items = new HashMap<String, Texture>();
	private HashMap<String, Texture> decals = new HashMap<String, Texture>();
	private HashMap<String, Texture> skills = new HashMap<String, Texture>();
	
	private static HashMap<Pair<String, Direction>, Iterator<String>> avatar = new HashMap<Pair<String, Direction>, Iterator<String>>();
	private static HashMap<Pair<String, Direction>, Iterator<String>> mobs = new HashMap<Pair<String, Direction>, Iterator<String>>();
	private static HashMap<Pair<String, Direction>, Iterator<String>> vehicle = new HashMap<Pair<String, Direction>, Iterator<String>>();
	
	private final static int SLOW_DOWN_FACTOR = 10;

	static{
		for(Occupation occ: new Occupation[] {new Smasher(), new Summoner(), new Sneak()}) {
	        String s = occ.getName().toLowerCase();
	        
	        avatar.put(new Pair<String, Direction>(occ.getName(), Direction.NORTH), new MultiplyIterator<String>(new ContinuousIteratorWithElements<String>(
	                "entity/" + s + "/" + s + "WalkNorth1.png",
	                "entity/" + s + "/" + s  + "WalkNorth2.png"
	        ), SLOW_DOWN_FACTOR));

	        avatar.put(new Pair<String, Direction>(occ.getName(), Direction.SOUTH), new MultiplyIterator<String>(new ContinuousIteratorWithElements<String>(
	                "entity/" + s + "/" + s  + "WalkSouth1.png",
	                "entity/" + s + "/" + s +  "WalkSouth2.png"
	        ), SLOW_DOWN_FACTOR));
	        
	        avatar.put(new Pair<String, Direction>(occ.getName(), Direction.EAST), new MultiplyIterator<String>(new ContinuousIteratorWithElements<String>(
	                "entity/" + s + "/" + s + "WalkEast1.png",
	                "entity/" + s + "/" + s + "WalkEast2.png"
	        ), SLOW_DOWN_FACTOR));

	        avatar.put(new Pair<String, Direction>(occ.getName(), Direction.WEST), new MultiplyIterator<String>(new ContinuousIteratorWithElements<String>(
	                "entity/" + s + "/" + s  + "WalkWest1.png",
	                "entity/" + s + "/" + s  + "WalkWest2.png"
	        ), SLOW_DOWN_FACTOR));

	        avatar.put(new Pair<String, Direction>(occ.getName(), Direction.NORTHEAST), avatar.get(new Pair<String, Direction>(occ.getName(), Direction.NORTH)));
	        avatar.put(new Pair<String, Direction>(occ.getName(), Direction.NORTHWEST), avatar.get(new Pair<String, Direction>(occ.getName(), Direction.NORTH)));
	        avatar.put(new Pair<String, Direction>(occ.getName(), Direction.SOUTHEAST), avatar.get(new Pair<String, Direction>(occ.getName(), Direction.SOUTH)));
	        avatar.put(new Pair<String, Direction>(occ.getName(), Direction.SOUTHWEST), avatar.get(new Pair<String, Direction>(occ.getName(), Direction.SOUTH)));
		}
		
		for(Monster mons: new Monster[] {new Soldier(), new Skeleton(), new Rat()}) {
	        String s = mons.toString().toLowerCase();
	        
	        mobs.put(new Pair<String, Direction>(mons.toString(), Direction.NORTH), new MultiplyIterator<String>(new ContinuousIteratorWithElements<String>(
	                "entity/" + s + "/" + s + "WalkNorth1.png",
	                "entity/" + s + "/" + s  + "WalkNorth2.png"
	        ), SLOW_DOWN_FACTOR));

	        mobs.put(new Pair<String, Direction>(mons.toString(), Direction.SOUTH), new MultiplyIterator<String>(new ContinuousIteratorWithElements<String>(
	                "entity/" + s + "/" + s  + "WalkSouth1.png",
	                "entity/" + s + "/" + s +  "WalkSouth2.png"
	        ), SLOW_DOWN_FACTOR));
	        
	        mobs.put(new Pair<String, Direction>(mons.toString(), Direction.EAST), new MultiplyIterator<String>(new ContinuousIteratorWithElements<String>(
	                "entity/" + s + "/" + s + "WalkEast1.png",
	                "entity/" + s + "/" + s + "WalkEast2.png"
	        ), SLOW_DOWN_FACTOR));

	        mobs.put(new Pair<String, Direction>(mons.toString(), Direction.WEST), new MultiplyIterator<String>(new ContinuousIteratorWithElements<String>(
	                "entity/" + s + "/" + s  + "WalkWest1.png",
	                "entity/" + s + "/" + s  + "WalkWest2.png"
	        ), SLOW_DOWN_FACTOR));

//	        avatar.put(new Pair<String, Direction>(mons.toString(), Direction.NORTHEAST), avatar.get(new Pair<String, Direction>(mons.getName(), Direction.NORTH)));
//	        avatar.put(new Pair<String, Direction>(mons.toString(), Direction.NORTHWEST), avatar.get(new Pair<String, Direction>(mons.getName(), Direction.NORTH)));
//	        avatar.put(new Pair<String, Direction>(mons.toString(), Direction.SOUTHEAST), avatar.get(new Pair<String, Direction>(mons.getName(), Direction.SOUTH)));
//	        avatar.put(new Pair<String, Direction>(mons.toString(), Direction.SOUTHWEST), avatar.get(new Pair<String, Direction>(mons.getName(), Direction.SOUTH)));
		}
		
		
		
	        String s = "vehicle";
	        
	        vehicle.put(new Pair<String, Direction>(s, Direction.NORTH), new MultiplyIterator<String>(new ContinuousIteratorWithElements<String>(
	                "entity/vehicle/vehicleNorth1.png",
	                "entity/vehicle/vehicleNorth2.png"
	        ), SLOW_DOWN_FACTOR));

	        vehicle.put(new Pair<String, Direction>(s, Direction.SOUTH), new MultiplyIterator<String>(new ContinuousIteratorWithElements<String>(
	                "entity/vehicle/vehicleSouth1.png",
	                "entity/vehicle/vehicleSouth2.png"
	        ), SLOW_DOWN_FACTOR));
	        
	        vehicle.put(new Pair<String, Direction>(s, Direction.EAST), new MultiplyIterator<String>(new ContinuousIteratorWithElements<String>(
	                "entity/vehicle/vehicleEast1.png",
	                "entity/vehicle/vehicleEast2.png"
	        ), SLOW_DOWN_FACTOR));

	        vehicle.put(new Pair<String, Direction>(s, Direction.WEST), new MultiplyIterator<String>(new ContinuousIteratorWithElements<String>(
	                "entity/vehicle/vehicleWest1.png",
	                "entity/vehicle/vehicleWest1.png"
	        ), SLOW_DOWN_FACTOR));

	        vehicle.put(new Pair<String, Direction>(s, Direction.NORTHEAST), vehicle.get(new Pair<String, Direction>(s, Direction.NORTH)));
	        vehicle.put(new Pair<String, Direction>(s, Direction.NORTHWEST), vehicle.get(new Pair<String, Direction>(s, Direction.NORTH)));
	        vehicle.put(new Pair<String, Direction>(s, Direction.SOUTHEAST), vehicle.get(new Pair<String, Direction>(s, Direction.SOUTH)));
	        vehicle.put(new Pair<String, Direction>(s, Direction.SOUTHWEST), vehicle.get(new Pair<String, Direction>(s, Direction.SOUTH)));
		}
	
	public void setSize(double size) {
		this.size = size;
	}
	
	public void setGL(GL gl) {
		this.gl = gl;
	}
	
	public void visitAreaEffect(AreaEffect area_effect) {
	}

	public void visitDecal(Decal decal) {
		Texture texture = decals.get(decal.getName());
		if(texture == null) {
			String key = "img.decal." + decal.getName().toLowerCase().replaceAll(" ", "_");
			String imgKey = GameProperties.getInstance().getProperty(key);
			texture = ResourceLoader.getInstance().getTexture(imgKey);
			decals.put(decal.getName(), texture);
		}
		new TexturedRectangle(texture, size, size).render(gl);
	}

	public void visitEntity(PC entity) {
		Iterator<String> iter = avatar.get(new Pair<String, Direction>(entity.getOccupation().getName(), entity.getFacingDirection()));
		//System.out.println(iter);
		new TexturedRectangle(ResourceLoader.getInstance().getTexture(iter.current()), size, size).render(gl);
		iter.advance();	
	}
	
	public void visitEntity(Monster entity)
	{
		Iterator<String> iter = mobs.get(new Pair<String, Direction>(entity.toString(), entity.getFacingDirection()));
		//System.out.println(iter);
		new TexturedRectangle(ResourceLoader.getInstance().getTexture(iter.current()), size, size).render(gl);
		iter.advance();	
	}
	
	public void visitEntity(Merchant entity)
	{
		
	}
	
	public void visitEntity(Villager entity)
	{
		
	}
	
	public void visitEntity(Vehicle entity){
		Iterator<String> iter = vehicle.get(new Pair<String, Direction>("vehicle", entity.getFacingDirection()));
		new TexturedRectangle(ResourceLoader.getInstance().getTexture(iter.current()), size, size).render(gl);
		iter.advance();	
	}
	
	public Texture textureForItem(Item item) {
		if(item == null)
			return null;
		Texture texture = items.get(item.getName());
		if(texture == null) {
			String key = "img.item." + item.getName().toLowerCase().replaceAll(" ", "_");
			System.out.println(key);
			String imgKey = GameProperties.getInstance().getProperty(key);
			texture = ResourceLoader.getInstance().getTexture(imgKey);
			items.put(item.getName(), texture);
		}
		return texture;
	}
	
	public void visitItem(Item item) {
		new TexturedRectangle(textureForItem(item), size, size).render(gl);
	}

	public void visitMap(Map map) {
	}

	public void visitModel(Model model) {
	}

	public void visitTerrain(Terrain terrain) {
//		LogManager.getInstance().log("Drawing terrain", "VIEW");
		Texture texture = terrains.get(terrain.getName());
		if(texture == null) {
			String key = "img.terrain." + terrain.getName().toLowerCase().replaceAll(" .*$", "");
			String imgKey = GameProperties.getInstance().getProperty(key);
			texture = ResourceLoader.getInstance().getTexture(imgKey);
			terrains.put(terrain.getName(), texture);
		}
		new TexturedRectangle(texture, size, size).render(gl);
	}

	public void visitTile(Tile tile) {
	}

	public Texture textureForSkill(Skill s) {
		if(s == null)
			return null;
		Texture texture = skills.get(s.toString());
		if(texture == null) {
			String key = "img.skill." + s.toString().toLowerCase().replaceAll("[ \\-]", "_");
			System.out.println(key);
			String imgKey = GameProperties.getInstance().getProperty(key);
			texture = ResourceLoader.getInstance().getTexture(imgKey);
			skills.put(s.toString(), texture);
		}
		return texture;
	}
}
