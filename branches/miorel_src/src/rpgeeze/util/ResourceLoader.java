package rpgeeze.util;

import java.io.IOException;
import java.io.InputStream;

import java.util.HashMap;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.media.opengl.GL;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;

/**
 * Centralized access point for resource files.
 */

public class ResourceLoader {
	private final ClassLoader loader;
	private final HashMap<String, BufferedImage> images = new HashMap<String, BufferedImage>();
	private final HashMap<String, Texture> textures = new HashMap<String, Texture>();
	
	private static ResourceLoader instance = null;	

	private ResourceLoader() {
		this.loader = getClass().getClassLoader();
	}

	public static ResourceLoader getInstance() {
		if(instance == null)
			instance = new ResourceLoader();
		return instance;
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
		catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public BufferedImage getImage(String key) {
		BufferedImage ret = images.get(key);
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

	public Texture getTexture(String key) {
		Texture ret = textures.get(key);
		if(ret == null) {
			ret = TextureIO.newTexture(getImage(key), true);
			ret.setTexParameteri(GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
			ret.setTexParameteri(GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR_MIPMAP_NEAREST);
			textures.put(key, ret);
		}
		return ret;
	}
}
