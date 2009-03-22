package model.decal;

public class Trampoline extends Decal {
	private static Trampoline instance = null;

	public Trampoline() {
		super("Trampoline");
	}

	public static Trampoline getInstance() {
		if(instance == null)
			instance = new Trampoline();
		return instance;
	}
}
