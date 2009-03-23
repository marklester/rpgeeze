package rpgeeze.model.entity;

import rpgeeze.model.item.Item;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Equipment implements Cloneable {
	private Item head = null;
	private Item armor = null;
	private Item boots = null;
	private Item weapon = null;
	private Item auxiliary = null;
	
	public Equipment() 	{}
	public Equipment(Item head,Item armor,Item boots,Item weapon,Item aux){
		this.head = head;
		this.armor=armor;
		this.boots=boots;
		this.weapon = weapon;
		this.auxiliary = aux;
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