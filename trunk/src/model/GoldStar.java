package model;

import view.Drawer;

public class GoldStar extends Decal {

	public GoldStar() {
		super("Gold Star");
	}

	public void draw(Drawer d) {
		d.drawGoldStarDecal(this);
	}
}

