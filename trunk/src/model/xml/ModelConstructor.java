package model.xml;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import model.*;
import model.ae.AreaEffect;
import model.decal.Decal;
import model.entity2.*;
import model.item.Item;
import model.skill.Skill;
import model.skill.SkillContainer;

import org.w3c.dom.*;

import javax.xml.parsers.*;

public class ModelConstructor{	
	private Document doc;
	
    public ModelConstructor(InputStream is) {
        try {
            /////////////////////////////
            //Creating an empty XML Document
            //We need a Document
            DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
            doc = docBuilder.parse(is);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

	
	public Model createModel(Occupation occ) {//new Game
		Map map =  createMaps();
		PC avatar = createPC(doc.getElementsByTagName("entity").item(0),map);
		avatar.setOccupation(occ);
		return new Model(map,avatar);
		//List<EntityHandler> ehs= createEntityHandlers();
		//return new Model(aassdwmaps,ehs);
	}
	
	public Map createMaps() {
		NodeList mapNodes = doc.getElementsByTagName("map");
		ArrayList<Map> maps = new ArrayList<Map>();
		for(int i=0;i<mapNodes.getLength();i++){//In case there are more than one map will implement
			int count =0;
			Element map = (Element)mapNodes.item(i);
			NodeList tilesNodes  = map.getElementsByTagName("tile");
			int maxX = 0;
			int maxY = 0;
			List<Tile> tiles = new ArrayList<Tile>();
			for(int j=0;j<tilesNodes.getLength();j++){
				Tile tile = createTile(tilesNodes.item(j));
				tiles.add(tile);
				maxX = Math.max(maxX, tile.getLocation().getX());
				maxY = Math.max(maxY, tile.getLocation().getY());
				count++;
			}
			Tile[][] matrix = new Tile[maxY + 1][maxX + 1];
			for(Tile tile: tiles) {
				Location loc = tile.getLocation();
				matrix[loc.getY()][loc.getX()] = tile;
			}
			maps.add(Map.fromTiles(matrix));
			System.out.println(count);
		}
		return maps.get(0);
	}

	private Tile createTile(Node tileNode){
		Element tile = (Element)tileNode;
		Location location = createLocation(tile.getElementsByTagName("location").item(0));
		Terrain terrain = createTerrain(tile.getElementsByTagName("terrain").item(0));
		Decal decal = createDecal(tile.getElementsByTagName("decal").item(0));
		Item item = createItem(tile.getElementsByTagName("item").item(0));
		AreaEffect ae =createAreaEffect(tile.getElementsByTagName("ae").item(0));
		return new Tile(terrain,location,decal,item,ae);
	}
	private Location createLocation(Node locationNode){
		Element location = (Element)locationNode;
		
		String xs = location.getElementsByTagName("x").item(0).getTextContent();
		String ys = location.getElementsByTagName("y").item(0).getTextContent();
		//System.out.println("("+xs+","+ys+")");
		int x=0;
		int y=0;
		if(xs!=null)x = Integer.parseInt(xs);
		if(ys!=null)y = Integer.parseInt(ys);
		return new Location(x,y);
	}
	private Terrain createTerrain(Node terrainNode){
		return Terrain.getTerrain(terrainNode.getTextContent());
	}
	private Decal createDecal(Node decal){
		try{
			return Decal.getDecal(decal.getTextContent());
		}catch(NullPointerException e){
			return null;
		}
	}
	
	private Item createItem(Node itemNode){
		try{
			Element item = (Element)itemNode;
			String type = item.getElementsByTagName("type").item(0).getTextContent();
			NodeList properties = item.getElementsByTagName("property");
			Hashtable<String,String> ht= new Hashtable<String,String>();
			for(int i=0;i<properties.getLength();i++){
				Element p = (Element)properties.item(i);
				System.out.println(p.getAttribute("location"));
				ht.put(p.getAttribute("name"),p.getAttribute("value"));
			}
			Item i = Item.getItem(type);
			i.setProperties(ht);
			return i;
		}catch(NullPointerException e){
			return null;
		}
	}
	private AreaEffect createAreaEffect(Node aeNode){
		try{
			Element ae = (Element)aeNode;
			String name = ae.getElementsByTagName("name").item(0).getTextContent();
			int rate = Integer.parseInt(ae.getElementsByTagName("rate").item(0).getTextContent());
			AreaEffect area_effect= AreaEffect.getAreaEffect(name);
			area_effect.setRate(rate);
			return area_effect;
		}catch(NullPointerException e){
			return null;
		}
	}
	private PC createPC(Node pcNode,Map map){
		Element pc = (Element)pcNode;
		String type = pc.getAttribute("entityType");
		PC pchar= (PC)Entity.getEntityPrototype(type);
		Inventory inv = createInventory(pc.getElementsByTagName("inventory").item(0));
		Occupation occ = createOccupation(pc.getElementsByTagName("occupation").item(0));
		Stats stats =createStats(pc.getElementsByTagName("stats").item(0));
		SkillContainer skills = createSkillContainer(pc.getElementsByTagName("skillContainer").item(0));
		Equipment equip = createEquipment(pc.getElementsByTagName("equipment").item(0));
		Location location = createLocation(pc.getElementsByTagName("location").item(0));
		Direction dir = createFacingDirection(pc.getElementsByTagName("facing").item(0));
		
		pchar.setInventory(inv);
		pchar.setOccupation(occ);
		pchar.setStats(stats);
		pchar.setSkills(skills);
		pchar.setEquipment(equip);
		//This may cause problem
		pchar.setTile(map.getTile(location));
		pchar.setFacingDirection(dir);
		return pchar;
	}
	private Direction createFacingDirection(Node fnode){
		Element el = (Element)fnode;
		Location loc = createLocation(el.getElementsByTagName("location").item(0));
		return loc.closestDirection();
	}
	private Inventory createInventory(Node invNode){
		Element inv = (Element)invNode;
		NodeList items = inv.getElementsByTagName("item");
		Inventory inventory = new Inventory();
		for(int i =0;i<items.getLength();i++){
			inventory.addItemSilently(createItem(items.item(i)));
		}
		return inventory;
	}
	private Occupation createOccupation(Node occNode){
		try{
			return Occupation.getOccupationPrototype(occNode.getTextContent());
		}catch(NullPointerException e){
			return null;
		}
	}
	private Stats createStats(Node statsNode){
		Element stat = (Element)statsNode;
		try{
			int level = Integer.parseInt(stat.getElementsByTagName("level").item(0).getTextContent());
			int life = Integer.parseInt(stat.getElementsByTagName("life").item(0).getTextContent());
			int mana = Integer.parseInt(stat.getElementsByTagName("mana").item(0).getTextContent());
			int movement = Integer.parseInt(stat.getElementsByTagName("movement").item(0).getTextContent());
			PrimaryStats pstats = createPrimaryStats(stat.getElementsByTagName("primaryStats").item(0));
			Stats stats = new Stats(level,life,mana,movement,pstats);
			return stats;
		}catch(NullPointerException e){
			return null;
		}
	}
	private PrimaryStats createPrimaryStats(Node pstatsNode){
		Element stat = (Element)pstatsNode;
		try{
			int livesLeft = Integer.parseInt(stat.getElementsByTagName("livesLeft").item(0).getTextContent());
			int strength = Integer.parseInt(stat.getElementsByTagName("strength").item(0).getTextContent());
			int agility = Integer.parseInt(stat.getElementsByTagName("agility").item(0).getTextContent());
			int intellect = Integer.parseInt(stat.getElementsByTagName("intellect").item(0).getTextContent());
			double hardiness = Double.parseDouble(stat.getElementsByTagName("hardiness").item(0).getTextContent());
			int experience = Integer.parseInt(stat.getElementsByTagName("experience").item(0).getTextContent());
			return new PrimaryStats(livesLeft,strength,agility,intellect,hardiness,experience);
		}catch(NullPointerException e){
			return null;
		}
	}
	private SkillContainer createSkillContainer(Node skillsNode){
		Element skillContainer = (Element)skillsNode;
		NodeList skilts = skillContainer.getElementsByTagName("skill");
		SkillContainer scon = new SkillContainer();
		for(int i =0;i<skilts.getLength();i++){
			scon.add(createSkill(skilts.item(i)));
		}
		return scon;
	}
	private Skill createSkill(Node skillNode){
		try{
			Element el = (Element)skillNode;
			String type = el.getElementsByTagName("type").item(0).getTextContent();
			int points = Integer.parseInt(el.getElementsByTagName("points").item(0).getTextContent());
			Skill skill = Skill.getSkillPrototype(type);
			skill.setPoints(points);
			return skill;
		}catch(NullPointerException e){
			return null;
		}
	}
	private Equipment createEquipment(Node eqNode){
		Element el = (Element)eqNode;
		Item head = null;
		head = createItem(el.getElementsByTagName("head").item(0));
		Item armor = createItem(el.getElementsByTagName("armor").item(0));
		Item boot = createItem(el.getElementsByTagName("boot").item(0));
		Item weapon = createItem(el.getElementsByTagName("weapon").item(0));
		Item auxiliary = createItem(el.getElementsByTagName("auxiliary").item(0));
		return new Equipment(head,armor,boot,weapon,auxiliary);
	}
}

