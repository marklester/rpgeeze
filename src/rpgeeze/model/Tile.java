package rpgeeze.model;

import rpgeeze.model.ae.AreaEffect;
import rpgeeze.model.decal.Decal;
import rpgeeze.model.item.Item;
import rpgeeze.model.terrain.Terrain;

public class Tile implements Visitable {
	private Terrain terrain;
	private Entity entity;
	private Item item;
	private AreaEffect areaEffect;
	private Decal decal;
	
	private int x, y;
	
	public Tile(Terrain terrain, int x, int y) {
		this.terrain = terrain;
		this.x = x;
		this.y = y;
	}
	
	public Terrain getTerrain() {
		return terrain;
	}
	
	public void setTerrain(Terrain newTerrain) {
		this.terrain = newTerrain;
	}
	
	public Entity getEntity() {
		return entity;
	}
	
	public void setEntity(Entity entity) {
		if(this.entity != null && entity != null) {
			throw new IllegalMoveException("Destination tile already occupied");
		}
		else if(!this.terrain.isPassable(entity)) {
			throw new IllegalMoveException("Entity may not traverse destination terrain");
		}
		if(item != null)
			item.activate(entity, this);
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

	public void setDecal(Decal newDecal) {
		decal = newDecal;
	}
	
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
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
