package model.decal;

import view.Drawer;

public class GoldStar extends Decal {
	private static GoldStar instance = null;

	public GoldStar() {
		super("Gold Star");
	}

	public static GoldStar getInstance() {
		if(instance == null)
			instance = new GoldStar();
		return instance;
	}

}
