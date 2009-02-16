package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrimaryStats implements Cloneable {
	private static final Pattern pattern = Pattern.compile("<primaryStats><livesLeft>(.*)</livesLeft><strength>(.*)</strength><agility>(.*)</agility><intellect>(.*)</intellect><hardiness>(.*)</hardiness><experience>(.*)</experience></primaryStats>");
	
	// how many more times the entity can die before the game is over.
	// Start the game with 3 lives
	int livesLeft;

	// primary attribute of the Smasher
	// Everyone starts w/ 20, except smasher, who starts with 40
	int strength;

	// primary attribute of the Sneak
	// Everyone starts w/ 20, except sneak, who starts with 40
	int agility;

	// primary attribute of the Summoner
	// Everyone starts w/ 20, except summoner, who starts with 40
	int intellect;

	// measures how resistant a character is to physical abuse
	// Scale is 0-1 (a double) All start with .2
	double hardiness;

	// measures how much an entity knows about her occupation; earned by
	// adventuring, solving problems, etc.
	// Scale is 0-100
	int experience;

	// Generic stats
	public PrimaryStats() {
		this.livesLeft = 3;
		this.strength = 20;
		this.agility = 20;
		this.intellect = 20;
		this.hardiness = .2;
		this.experience = 1;
	}

	public PrimaryStats(int livesLeft, int strength, int agility, int intellect, double hardiness, int experience) {
		this.livesLeft = livesLeft;
		this.strength = strength;
		this.agility = agility;
		this.intellect = intellect;
		this.hardiness = hardiness;
		this.experience = experience;
	}
	
	public void decLivesLeft() {
		livesLeft--;
	}

	public void setLivesLeft(int livesLeft) {
		this.livesLeft = livesLeft;
	}

	public int getLivesLeft() {
		return this.livesLeft;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getStrength() {
		return this.strength;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public int getAgility() {
		return this.agility;
	}

	public void setIntellect(int intellect) {
		this.intellect = intellect;
	}

	public int getIntellect() {
		return this.intellect;
	}

	public void setHardiness(int hardiness) {
		this.hardiness = hardiness;
	}

	public double getHardiness() {
		return this.hardiness;
	}

	public void setExperience(int experience) {
		if(experience > 100)
		{
			this.experience = 100;
		}
		else
		{
			this.experience = experience;	
		}
		
	}

	public int getExperience() {
		return this.experience;
	}

	public PrimaryStats clone() throws CloneNotSupportedException {
		PrimaryStats s = new PrimaryStats();
		try{s = (PrimaryStats) super.clone();}
		catch(CloneNotSupportedException e){};
		return s;
	}

	public String toXml() {
		return toXml("");
	}
	
	public String toXml(String indent) {
		StringBuilder sb = new StringBuilder();
		sb.append(indent + "<primaryStats>\n");
		sb.append(indent + "\t<livesLeft>" + livesLeft + "</livesLeft>\n");
		sb.append(indent + "\t<strength>" + strength + "</strength>\n");
		sb.append(indent + "\t<agility>" + agility + "</agility>\n");
		sb.append(indent + "\t<intellect>" + intellect + "</intellect>\n");
		sb.append(indent + "\t<hardiness>" + hardiness + "</hardiness>\n");
		sb.append(indent + "\t<experience>" + experience + "</experience>\n");
		sb.append(indent + "</primaryStats>");
		return sb.toString();
	}

	public static PrimaryStats fromXml(String xml) {
		Matcher mat = pattern.matcher(xml);
		if(!mat.matches())
			throw new RuntimeException("Bad XML for PrimaryStats");
		int livesLeft = Integer.parseInt(mat.group(1));
		int strength = Integer.parseInt(mat.group(2));
		int agility = Integer.parseInt(mat.group(3));
		int intellect = Integer.parseInt(mat.group(4));
		double hardiness = Double.parseDouble(mat.group(5));
		int experience = Integer.parseInt(mat.group(6));
		return new PrimaryStats(livesLeft, strength, agility, intellect, hardiness, experience);
	}
}
