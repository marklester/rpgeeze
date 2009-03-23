package rpgeeze.model.entity;

import rpgeeze.model.item.Item;

public class Equipment implements Cloneable {
	private Item head;
	private Item armor;
	private Item boots;
	private Item weapon;
	private Item auxiliary;
	
	public Equipment() 	{
		this(null, null, null, null, null);
	}

	public Equipment(Item head, Item armor, Item boots, Item weapon, Item auxiliary) {
		this.head = head;
		this.armor = armor;
		this.boots = boots;
		this.weapon = weapon;
		this.auxiliary = auxiliary;
	}
	
	public Item getHead() {
		return head;
	}

	public void setHead(Item head) {
		this.head = head;
	}

	public Item getArmor() {
		return armor;
	}

	public void setArmor(Item armor) {
		this.armor = armor;
	}

	public Item getBoots() {
		return boots;
	}

	public void setBoots(Item boots) {
		this.boots = boots;
	}

	public Item getWeapon() {
		return weapon;
	}

	public void setWeapon(Item weapon) {
		this.weapon = weapon;
	}

	public Item getAuxiliary() {
		return auxiliary;
	}

	public void setAuxiliary(Item auxiliary) {
		this.auxiliary = auxiliary;
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