package rpgeeze.model.entity;

import rpgeeze.model.skill.*;
import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.model.*;
//import rpgeeze.model.xml.*;
import rpgeeze.model.item.*;
import rpgeeze.model.entity.Occupation;
import java.util.LinkedList;

public class PC extends Entity { //implements { EquippableInventory, StatsModifiable {
	private LinkedList<Skill> skills;
	private int updateCounter;

	public PC(Occupation occupation, Map map) {
		super(occupation);
		this.skills = occupation.getSkills();
	}
	
	public PC(){
		super();
		this.setEntityType("Playable Character");
	}
	
	public void setSkills(LinkedList<Skill> skills) {
		this.skills = skills;
	}
	
	public boolean hasEnoughHP(int value){
		return value <= getStats().life;
	}
	
	public boolean hasEnoughMP(int value)
	{
		return value <= getStats().mana;
	}
	
	
	
	public int maxHealth()
	{
		return getStats().MAX_LIFE;
	}
	
	public int maxMana()
	{
		return getStats().MAX_MP;
	}
	
	public int getLevel()
	{
		return getStats().getLevel();
	}
	
	
	public LinkedList<Skill> getSkills()
	{
		return skills;
	}
	
	public Skill getSkillAt(int index) 
	{
		return skills.get(index);
	}
	
	public void update()
	{
		if(this.updateCounter > 0)
			--this.updateCounter;
	}
	
	public PC clone()
	{
		return (PC)super.clone();
		/*
		pc.equipment = equipment.clone();
		pc.inventory = inventory.clone();
		pc.occupation = occupation.clone();		
		pc.stats = stats.clone();
		*/
	}
	@Override
	public boolean isAlive() {
		return true; //stats.getLife() <= 0;
	}

}

