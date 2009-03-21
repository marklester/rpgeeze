package model.skill;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.skill.Visitor;

public class RangedWeapon extends Skill implements UsableSkill {

	private static final Pattern pattern = Pattern.compile("<rangedweapon><points>(.*)</points></rangedweapon>");
		
	public RangedWeapon() {
		this(0);
	}
	
	public RangedWeapon(int points) {
		super("Ranged Weapon", "Does damage - based on your skill level", 0);
	}
	
	public void use() {
		// TODO Auto-generated method stub

	}

	public void accept(Visitor v) {
		//v.visit(this);
		
	}
	
	/*
	public static RangedWeapon fromXml(String xml) {
		Matcher mat = pattern.matcher(xml);
		if(!mat.matches())
			throw new RuntimeException("Bad XML for RangedWeapon Skill");
		int points = Integer.parseInt(mat.group(1));
		return new RangedWeapon(points);
	}
	
	public String toXml(String indent) {
		StringBuilder sb = new StringBuilder();
		sb.append(indent + "<rangedweapon>\n");
		sb.append(indent + "\t<points>" + this.getPoints() + "</points>\n");
		sb.append(indent + "</rangedweapon>");
		return sb.toString();
	}
	
	*/
}
