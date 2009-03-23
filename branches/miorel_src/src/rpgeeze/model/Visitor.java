package rpgeeze.model;

import rpgeeze.model.ae.AreaEffect;
import rpgeeze.model.decal.Decal;
import rpgeeze.model.entity.PC;
import rpgeeze.model.entity.Vehicle;
import rpgeeze.model.entity.monster.*;
import rpgeeze.model.entity.merchant.*;
import rpgeeze.model.entity.villager.*;
import rpgeeze.model.item.Item;
import rpgeeze.model.terrain.Terrain;

public interface Visitor {
	public void visitModel(Model model);
	public void visitMap(Map map);
	public void visitTile(Tile tile);
	public void visitTerrain(Terrain terrain);
//	public void visitLocation(Location location);
	public void visitAreaEffect(AreaEffect area_effect);
	public void visitItem(Item item);
	public void visitDecal(Decal decal);
	public void visitEntity(Merchant entity);
	public void visitEntity(PC entity);
	public void visitEntity(Monster entity);
	public void visitEntity(Villager entity);
	//public void visitEntity(Entity entity);
	public void visitEntity(Vehicle vehicle);
}
/*
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
*/