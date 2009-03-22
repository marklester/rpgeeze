package rpgeeze.view;

import java.util.HashMap;

import javax.media.opengl.GL;

import com.sun.opengl.util.texture.Texture;

import rpgeeze.GameProperties;
import rpgeeze.gl.geom.TexturedRectangle;
import rpgeeze.log.LogManager;
import rpgeeze.model.Entity;
import rpgeeze.model.Map;
import rpgeeze.model.Model;
import rpgeeze.model.Tile;
import rpgeeze.model.Visitor;
import rpgeeze.model.ae.AreaEffect;
import rpgeeze.model.decal.Decal;
import rpgeeze.model.item.Item;
import rpgeeze.model.terrain.Terrain;
import rpgeeze.util.ResourceLoader;

public class Drawer implements Visitor {
	private GL gl;
	private double size;

	private HashMap<String, Texture> terrains = new HashMap<String, Texture>();
	
	public void setSize(double size) {
		this.size = size;
	}
	
	public void setGL(GL gl) {
		this.gl = gl;
	}
	
	public void visitAreaEffect(AreaEffect area_effect) {
	}

	public void visitDecal(Decal decal) {
	}

	public void visitEntity(Entity entity) {
//		LogManager.getInstance().log("Drawing entity", "VIEW");
		new TexturedRectangle(ResourceLoader.getInstance().getTexture("entity/entity.png"), size, size).render(gl);
	}

	public void visitItem(Item item) {
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
}
