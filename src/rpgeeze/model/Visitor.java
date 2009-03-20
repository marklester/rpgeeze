package rpgeeze.model;

import rpgeeze.model.ae.AreaEffect;
import rpgeeze.model.decal.Decal;
import rpgeeze.model.item.Item;
import rpgeeze.model.terrain.Terrain;

public interface Visitor {
	public void visitTile(Tile tile);
	public void visitTerrain(Terrain terrain);
	public void visitAreaEffect(AreaEffect areaEffect);
	public void visitDecal(Decal decal);
	public void visitItem(Item item);
	public void visitEntity(Entity entity);
}
