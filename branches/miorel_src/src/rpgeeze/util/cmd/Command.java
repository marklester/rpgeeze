package rpgeeze.util.cmd;

public interface Command<T> {
	public void execute(T t);
}
