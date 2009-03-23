package rpgeeze.model.entity;

import rpgeeze.model.skill.*;
import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.model.*;
//import rpgeeze.model.xml.*;
import rpgeeze.model.item.*;
import rpgeeze.model.entity.Occupation;
import java.util.LinkedList;

public class PC extends Entity {//implements StatsModifiable{ //implements { EquippableInventory, StatsModifiable {
	private LinkedList<Skill> skills;
	private int updateCounter;
	private float cash;

	public PC(Occupation occupation, Map map) {
		super(occupation);
		this.skills = occupation.getSkills();
		cash = 0;
	}
	
	public PC(){
		super();
		this.setEntityType("Playable Character");
	}
	
	public void setSkills(LinkedList<Skill> skills) {
		this.skills = skills;
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
	
	public double getCash() {
		return cash;
	}
	
	public void addCash(int howMuch) {
		cash+= howMuch;
		LogManager.getInstance().log("Money in the bank - " + howMuch, "", Message.Type.GAME);
	}

}

