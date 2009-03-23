package rpgeeze.model.entity;

import rpgeeze.model.entity.PrimaryStats;
import rpgeeze.model.entity.Stats;
import rpgeeze.model.skill.Brawling;
import rpgeeze.model.skill.OneHandedWeapon;
import rpgeeze.model.skill.TwoHandedWeapon;

public class Smasher extends Occupation {
	public Smasher() {
		super("Smasher", new Stats(1,100,20,15,(new PrimaryStats(3,20,5,5,2,1))),
				new OneHandedWeapon(), new TwoHandedWeapon(), new Brawling()
		);
	}
}
