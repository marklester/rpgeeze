package rpgeeze.model;

import rpgeeze.model.ae.AreaEffect;
import rpgeeze.model.decal.Decal;
import rpgeeze.model.item.Item;
import rpgeeze.model.terrain.Terrain;
import rpgeeze.view.Drawable;

public class Tile implements Drawable {
	private Terrain terrain;
	private Entity entity;
	private Item item;
	private AreaEffect areaEffect;
	private Decal decal;
	
	public Tile(Terrain terrain) {
		this.terrain = terrain;
	}
	
	public Terrain getTerrain() {
		return terrain;
	}
	
	public Entity getEntity() {
		return entity;
	}
	
	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item newItem) {
		item = newItem;
	}

	public AreaEffect getAreaEffect() {
		return areaEffect;
	}

	public void setAreaEffect(AreaEffect newAreaEffect) {
		areaEffect = newAreaEffect;
	}

	public Decal getDecal() {
		return decal;
	}

	public void setDecal(Decal newDecal) {
		decal = newDecal;
	}
}
