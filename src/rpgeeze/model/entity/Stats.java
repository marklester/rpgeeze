package rpgeeze.model.entity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Stats implements Cloneable {
	private static final Pattern pattern = Pattern.compile("<stats><level>(.*)</level><life>(.*)</life><mana>(.*)</mana><offensiveRating>(.*)</offensiveRating><defensiveRating>(.*)</defensiveRating><armorRating>(.*)</armorRating><movement>(.*)</movement>(<primaryStats>.*</primaryStats>)</stats>");
	
	// measures how good the entity is at her occupation;based on experience
	// between 1-5
	public int level;

	// how close the entity is to death; based upon hardiness and level
	// between 0-100
	public int life;

	// how much energy the entity has to fuel her spells; based on intellect and
	// level
	// between 0-100 - play starts with 20
	public int mana;

	// damage dealt when attacking;based on the equipped weapon,strength and
	// level
	// between 0-110
	public int offensiveRating;

	// how difficult it is to successfully attack this entity;based on agility
	// and level
	// between 0-110
	public int defensiveRating;

	// armor absorbs a fixed amount of damage;based on equipped armor and
	// hardiness
	// between 0-110
	public int armorRating;

	// the max. distance an entity may move over ideal terrain per unit time
	// 1 unit for now... Portal, here we come
	public int movement;

	PrimaryStats primaryStats;
	private boolean visible = true;
	
	public static int MAX_LIFE = 100;
	public static int MAX_MP = 100;
	public static final int MAX_LEVEL 	= 10;

	public Stats() {
		this.level = 1;
		this.life = MAX_LIFE;
		this.mana = MAX_MP;
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

	//Once NPC's and puzzles are introduced, this function will be called during update of avatar 
	//A entity's experience will be increased as they play the game
	//Once it hits a certain point, this will trigger a "level up"
	public void calculateLevel() {
		if(this.primaryStats.experience >= this.level * this.level * 5 && this.level < MAX_LEVEL)
			++this.level;
	}

	public int getLife() {
		return this.life;
	}

	public int getLevel() {
		return this.level;
	}
	
	//This is basically used by the "Level Up" AE's to increment the entity's level up by 1
	public void incLevel() {
		if (level < MAX_LEVEL)
			level++;
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
		if (amount == MAX_LIFE){
			life = 0;
			primaryStats.decLivesLeft();
		}
		else
			this.life -= Math.max( (int)(amount/(this.level + this.primaryStats.hardiness)) , 0);
		if(this.life < 0){
			this.life = 0;
		primaryStats.decLivesLeft();
		}
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
		this.offensiveRating = (this.primaryStats.strength / 2 + effectiveness) * this.level;
		if(this.offensiveRating > 110)
			this.offensiveRating = 110;
	}

	public void calculateDefensiveRating() {
		this.defensiveRating = this.primaryStats.agility * this.level + 10;
		if(this.defensiveRating > 110)
			this.defensiveRating = 110;
	}

	public void calculateArmorRating(int effectiveness) {
		this.armorRating = effectiveness + (int) (effectiveness * this.primaryStats.hardiness);
		if(this.armorRating > 110)
			this.armorRating = 110;
		// hardiness is a dec between 1 & 0
	}

	public void setMovement(int movement) {
		this.movement = movement;
	}
	
	

	public int getMovement() {
		return this.movement;
	}

	public void addHealth(int value)
	{
		
		this.life += value;
		if( this.life < 0)
		{
			this.life = 0;
			//TODO: alert someone that the character has died
			
		}
		if ( this.life > MAX_LIFE)
			this.life = MAX_LIFE;
	}
	
	public void addMovement(int value)
	{
		movement += value;
	}
	
	public void addMana(int value)
	{
		mana += value;
		if( mana < 0)
			mana = 0;
		if(mana > MAX_MP)
			mana = MAX_MP;
	}
	
	public void addLevel(int value)
	{
		this.level += value;
		if( level < 0)
			level = 0;
	}
	
	public void attack(int offensiveRating)
	{
		int life = (int)((double)offensiveRating / (double)this.defensiveRating) * 15;
		this.addHealth(0 - life);
	}
	
	public Stats clone()  {
		Stats s = new Stats();
		try {
		s = (Stats) super.clone();
		s.primaryStats = primaryStats == null? null :primaryStats.clone();}
		catch(CloneNotSupportedException e){}
		return s;
	}

	public PrimaryStats getPrimary() {
		return this.primaryStats;
	}
	
	public String toXml() {
		return toXml("");
	}
	
	public String toXml(String indent) {
		StringBuilder sb = new StringBuilder();
		sb.append(indent + "<stats>\n");
		sb.append(indent + "\t<level>" + level + "</level>\n");
		sb.append(indent + "\t<life>" + life + "</life>\n");
		sb.append(indent + "\t<mana>" + mana + "</mana>\n");
		sb.append(indent + "\t<offensiveRating>" + offensiveRating + "</offensiveRating>\n");
		sb.append(indent + "\t<defensiveRating>" + defensiveRating + "</defensiveRating>\n");
		sb.append(indent + "\t<armorRating>" + armorRating + "</armorRating>\n");
		sb.append(indent + "\t<movement>" + movement + "</movement>\n");
		sb.append(primaryStats.toXml(indent + "\t") + "\n");
		sb.append(indent + "</stats>");
		return sb.toString();
	}

	public static Stats fromXml(String xml) {
		Matcher mat = pattern.matcher(xml);
		if(!mat.matches())
			throw new RuntimeException("Bad XML for Stats");
		int level = Integer.parseInt(mat.group(1));
		int life = Integer.parseInt(mat.group(2));
		int mana = Integer.parseInt(mat.group(3));
		int offensiveRating = Integer.parseInt(mat.group(4));
		int defensiveRating = Integer.parseInt(mat.group(5));
		int armorRating = Integer.parseInt(mat.group(6));
		int movement = Integer.parseInt(mat.group(7));
		PrimaryStats primaryStats = PrimaryStats.fromXml(mat.group(8));
		Stats ret = new Stats(level, life, mana, movement, primaryStats);
		ret.offensiveRating = offensiveRating;
		ret.defensiveRating = defensiveRating;
		ret.armorRating = armorRating;
		return ret;
	}
}