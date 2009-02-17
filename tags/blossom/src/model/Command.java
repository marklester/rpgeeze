package model;

public interface Command {
	// The argument probably shouldn't be a Model but some interface.
	public void execute(Model m);
}
