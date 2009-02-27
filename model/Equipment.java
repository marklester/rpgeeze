package model;

import model.item.Item;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Equipment implements Cloneable {
	private static final Pattern pattern = Pattern.compile("<equipment><head>(.*)</head><armor>(.*)</armor><boots>(.*)</boots><weapon>(.*)</weapon><auxiliary>(.*)</auxiliary></equipment>");
	
	public Item head = null;
	public Item armor = null;
	public Item boots = null;
	public Item weapon = null;
	public Item auxiliary = null;
	
	public Equipment() 	{
	}
		
	public String toXml() {
		return toXml("");
	}
	
	public String toXml(String indent) {
		StringBuilder sb = new StringBuilder();
		sb.append(indent + "<equipment>\n");
		sb.append(indent + "\t<head>\n"); sb.append(head == null ? "" : head.toXml(indent + "\t\t") + "\n"); sb.append(indent + "\t</head>\n");
		sb.append(indent + "\t<armor>\n"); sb.append(armor == null ? "" : armor.toXml(indent + "\t\t") + "\n"); sb.append(indent + "\t</armor>\n");
		sb.append(indent + "\t<boots>\n"); sb.append(boots == null ? "" : boots.toXml(indent + "\t\t") + "\n"); sb.append(indent + "\t</boots>\n");
		sb.append(indent + "\t<weapon>\n"); sb.append(weapon == null ? "" : weapon.toXml(indent + "\t\t") + "\n"); sb.append(indent + "\t</weapon>\n");
		sb.append(indent + "\t<auxiliary>\n"); sb.append(auxiliary == null ? "" : auxiliary.toXml(indent + "\t\t") + "\n"); sb.append(indent + "\t</auxiliary>\n");
		sb.append(indent + "</equipment>");
		return sb.toString();
	}

	public static Equipment fromXml(String xml) {
		Matcher mat = pattern.matcher(xml);
		if(!mat.matches())
			throw new RuntimeException("Bad XML for Equipment");
		Equipment ret = new Equipment();
		String head = mat.group(1); if(head.length() != 0) ret.head = Item.fromXml(head);
		String armor = mat.group(2); if(armor.length() != 0) ret.armor = Item.fromXml(armor);
		String boots = mat.group(3); if(boots.length() != 0) ret.boots = Item.fromXml(boots);
		String weapon = mat.group(4); if(weapon.length() != 0) ret.weapon = Item.fromXml(weapon);
		String auxiliary = mat.group(5); if(auxiliary.length() != 0) ret.auxiliary = Item.fromXml(auxiliary);
		return ret;
	}
	
	public synchronized Equipment clone() {
		Equipment ret = new Equipment();
		ret.head = head == null ? null : head.clone();
		ret.armor = armor == null ? null : armor.clone();
		ret.boots = boots == null ? null : boots.clone();
		ret.weapon = weapon == null ? null : weapon.clone();
		ret.auxiliary = auxiliary == null ? null : auxiliary.clone();
		return ret;
	}
}