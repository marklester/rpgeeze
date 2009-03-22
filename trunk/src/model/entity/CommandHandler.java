package model.entity;

import model.Command;

public interface CommandHandler {
	public void execute(Command c);
}
