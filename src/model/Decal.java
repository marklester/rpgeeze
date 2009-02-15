package model;

/*
 * Decals augment the terrain and primarily serve as eye-candy. They do not
 * intrinsically affect game play -- though one may be used to mark a tile to
 * indicate an area-effect, &c.
 */

import view.Drawable;

public abstract class Decal implements Drawable, Cloneable {

	protected final String name;

	protected Decal(String name) {
		this.name = name;
	}

	public Decal clone() throws CloneNotSupportedException {
		return (Decal) super.clone();
	}

	public String toString() {
		return this.name;
	}
}