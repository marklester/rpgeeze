package model;

import view.Drawer;

public class SkullAndCrossbones extends Decal {
	
	public SkullAndCrossbones() {
		super("Skull and Crossbones");
	}
	
	public void draw(Drawer d) {
		d.drawSkullAndCrossbonesDecal(this);
	}
}

