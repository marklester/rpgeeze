package rpgeeze.model.decal;

public class Fire extends Decal {
	private static Fire instance = null;

	public Fire() {
		super("Fire");
	}

	public static Fire getInstance() {
		if(instance == null)
			instance = new Fire();
		return instance;
	}

}
