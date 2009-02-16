package model;

import model.item.Item;

public class Equipment implements Cloneable {
	public Item head;
	public Item armor;
	public Item boots;
	public Item weapon;
	public Item auxiliary;
	
	public Equipment() 	{
		head = null;
		armor = null;
		boots = null;
		weapon = null;
		auxiliary = null;
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
		return null;
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