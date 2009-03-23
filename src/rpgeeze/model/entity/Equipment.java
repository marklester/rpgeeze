package rpgeeze.model.entity;


import rpgeeze.model.item.EquippableItem;

public class Equipment implements Cloneable {
	private EquippableItem head;
	private EquippableItem armor;
	private EquippableItem boots;
	private EquippableItem weapon;
	private EquippableItem auxiliary;
	
	public Equipment() 	{
		this(null, null, null, null, null);
	}

	public Equipment(EquippableItem head, EquippableItem armor, EquippableItem boots, EquippableItem weapon, EquippableItem auxiliary) {
		this.head = head;
		this.armor = armor;
		this.boots = boots;
		this.weapon = weapon;
		this.auxiliary = auxiliary;
	}
	
	public EquippableItem getHead() {
		return head;
	}

	public void setHead(EquippableItem head) {
		this.head = head;
	}

	public EquippableItem getArmor() {
		return armor;
	}

	public void setArmor(EquippableItem armor) {
		this.armor = armor;
	}

	public EquippableItem getBoots() {
		return boots;
	}

	public void setBoots(EquippableItem boots) {
		this.boots = boots;
	}

	public EquippableItem getWeapon() {
		return weapon;
	}

	public void setWeapon(EquippableItem weapon) {
		this.weapon = weapon;
	}

	public EquippableItem getAuxiliary() {
		return auxiliary;
	}

	public void setAuxiliary(EquippableItem auxiliary) {
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