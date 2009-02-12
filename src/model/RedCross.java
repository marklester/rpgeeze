package model;

import view.Drawer;

public class RedCross extends Decal {

	public RedCross() {
		super("Red Cross");
	}

	public void draw(Drawer d) {
		d.drawRedCrossDecal(this);
	}
}

