package rpgeeze.util.cmd;

public abstract class CommandHandler implements Commandable {
	public final void handle(Command cmd) {
		cmd.execute(this);
	}
	
	public void handleChangeViewportCommand(ChangeViewportCommand cmd) {
		defaultAction(cmd);
	}
	
	public void defaultAction(Command cmd) {
		throw new RuntimeException();
	}
	
	public final CommandHandler handler() {
		return this;
	}
}
