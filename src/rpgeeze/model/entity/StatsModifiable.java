package rpgeeze.model.entity;

public interface StatsModifiable {
	
	public void addMana(int value);
	public void addHealth(int value);
	public void addMovement(int value);
	public void addLevel(int value);
	
	public boolean hasEnoughMP(int value);
	public boolean hasEnoughHP(int value);
	
	public int maxHealth();
	public int maxMana();
	public int getLevel();
	
	public Stats getStats();
	
}
