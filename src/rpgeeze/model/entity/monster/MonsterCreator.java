package rpgeeze.model.entity.monster;

public class MonsterCreator {

	private static MonsterCreator instance;
	
	private MonsterCreator()
	{
	}
	
	public static MonsterCreator getInstance()
	{
		if(instance == null)
			instance = new MonsterCreator();
		return instance;
	}
	
	public Monster createMonster(MonsterType type)
	{
		 switch(type)
		 {
			 case Soldier : 
				 return new Soldier();
			 case Golem : 
				 return new Golem();
			 case Skeleton : 
				 return new Skeleton();
			 case Rat : 
				 return new Rat();
			 default : break;
		 }
		 return null;
	}
	
}
