package rpgeeze.model;

import rpgeeze.math.Vector;
import rpgeeze.model.ae.AreaEffect;
import rpgeeze.model.decal.Decal;
import rpgeeze.model.item.Item;
import rpgeeze.model.terrain.Terrain;

public abstract class Tile implements Visitable {
	private Terrain terrain;
	private Entity entity;
	private Item item;
	private AreaEffect areaEffect;
	private Decal decal;
	
	public Terrain getTerrain() {
		return terrain;
	}
	
	protected void setTerrain(Terrain newTerrain) {
		this.terrain = newTerrain;
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

	protected void setItem(Item newItem) {
		item = newItem;
	}

	protected AreaEffect getAreaEffect() {
		return areaEffect;
	}

	protected void setAreaEffect(AreaEffect newAreaEffect) {
		areaEffect = newAreaEffect;
	}

	protected void setDecal(Decal newDecal) {
		decal = newDecal;
	}
	
	public abstract Tile getTile(Vector offset);
	
	public void accept(Visitor visitor) {
		visitor.visitTile(this);
		if(terrain != null)
			terrain.accept(visitor);
		if(areaEffect != null)
			areaEffect.accept(visitor);
		if(decal != null)
			decal.accept(visitor);
		if(item != null)
			item.accept(visitor);
		if(entity != null)
			entity.accept(visitor);
	}
}
