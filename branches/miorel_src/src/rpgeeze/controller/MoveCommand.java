package rpgeeze.controller;

import rpgeeze.dp.Command;
import rpgeeze.model.entity.Entity;
import rpgeeze.util.Direction;

public class MoveCommand implements Command {
	private Entity entity;
	private Direction direction;
	
	public MoveCommand(Entity entity, Direction direction) {
		this.entity = entity;
		this.direction = direction;
	}
	
	public void execute() {
		entity.move(direction);
	}
}
