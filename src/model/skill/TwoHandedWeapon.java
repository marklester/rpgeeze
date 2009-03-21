package model.skill;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.entity.Stats;

public class TwoHandedWeapon extends Skill implements PermanentSkill {

	private static final Pattern pattern = Pattern.compile("<twohandedweapon><points>(.*)</points></twohandedweapon>");
	
	public TwoHandedWeapon() {
		this(0);
	}
	
	public TwoHandedWeapon(int points) {
		super("Two-handed weapon", "Ability to fight with a two-handed weapon", points);
	}
	
	public void modifyStats(Stats s) {
		// TODO Auto-generated method stub

	}
	
	/*
	public static TwoHandedWeapon fromXml(String xml) {
		Matcher mat = pattern.matcher(xml);
		if(!mat.matches())
			throw new RuntimeException("Bad XML for Two-handed Skill");
		int points = Integer.parseInt(mat.group(1));
		return new TwoHandedWeapon(points);
	}
	
	public String toXml(String indent) {
		StringBuilder sb = new StringBuilder();
		sb.append(indent + "<tohandedweapon>\n");
		sb.append(indent + "\t<points>" + this.getPoints() + "</points>\n");
		sb.append(indent + "</twohandedweapon>");
		return sb.toString();
	}
	*/

}
