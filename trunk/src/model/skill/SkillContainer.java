package model.skill;

import java.util.LinkedList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SkillContainer {
	
	private static final Pattern skillPattern = Pattern.compile("(<skill>.*?</skill>)");	
	private LinkedList<Skill> skills;
	
	public SkillContainer() {
		skills = new LinkedList<Skill>();
	}
	
	public void add(Skill s) {
		skills.add(s);
	}
	
	public Skill remove(int index) {
		return skills.remove(index);
	}

	public Skill remove() {
		return skills.remove();
	}
	
	public Skill get(int index) {
		return skills.get(index);
	}
	
	public Iterator<Skill> iterator() {
		return skills.iterator();
	}

	public int size() {
		return skills.size();
	}

	public String toXml() {
		return toXml("");
	}
	
	public String toXml(String indent) {
		StringBuilder sb = new StringBuilder();
		sb.append(indent + "<skillContainer>\n");
		Iterator<Skill> iterator = iterator();
		while (iterator.hasNext())
			sb.append(iterator.next().toXml(indent + "\t") + "\n");
		sb.append(indent + "</skillContainer>");
		return sb.toString();
	}
	
	public static SkillContainer fromXml(String xml) {
		SkillContainer ret = new SkillContainer();
		Matcher skillMatcher = skillPattern.matcher(xml);
		while(skillMatcher.find()) {
			Skill skill = Skill.fromXml(skillMatcher.group());
			ret.add(skill);
		}
		return ret;
	}
}