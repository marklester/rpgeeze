package rpgeeze.model.xml;

import rpgeeze.model.*;
import rpgeeze.model.terrain.*;
import rpgeeze.model.ae.AreaEffect;
import rpgeeze.model.decal.Decal;
import rpgeeze.model.entity.Entity;
import rpgeeze.model.item.Item;

public interface GameVisitor {
	void visit(Model model);
	void visit(Map map);
	void visit(Tile tile);
	void visit(Terrain terrain);
	void visit(Location location);
	void visit(AreaEffect area_effect);
	void visit(Item item);
	void visit(Decal decal);
	void visit(Entity entity);
}
