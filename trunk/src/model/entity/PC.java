package model.entity;

import java.util.List;
import java.util.regex.*;
import view.Drawer;
import model.skill.*;
import model.*;
import model.xml.*;
import model.item.*;
import view.Console;

public class PC extends Entity implements EquippableInventory, StatsModifiable, ModelElement {
	private Inventory inventory;
	private Occupation occupation;
	private Stats stats;
	private SkillContainer skills;
	private Equipment equipment;
	private int updateCounter;
	
	public PC(Occupation occupation, Map map) {
		this.inventory = new Inventory();
		this.occupation = occupation;
		this.stats = (Stats) occupation.stats.clone();
		this.skills = occupation.skills;
		this.equipment = new Equipment();
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}
	public void setStats(Stats stats) {
		this.stats = stats;
	}
	public void setSkills(SkillContainer skills) {
		this.skills = skills;
	}
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}
	public PC(){
		this.setEntityType("Playable Character");
	}
	
	public void draw(Drawer d) {
		d.drawEntity(this);
	}
	
	public void setTile(Tile tile)
	{
		super.setTile(tile);
		this.updateCounter = super.getSpeed();
		tile.collectItem(this);
	}

	public Inventory getInventory() {
		return inventory;
	}
	
	public void addItem(Item item)
	{
		inventory.addItem(item);
	}
	
	public void addItemSilenetly(Item item)
	{
		inventory.addItemSilently(item);
	}
	
	private void dropItem(int index) {
		if(!getTile().hasItem()) {
			Item i = this.inventory.removeItemAt(index);
			getTile().setItem(i);
			Console.getInstance().writeLine("Dropped " + i);
		}
	}

	public void equipActionAtIndex(int index) {
		//from action listener in viewer
		if(index >= inventory.count())
			return;
		inventory.getItemAt(index).activate(this);	
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
		return value >= stats.life;
	}
	
	public boolean hasEnoughMP(int value)
	{
		return value >= stats.mana;
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
	
	public boolean isAlive() {
		return stats.getLife() <= 0;
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
		pc.equipment = equipment == null ? null : equipment.clone();
		pc.inventory = inventory == null ? null : inventory.clone();
		pc.occupation = occupation == null ? null : occupation.clone();
		pc.stats = stats == null ? null : stats.clone();
		return pc;
	}
	
	public static PC fromXml(Occupation occ, String xml) {
		PC ret = fromXml(xml);
		if(occ != null) 
		{
		 ret.occupation = occ;
		 ret.stats = (Stats) ret.occupation.stats.clone();
		 ret.skills = ret.occupation.skills;
		}
		return ret;
	}
	
	public static PC fromXml(String xml) {
		final Pattern pattern = Pattern.compile("<entity>(<stats>.*</stats>)(<occupation>.*</occupation>)(<inventory>.*</inventory>)(<equipment>.*</equipment>)(<tile>.*</tile>)<facing>(.*)</facing>.*");//(</skillContainer>.*</skillContainer>)</entity>");;//;
		Matcher mat = pattern.matcher(xml);
		if(!mat.matches())
		{
			System.out.println(xml);
			throw new RuntimeException("Bad XML for Entity");
		}
		PC ret = new PC();
		ret.stats = Stats.fromXml(mat.group(1));
		ret.occupation = Occupation.fromXml(mat.group(2));
		ret.inventory = Inventory.fromXml(mat.group(3));
		ret.equipment = Equipment.fromXml(mat.group(4));
		ret.setTile(Tile.fromXml(mat.group(5)));
		ret.setFacingDirection(Location.fromXml(mat.group(6)).closestDirection());
		return ret;
	}
	public void accept(GameVisitor visitor)	
	{
		//visitor.visit();
	}
}

