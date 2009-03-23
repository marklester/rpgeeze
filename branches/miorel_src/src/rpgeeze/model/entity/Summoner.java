package rpgeeze.model.entity;

import rpgeeze.model.skill.*;

public class Summoner extends Occupation {
	public Summoner() {
		super("Summoner", new Stats(1,100,20,15,new PrimaryStats(3,5,5,20,2,1)),
		new BindWounds(), new Bargain(), new Observation(),
				new OneHandedWeapon(), new TwoHandedWeapon(), new Brawling()
		);
	}
}
