package rpgeeze.model.entity;

import rpgeeze.model.item.TakeableItem;

public class Equipment implements Cloneable {
	private TakeableItem head;
	private TakeableItem armor;
	private TakeableItem boots;
	private TakeableItem weapon;
	private TakeableItem auxiliary;
	
	public Equipment() 	{
		this(null, null, null, null, null);
	}

	public Equipment(TakeableItem head, TakeableItem armor, TakeableItem boots, TakeableItem weapon, TakeableItem auxiliary) {
		this.head = head;
		this.armor = armor;
		this.boots = boots;
		this.weapon = weapon;
		this.auxiliary = auxiliary;
	}
	
	public TakeableItem getHead() {
		return head;
	}

	public void setHead(TakeableItem head) {
		this.head = head;
	}

	public TakeableItem getArmor() {
		return armor;
	}

	public void setArmor(TakeableItem armor) {
		this.armor = armor;
	}

	public TakeableItem getBoots() {
		return boots;
	}

	public void setBoots(TakeableItem boots) {
		this.boots = boots;
	}

	public TakeableItem getWeapon() {
		return weapon;
	}

	public void setWeapon(TakeableItem weapon) {
		this.weapon = weapon;
	}

	public TakeableItem getAuxiliary() {
		return auxiliary;
	}

	public void setAuxiliary(TakeableItem auxiliary) {
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