package rpgeeze.model.entity;

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
		this.stats = occupation.getStats().clone();
		this.skills = occupation.getSkillContainer();
		this.equipment = new Equipment();
	}
	
	public PC(){
		this.setEntityType("Playable Character");
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
	public void setTile(Tile tile){
		super.setTile(tile);
		//this.updateCounter = super.getSpeed();
		tile.collectItem(this);
	}

	public Inventory getInventory() {
		return inventory;
	}
	
	public void addItem(Item item){
		inventory.addItem(item, true);
	}
	
	public void addItemSilently(Item item){
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
		if(index >= inventory.itemCount())
			return;
		inventory.getItemAt(index).activate(this, this.getTile());	
	}
	
	public void dropActionAtIndex(int index) {
		//from action listener in viewer
		if(index >= inventory.itemCount())
			return;	
		//Drop item here
		dropItem(index);
	}
	
	public void removeItem(Item item){
		inventory.removeItem(item);
	}
	
	public void equipHead(Item i) {
		if(equipment.getHead() == i){	
		}else{
			inventory.removeItem(i);
			unequipHead();
			equipment.setHead(i);
		}
	}

	public void equipBoots(Item i) {
		if(equipment.getBoots() == i){	
		}else{
			inventory.removeItem(i);
			unequipBoots();
			equipment.setBoots(i);
		}
	}
	
	public void equipArmor(Item i) {
		if(equipment.getArmor() == i){	
		}else{
			inventory.removeItem(i);
			unequipArmor();
			equipment.setArmor(i);
		}
	}

	public void equipWeapon(Item i) {
		if(equipment.getWeapon() == i){	
		}else{
			inventory.removeItem(i);
			unequipWeapon();
			equipment.setWeapon(i);
		}
	}

	public void equipAuxiliary(Item i) {
		if(equipment.getAuxiliary() == i)
			return;
		inventory.removeItem(i);
		if(equipment.getAuxiliary() != null)
			inventory.addItem(equipment.getAuxiliary(),false);
		equipment.setAuxiliary(i);
		if(equipment.getWeapon() != null)
			equipment.getWeapon().use(this);
	}

	public void unequipHead() {
		if(equipment.getHead() != null){
			inventory.addItem(equipment.getHead(),false);
			equipment.getHead().deActivate(this);
		}
		equipment.setHead(null);
	}

	public void unequipBoots() {
		if(equipment.getBoots() != null){
			inventory.addItem(equipment.getBoots(),false);
			equipment.getBoots().deActivate(this);
		}
		equipment.setBoots(null);
	}

	public void unequipArmor() {
		if(equipment.getArmor() != null){
			inventory.addItem(equipment.getArmor(),false);
			equipment.getArmor().deActivate(this);
		}
		equipment.setArmor(null);
	}
	
	public void unequipWeapon() {
		if(equipment.getWeapon() != null){
			inventory.addItem(equipment.getWeapon(),false);
			equipment.getWeapon().deActivate(this);
		}
		equipment.setWeapon(null);
	}
	
	public void unequipAuxiliary() {
		if(equipment.getAuxiliary() != null){
			inventory.addItem(equipment.getAuxiliary(),false);
			equipment.getAuxiliary().deActivate(this);
		}
		equipment.setAuxiliary(null);
	}
	
	public void unequipAll(){
		unequipHead();
		unequipBoots();
		unequipArmor();
		unequipWeapon();
		unequipAuxiliary();
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

