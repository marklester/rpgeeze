package rpgeeze;

import java.awt.event.MouseEvent;

import rpgeeze.controller.Controller;
import rpgeeze.util.cmd.Command;

public abstract class MouseHit implements Command<Controller> {
	private MouseEvent me;
	
	public MouseHit(MouseEvent me) {
		this.me = me;
	}
	
	public MouseEvent getMouseEvent() {
		return me;
	}
}
