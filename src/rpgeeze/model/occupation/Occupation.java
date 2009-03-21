package rpgeeze.model.occupation;

import rpgeeze.dp.Iterator;
import rpgeeze.util.ArrayIterator;

public abstract class Occupation {
	public static Iterator<Occupation> getPlayerOccupations() {
		return new ArrayIterator<Occupation>(
				new Smasher(),
				new Summoner(),
				new Sneak()
		);
	}
	
	public abstract String getName();
}
