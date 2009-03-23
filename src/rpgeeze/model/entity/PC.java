package rpgeeze.model.entity;

import java.util.List;
import java.util.regex.*;
import rpgeeze.model.skill.*;
import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.model.*;
import rpgeeze.model.xml.*;
import rpgeeze.model.item.*;
import rpgeeze.model.entity.Occupation;


public class PC extends Entity { //implements { EquippableInventory, StatsModifiable {

	private Inventory inventory;
	private Occupation occupation;
	private Stats stats;
	private SkillContainer skills;
	private Equipment equipment;
	private int updateCounter;
	
	public PC(Occupation occupation, Map map) {
		this.inventory = new Inventory();
		this.occupation = occupation;
		//this.stats = (Stats) occupation.stats.clone();
		//this.skills = occupation.skills;
		//this.equipment = new Equipment();
	}
	public PC()
	{
		
	}

	
	public void setTile(Tile tile)
	{
		super.setTile(tile);
		//this.updateCounter = super.getSpeed();
		tile.collectItem(this);
	}

	public Inventory getInventory() {
		return inventory;
	}
	
	public void addItem(Item item)
	{
		inventory.addItem(item, true);
	}
	
	public void addItemSilenetly(Item item)
	{
		inventory.addItem(item, false);
	}
	
	private void dropItem(int index) {
		if(!getTile().hasItem()) {
			Item i = this.inventory.removeItemAt(index);
			getTile().setItem(i);
			LogManager.getInstance().log("Dropped " + i, "MODEL", Message.Type.GAME);
		}
	}

	
	public void equipActionAtIndex(int index) {
		//from action listener in viewer
		if(index >= inventory.count())
			return;
		inventory.getItemAt(index).activate(this, this.getTile());	
	}
	
	public void dropActionAtIndex(int index) {
		//from action listener in viewer
		if(index >= inventory.count())
			return;	
		//Drop item here
		dropItem(index);
	}
	
	public void removeItem(Item item)
	{
		inventory.removeItem(item);
	}
	
	public void equipHead(Item i) {
		if(equipment.head == i)
			return;
		inventory.removeItem(i);
		unequipHead();
		equipment.head = i;
	}

	public void equipBoots(Item i) {
		if(equipment.boots == i)
			return;
		inventory.removeItem(i);
		unequipBoots();
		equipment.boots = i;
	}
	
	public void equipArmor(Item i) {
		if(equipment.armor == i)
			return;
		inventory.removeItem(i);
		unequipArmor();
		equipment.armor = i;
	}

	public void equipWeapon(Item i) {
		if(equipment.weapon == i)
			return;
		inventory.removeItem(i);
		unequipWeapon();
		equipment.weapon = i;
	}

	public void equipAuxiliary(Item i) {
		if(equipment.auxiliary == i)
			return;
		inventory.removeItem(i);
		if(equipment.auxiliary != null)
			inventory.addItem(equipment.auxiliary);
		equipment.auxiliary = i;
		if(equipment.weapon != null)
			equipment.weapon.use(this);
	}

	public void unequipHead() {
		if(equipment.head != null)
		{
			inventory.addItem(equipment.head);
			equipment.head.deActivate(this);
		}
		equipment.head = null;
	}

	public void unequipBoots() {
		if(equipment.boots != null)
		{
			inventory.addItem(equipment.boots);
			equipment.boots.deActivate(this);
		}
			
		equipment.boots = null;
	}

	public void unequipArmor() {
		if(equipment.armor != null)
		{
			inventory.addItem(equipment.armor);
			equipment.armor.deActivate(this);
		}
		equipment.armor = null;
	}
	
	public void unequipWeapon() {
		if(equipment.weapon != null)
		{
			inventory.addItem(equipment.weapon);
			equipment.weapon.deActivate(this);
		}
		equipment.weapon = null;
	}
	
	public void unequipAuxiliary() {
		if(equipment.auxiliary != null)
		{
			inventory.addItem(equipment.auxiliary);
			equipment.auxiliary.deActivate(this);
		}
		equipment.auxiliary = null;
	}
	
	public void unequipAll()
	{
		unequipHead();
		unequipBoots();
		unequipArmor();
		unequipWeapon();
		unequipAuxiliary();
	}
	
	public boolean hasEnoughHP(int value)
	{
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
	
	public SkillContainer getSkills()
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

