package util;

/**
 * Centralized access point for resource files.
 */

import java.util.Hashtable;
import java.awt.Image;
import javax.imageio.ImageIO;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.Scanner;

public class ResourceLoader {
	private final ClassLoader loader;
	private final Hashtable<String, Image> images;
	private final Hashtable<String, String> files;
	private final Hashtable<String, String> audios;
	private static ResourceLoader instance = null;
	
	
	
	/**
	 * Constructs a new ResourceLoader. Since this is a singleton, this is
	 * only called once, from within a static method.
	 * 
	 * To add a new "thing" to the game, such as a decal, item, or terrain follow these steps:
	 * 1. Create the images and store in res/img/.../foo.png folder
	 * 2. Create a new class called Foo, and be sure to set it's name in its constructor 
	 * 		-(e.g. Super("Foo Name") for an item, decal, or terrain)
	 * 3. Add to the files hashtable below - e.g. files.put("Foo Name", "img/foo.png")
	 * 
	 */
	private ResourceLoader() {
		this.loader = getClass().getClassLoader();
		this.images = new Hashtable<String, Image>();
		this.files = new Hashtable<String,String>(); //Modify this to set Item Images
		this.audios = new Hashtable<String,String>();
		files.put("Boulder", "img/terrain20px/Boulder.png");
		files.put("Crossbow", "img/crossbow.png");
		files.put("Sword", "img/sword.png");
		files.put("Potion Life", "img/potionlife.png");
		files.put("Red Armor", "img/redarmor.png");
		files.put("Boots", "img/boots.png");
		files.put("Skull and Crossbones","img/skullandcrossbones.png");
		files.put("Arrows", "img/arrows.png");
		files.put("Shield", "img/shield.png");
		files.put("Mana", "img/mana.png");
		files.put("Health Pack","img/healthpack20px.png");
		files.put("Portal Item","img/portal20px.png");
		files.put("Helmet","img/helmet.png");
		files.put("Gold Star","img/goldenstar.png");
		files.put("Red Cross","img/redcross.png");
		files.put("Fire Decal","img/fire.png");
		files.put("Grass Terrain","img/terrain20px/GrassTerrain.png");
		files.put("Mountain Terrain","img/terrain20px/MountainTerrain.png");
		files.put("Water Terrain","img/terrain20px/WaterTerrain.png");
		
		audios.put("Instant Death", "audio/evilLaugh.wav");
		audios.put("Portal Item", "audio/elevator.wav");
		
		images.put("Intro Image", getImage("img/IntroOccupationTypes.png"));
		images.put("New Image", getImage("img/buttons/NewGame.png"));
		images.put("Load Image", getImage("img/buttons/LoadGame.png"));
		images.put("Quit Image", getImage("img/buttons/QuitGame.png"));
		images.put("Smasher", getImage("img/buttons/Smasher.png"));
		images.put("Summoner", getImage("img/buttons/Summoner.png"));
		images.put("Sneak", getImage("img/buttons/Sneak.png"));
	}
		
	/**
	 * Returns the singleton instance of this class.
	 */
	public static ResourceLoader getInstance() {
		if(instance == null)
			instance = new ResourceLoader();
		return instance;
	}
	

	public Image getImage(String key) {
		Image ret = images.get(key);
		if(ret == null) {
			try {
				ret = ImageIO.read(getStream(key));
			}
			catch(IOException e) {
			}
			images.put(key, ret);
		}
		return ret;
	}

	public Image getItemImage(String key){
		return getImage(files.get(key));
	}
	

	public void playAudioClip(String key) {
		try {
			InputStream in = getStream(audios.get(key)); 
			AudioStream as = new AudioStream(in);
			AudioPlayer.player.start(as);
		}
		catch (Exception e) { System.out.println(e); }
	}
	
	/**
	 * Gives you an InputStream corresponding to the specified key. Currently
	 * the path is used as the key. When I think of a nicer way to do this,
	 * I'll do it.
	 */
	public InputStream getStream(String key) {
		try {
			InputStream ret = loader.getResourceAsStream("res/" + key);
			return ret;
		}
		catch (Exception e) {System.out.println(e);}
		return null;
	}

	public Scanner getScanner(String key) {
		return new Scanner(getStream(key));
	}
	
	
	//Used by Drawer to get Terrain width
	public int getTerrainWidth() {
		try {
			return images.get("Grass Terrain").getWidth(null);
		}
		catch (Exception e) { return 20; }
	}
	
	//Used by Drawer to get Terrain height
	public int getTerrainHeight() {
		try {
			return images.get("Grass Terrain").getHeight(null);
		}
		catch (Exception e) { return 20; }
	}
}
