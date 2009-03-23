package model.xml;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.Hashtable;

import model.Location;
import model.Map;
import model.Model;
import model.Tile;
import model.Terrain;
import model.ae.AreaEffect;
import model.decal.Decal;
import model.entity2.Entity;
import model.item.Item;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class XMLWriterVisitor implements GameVisitor{	
	private Document doc;
	private Element currentElement;
	public static void main (String args[]) {
        new XMLWriterVisitor();
    }

    public XMLWriterVisitor() {
        try {
            /////////////////////////////
            //Creating an empty XML Document
            //We need a Document
            DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
            doc = docBuilder.newDocument();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

	@Override
	public void visit(Model model) {
		//Create Model Element
		Element modelElement = doc.createElement("model");
		doc.appendChild(modelElement);
		currentElement = modelElement;
		//Visit Things in Model
		model.accept(this);
		
	}

	@Override
	public void visit(Map map) {
		//Store previous element temporarily(should be model)
		Element prevElement = currentElement;
		Element mapElement = doc.createElement("map");
		currentElement.appendChild(mapElement);
		currentElement = mapElement;//Set Map as current element
		map.accept(this); //Go to Everything in Map
		currentElement = prevElement; //Change current element back
	}

	@Override
	public void visit(Tile tile) {
		//Store previous element temporarily(should be map)
		Element prevElement = currentElement;
		Element tileElement = doc.createElement("tile");
		currentElement.appendChild(tileElement);
		currentElement = tileElement;//Set tile as current element
		tile.accept(this); //Go to Everything in tile
		currentElement = prevElement; //Change back to previous item
	}

	@Override
	public void visit(Terrain terrain) {
		Element terrainElement = doc.createElement("terrain");
		Text terrainName = doc.createTextNode(terrain.toString());
		terrainElement.appendChild(terrainName);
		//Append Terrain to Tile
		currentElement.appendChild(terrainElement);
	}

	@Override
	public void visit(Location location) {
		Element locationElement = doc.createElement("location");
		Element x = doc.createElement("x");
		Text xValue = doc.createTextNode(""+location.getX());
		x.appendChild(xValue);
		Element y = doc.createElement("y");
		Text yValue = doc.createTextNode(""+location.getY());
		y.appendChild(yValue);
		locationElement.appendChild(x);
		locationElement.appendChild(y);
		//Append Location to Tile
		currentElement.appendChild(locationElement);
		
	}

	@Override
	public void visit(AreaEffect area_effect) {
		Element aeElement = doc.createElement("ae");
		Element name = doc.createElement("name");
		Text nameValue = doc.createTextNode(""+area_effect.toString());
		name.appendChild(nameValue);
		Element rate = doc.createElement("rate");
		Text rateValue = doc.createTextNode(""+area_effect.getRate());
		rate.appendChild(rateValue);
		aeElement.appendChild(name);
		aeElement.appendChild(rate);
		//Append Area Effect to Tile
		currentElement.appendChild(aeElement);		
	}
	
	@Override
	public void visit(Decal decal){
		Element decalElement = doc.createElement("decal");
		Text nameValue = doc.createTextNode(""+decal.toString());
		decalElement.appendChild(nameValue);
		//Add Decal Element to Tile
		currentElement.appendChild(decalElement);	
	}
	
	@Override
	public void visit(Item item) {
		Element itemElement = doc.createElement("item");
		Element name = doc.createElement("type");
		Text nameValue = doc.createTextNode(""+item.toString());
		name.appendChild(nameValue);
		itemElement.appendChild(name);
		
		//Save Item Properties
		Hashtable<String,String> props = item.getProperties();
		Enumeration<String> keys = props.keys();
		while(keys.hasMoreElements()){
			String key = keys.nextElement();
			Element p = doc.createElement("property");
			p.setAttribute("name",key);
			p.setAttribute("value",  props.get(key));
			itemElement.appendChild(p);
		}
		//Append Item Effect to Tile
		currentElement.appendChild(itemElement);
	}

	
	@Override
	public void visit(Entity entity) {
		// TODO Auto-generated method stub
		
	}
	public String toString(){
		 /////////////////
        //Output the XML
		String xmlString ="";
		try{
	        //set up a transformer
	        TransformerFactory transfac = TransformerFactory.newInstance();
	        Transformer trans = transfac.newTransformer();
	        trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	        trans.setOutputProperty(OutputKeys.INDENT, "yes");
	        //create string from xml tree
	        StringWriter sw = new StringWriter();
	        StreamResult result = new StreamResult(sw);
	        DOMSource source = new DOMSource(doc);
	        trans.transform(source, result);
	        xmlString = sw.toString();
		}catch(Exception e){
			e.printStackTrace();
		}
        //return xml
        return  xmlString;
	}
}
