package rpgeeze.model.decal;

public class FireDecal extends Decal {
	private static FireDecal instance = null;

	public FireDecal() {
		super("Fire Decal");
	}

	public static FireDecal getInstance() {
		if(instance == null)
			instance = new FireDecal();
		return instance;
	}

}
