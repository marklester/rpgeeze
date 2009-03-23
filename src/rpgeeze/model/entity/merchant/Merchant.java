package rpgeeze.model.entity.merchant;

import rpgeeze.model.Visitor;
import rpgeeze.model.ai.ContainedAI;
import rpgeeze.model.entity.Inventory;
import rpgeeze.model.entity.NPC;
import rpgeeze.model.entity.Stats;
import rpgeeze.model.entity.monster.Monster;

public class Merchant extends NPC{
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visitEntity(this);	
	}

	public Merchant(){
		this.setEntityType("Merchant");		
		this.setAI(new ContainedAI(2));
		this.inventory = new Inventory(0);
		this.stats = new Stats();
		
		setStats(0);
	}
	
	@Override
	public boolean isAlive() {
		return true;
	}

	@Override
	public void update() {
		super.update();
		
	}
		
	private void setStats(int i)
	{
		stats.level = i;
		stats.life = 100 + i * 20;
		stats.defensiveRating = 20 + 12 * i;
		stats.offensiveRating = 20 + 12 * i;
		stats.movement = 10;
	}
	public String toString()
	{
		return "Merchant";
	}
	

}
