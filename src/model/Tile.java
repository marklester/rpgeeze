package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.entity.*;
import model.item.Item;
import model.xml.GameVisitor;
import model.xml.ModelElement;
import model.decal.Decal;
import model.entity.Entity;
import model.ae.AreaEffect;
import view.*;

public class Tile implements Cloneable, ModelElement{
	private static final Pattern pattern = Pattern.compile("<tile>(<terrain>.*</terrain>)(<location>.*</location>)(<decal>.*</decal>|)(<item>.*</item>|)(<ae>.*</ae>|)</tile>");
	
	private final Terrain terrain;
	private Location location;
	private Decal decal;
	private Item item;
	private AreaEffect ae;
	private Entity entity;
	//private Tile[][] adjacentTiles;
	private Map map;

	public Tile(Terrain terrain, Location location, Decal decal, Item item, AreaEffect ae) {
		this.terrain = terrain;
		this.location = location;
		this.decal = decal;
		this.item = item;
		this.ae = ae;
		//this.adjacentTiles = new Tile[3][3];
	}
		
	public Tile(Terrain terrain, Location location, Item item) {
		this(terrain, location, null, item, null);
	}
	
	public Tile(Terrain terrain, Location location) {
		this(terrain, location, null, null, null);
	}

	public Location getLocation() {
		return this.location;
	}
	
	public void setMap(Map map)
	{
		this.map = map;
	}
	
	public Item getItem() {
		return this.item;
	}

	public boolean hasItem() {
		return getItem() != null;
	}
	
	public boolean hasAE() {
		return getAE() != null;
	}

	// package level so that nobody outside Model can mess with this
	public void setItem(Item item) {
		this.item = item;
	}

	public AreaEffect getAE() {
		return this.ae;
	}

	public void setAreaEffect(AreaEffect ae) {
		this.ae = ae;
	}

	public Decal getDecal() {
		return this.decal;
	}

	public void setDecal(Decal decal) {
		this.decal = decal;
	}

	public Terrain getTerrain() {
		return this.terrain;
	}

	public Entity getEntity() {
		return this.entity;
	}
	
	public void removeEntity()
	{
		this.entity = null; 
	}

	public boolean hasEntity() {
		return this.entity != null;
	}

	void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	

	public void draw(Drawer d) {
		this.terrain.draw(d);
		if(this.decal != null)
			this.decal.draw(d);
		if(this.item != null)
			this.item.draw(d);
		if(this.entity != null)
			this.entity.draw(d);
	}

	public void accept(Entity e) {
		if(getTerrain().isPassable(e)) {
			if((hasItem() && getItem().isPassable()) || !hasItem()) {
				if(e.getTile() != null)
					e.getTile().releaseEntity();
				setEntity(e);
				e.setTile(this);
			}
		}
	}

	public void releaseEntity() {
		setEntity(null);
	}

	public Tile clone() {
		Tile tile = new Tile(
			terrain,
			location,
			decal == null ? null: decal.clone(),
			item == null ? null : item.clone(),
			ae == null ? null : ae.clone()
		);
		tile.entity = (entity == null ? null : entity.clone());
		return tile;
	}
	
	public Tile getAbsoluteTile(Location l)
	{
		return map.getTile(l);
	}
	public Tile getAbsoluteTile(int x, int y)
	{
		return map.getTile(x, y);
	}
	
	public Tile getRelativeTile(Location l)
	{
		//return map.getTile(l);
		return getRelativeTile(l.getX(), l.getY());
	}
	public Tile getRelativeTile(int x, int y)
	{
//		if(x > 2 || x < 0 || y > 2 || y < 0)
//			return null;
//		return adjacentTiles[x+1][y+1];
		return map.getTile(location.getX() + x, location.getY() + y);
	}

	public String toString() {
		return "Tile at " + this.location+"["+this.terrain+","+this.decal
		+","+this.item+","+this.ae+"]";
	}
	
	public String toXml() {
		return toXml("");
	}
	
	public void collectItem(PC pc)
	{
		if(item != null) {
			//OneShotItem need to be removed from the Tile
			item.activate(pc);
		}
	}
	
	public String toXml(String indent) {
		StringBuilder sb = new StringBuilder();		
		sb.append(indent + "<tile>\n");
		sb.append(terrain.toXml(indent + "\t") + "\n");
		sb.append(location.toXml(indent + "\t") + "\n");
		sb.append(decal == null ? "" : (decal.toXml(indent + "\t") + "\n"));
		sb.append(item == null ? "" : (item.toXml(indent + "\t") + "\n"));
		sb.append(ae == null ? "" : (ae.toXml(indent + "\t") + "\n"));
		sb.append(indent + "</tile>");
		return sb.toString();
	}
	
	public static Tile fromXml(String xml) {
		Matcher mat = pattern.matcher(xml);
		if(!mat.matches())
			throw new RuntimeException("Bad XML for Tile");
		Terrain terrain = Terrain.fromXml(mat.group(1));
		Location location = Location.fromXml(mat.group(2));
		Decal decal = mat.group(3).length() == 0 ? null : Decal.fromXml(mat.group(3));
		Item item = mat.group(4).length() == 0 ? null : Item.fromXml(mat.group(4));
		AreaEffect ae = mat.group(5).length() == 0 ? null : AreaEffect.fromXml(mat.group(5));
		return new Tile(terrain, location, decal, item, ae);
	}

	@Override
	//Visitor Patter Operation For Saving and Reading,etc
	public void accept(GameVisitor visitor) {
		
		visitor.visit(terrain);
		visitor.visit(location);
		if(decal != null)visitor.visit(decal);
		if(item != null)visitor.visit(item);
		if(ae != null)visitor.visit(ae);
		
	}
}
