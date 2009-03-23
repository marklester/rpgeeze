package rpgeeze.model.entity;

import rpgeeze.model.skill.*;

public class Sneak extends Occupation {
	public Sneak() {
		super("Sneak", new Stats(1,100,20,15,new PrimaryStats(3,5,20,5,2,1)),
		new BindWounds(), new Bargain(), new Observation(),
				new OneHandedWeapon(), new TwoHandedWeapon(), new Brawling()
		);
	}
}
