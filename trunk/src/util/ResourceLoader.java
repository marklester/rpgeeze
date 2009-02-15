package util;

/**
 * Centralized access point for resource files.
 */

import java.util.Hashtable;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class ResourceLoader {
	private final ClassLoader loader;
	private final Hashtable<String, Image> images;
	private final Hashtable<String, String>items;
	private static ResourceLoader instance = null;
	
	/**
	 * Constructs a new ResourceLoader. Since this is a singleton, this is
	 * only called once, from within a static method.
	 */
	private ResourceLoader() {
		this.loader = getClass().getClassLoader();
		this.images = new Hashtable<String, Image>();
		this.items = new Hashtable<String,String>(); //Modify this to set Item Images
		items.put("Boulder", "img/terrain20px/Boulder.png");
		items.put("Cross Bow", "img/crossbow.png");
		items.put("Sword", "img/sword.png");
		items.put("Potion Life", "img/potionlife.png");
		
	}
	
	/**
	 * Returns the singleton instance of this class.
	 */
	public static ResourceLoader getInstance() {
		if(instance == null)
			instance = new ResourceLoader();
		return instance;
	}
	
	/**
	 * Gives you the Image corresponding to the specified key. Currently the
	 * path is used as the key. When I think of a nicer way to do this, I'll do
	 * it. 
	 */
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
		return getImage(items.get(key));
	}
	
	/**
	 * Gives you an InputStream corresponding to the specified key. Currently
	 * the path is used as the key. When I think of a nicer way to do this,
	 * I'll do it.
	 */
	public InputStream getStream(String key) {
		InputStream ret = loader.getResourceAsStream("res/" + key);
		return ret;
	}
}
