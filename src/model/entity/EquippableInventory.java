package model.entity;

import model.item.*;

public interface EquippableInventory extends Inventoryable{

	public void equipHead(Item i); 

	public void equipBoots(Item i);
	
	public void equipArmor(Item i);
	
	public void equipWeapon(Item i) ;
	
	public void equipAuxiliary(Item i) ;		
	
	public void unequipHead() ;
	
	public void unequipBoots() ;
	
	public void unequipArmor() ;
	
	public void unequipWeapon() ;
	
	public void unequipAuxiliary() ;
	
	public void unequipAll();
	
	public Equipment getEquipment();
	
	public void equipActionAtIndex(int index);
}
