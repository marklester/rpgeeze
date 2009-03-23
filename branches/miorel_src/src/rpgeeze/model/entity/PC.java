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

	
	private Occupation occupation;
	private Stats stats;
	private LinkedList<Skill> skills;
	private int updateCounter;
	private Inventory inventory;
	private Equipment equipment;
	
	public PC(Occupation occupation, Map map) {
		this.inventory = new Inventory();
		this.occupation = occupation;
		this.stats = occupation.getStats().clone();
		this.skills = occupation.getSkills();
		this.equipment = new Equipment();
	}
	
	public PC(){
		this.setEntityType("Playable Character");
	}
	
	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}
	public void setStats(Stats stats) {
		this.stats = stats;
	}
	public void setSkills(LinkedList<Skill> skills) {
		this.skills = skills;
	}
	
	public void setTile(Tile tile){
		super.setTile(tile);
		//this.updateCounter = super.getSpeed();
		tile.collectItem(this);
	}
	
	public boolean hasEnoughHP(int value){
		return value <= stats.life;
	}
	
	public boolean hasEnoughMP(int value)
	{
		return value <= stats.mana;
	}
	
	public void addHealth(int value)
	{
		stats.addHealth(value);
	}
	
	public void addMana(int value)
	{
		stats.addMana(value);
	}
	
	public void addMovement(int value)
 	{
		stats.addMovement(value);
	}
	
	public void addLevel(int value)
	{
		stats.addLevel(value);
	}
	
	public int maxHealth()
	{
		return stats.MAX_LIFE;
	}
	
	public int maxMana()
	{
		return stats.MAX_MP;
	}
	
	public int getLevel()
	{
		return stats.getLevel();
	}
	
	public Equipment getEquipment()
	{
		return equipment;
	}

	public Stats getStats()
	{
		return stats;
	}
	
	public LinkedList<Skill> getSkills()
	{
		return skills;
	}
	
	public Skill getSkillAt(int index) 
	{
		return skills.get(index);
	}
	
	public Occupation getOccupation()
	{
		return occupation;
	}
	
	public void update()
	{
		if(this.updateCounter > 0)
			--this.updateCounter;
	}
	
	public PC clone()
	{
		PC pc = (PC)super.clone();
		/*
		pc.equipment = equipment.clone();
		pc.inventory = inventory.clone();
		pc.occupation = occupation.clone();		
		pc.stats = stats.clone();
		*/
		return pc;
	}
	@Override
	public boolean isAlive() {
		return true; //stats.getLife() <= 0;
	}

}

