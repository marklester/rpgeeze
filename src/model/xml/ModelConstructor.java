package model.xml;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import model.Location;
import model.Map;
import model.Model;
import model.Terrain;
import model.Tile;
import model.ae.AreaEffect;
import model.decal.Decal;
import model.entity.Entity;
import model.entity.Occupation;
import model.item.Item;

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
		model.entity.PC avatar = new model.entity.PC(occ,map);
		avatar.setTile(map.getTile(0,0));
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
}

