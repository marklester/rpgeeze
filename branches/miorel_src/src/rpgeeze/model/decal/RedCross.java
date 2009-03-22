package rpgeeze.model.decal;

public class RedCross extends Decal {
	private static RedCross instance = null;

	public RedCross() {
		super("Red Cross");
	}

	public static RedCross getInstance() {
		if(instance == null)
			instance = new RedCross();
		return instance;
	}

}
