package model;

public class Entity {
	private Stats stats;
	private Inventory inventory;
	private Occupation occupation;
//    Location
//    Name
//    EquippedItems
	
	public Entity(Occupation occupation) {
		this.occupation = occupation;
	}
}

