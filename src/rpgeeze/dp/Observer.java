package rpgeeze.dp;

public interface Observer<T extends Subject<?>> {
	public void update();
}
