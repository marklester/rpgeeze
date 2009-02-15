package model;

public class Stats implements Cloneable {

	// measures how good the entity is at her occupation;based on experience
	// between 1-5
	int level;

	// how close the entity is to death; based upon hardiness and level
	// between 0-100
	int life;

	// how much energy the entity has to fuel her spells; based on intellect and
	// level
	// between 0-100 - play starts with 20
	int mana;

	// damage dealt when attacking;based on the equipped weapon,strength and
	// level
	// between 0-110
	int offensiveRating;

	// how difficult it is to successfully attack this entity;based on agility
	// and level
	// between 0-110
	int defensiveRating;

	// armor absorbs a fixed amount of damage;based on equipped armor and
	// hardiness
	// between 0-110
	int armorRating;

	// the max. distance an entity may move over ideal terrain per unit time
	// 1 unit for now... Portal, here we come
	int movement;

	PrimaryStats primaryStats;
	private boolean visible = true;

	public Stats() {
		this.level = 1;
		this.life = 100;
		this.mana = 20;
		this.offensiveRating = 1;
		this.defensiveRating = 1;
		this.armorRating = 1;
		this.movement = 15;
		this.primaryStats = new PrimaryStats();
	}

	public Stats(int level, int life, int mana, int movement, PrimaryStats stats) {
		this.level = level;
		this.life = life;
		this.mana = mana;
		this.movement = movement;
		this.primaryStats = stats;
	}

	public void calculateLevel() {
		if(this.primaryStats.experience >= this.level * this.level * 5
				&& this.level < 5)
			++this.level;
	}

	public int getLife() {
		return this.life;
	}

	public int getLevel() {
		return this.level;
	}

	public int getMana() {
		return this.mana;
	}

	public int getOffensiveRating() {
		return this.offensiveRating;
	}

	public int getDefensiveRating() {
		return this.defensiveRating;
	}

	public int getArmorRating() {
		return this.armorRating;
	}

	public boolean isVisible() {
		return this.visible;
	}

	public void setVisible(boolean b) {
		this.visible = b;
	}

	public void incLife(int amount) {
		this.life += amount;
		if(this.life > 100)
			this.life = 100; // max is 100
	}

	public void decLife(int amount) {
		this.life -= amount / (this.level + this.primaryStats.hardiness);
		if(this.life < 0)
			this.life = 0;
	}

	public void decMana(int amount) {
		this.mana -= amount;
		if(this.mana < 0)
			this.mana = 0;
	}

	public void incMana(int amount) {
		this.mana += amount;
		if(this.mana > 100)
			this.mana = 100;
	}

	// Ratings are passed an integer "effectiveness" which is a property of
	// each weapon/armor, which will be on a 1-20 scale
	// These methods will need to be called upon equipping & unequipping

	public void calculateOffensiveRating(int effectiveness) {
		this.offensiveRating = (this.primaryStats.strength / 2 + effectiveness)
				* this.level;
		if(this.offensiveRating > 100)
			this.offensiveRating = 110;
	}

	public void calculateDefensiveRating() {
		this.defensiveRating = this.primaryStats.agility * this.level + 10;
	}

	public void calculateArmorRating(int effectiveness) {
		this.armorRating = effectiveness
				+ (int) (effectiveness * this.primaryStats.hardiness);
		// hardiness is a dec between 1 & 0
	}

	public void setMovement(int movement) {
		this.movement = movement;
	}

	public int getMovement() {
		return this.movement;
	}

	public Stats clone() throws CloneNotSupportedException {
		Stats s = (Stats) super.clone();
		s.primaryStats = this.primaryStats.clone();
		return s;
	}

	public PrimaryStats getPrimary() {
		return this.primaryStats;
	}
}