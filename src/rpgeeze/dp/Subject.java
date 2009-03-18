package rpgeeze.dp;

public interface Subject<T extends Subject<?>> {
	public void attach(Observer<T> observer);
	public void detach(Observer<T> observer);
}
