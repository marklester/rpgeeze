package model.xml;

import model.*;
import model.Terrain;
import model.ae.AreaEffect;
import model.decal.Decal;
import model.entity.Entity;
import model.item.Item;

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
