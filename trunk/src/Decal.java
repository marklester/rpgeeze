/*
 * Decals augment the terrain and primarily serve as eye-candy. They do not
 * intrinsically affect game play—though one may be used to mark a tile to
 * indicate an area-effect, &c.
 */

public abstract class Decal implements Drawable {
	
    protected final String name;	
	
    public String toString() {
	    return name;
	}
}

