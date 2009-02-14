package model;

import view.Drawer;

public class SkullAndCrossbones extends Decal {
	private static SkullAndCrossbones instance = null;
	public SkullAndCrossbones() {
		super("Skull and Crossbones");
	}
	
	public static SkullAndCrossbones getInstance() {
		if(instance == null)
			instance = new SkullAndCrossbones();
		return instance;
	}
	
	public void draw(Drawer d) {
		d.drawSkullAndCrossbonesDecal(this);
	}
}
