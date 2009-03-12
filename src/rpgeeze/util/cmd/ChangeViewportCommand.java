package rpgeeze.util.cmd;

import rpgeeze.controller.Controller;
import rpgeeze.view.View;

public class ChangeViewportCommand implements Command {
	public ChangeViewportCommand(View view, Controller controller) {
		
	}
	
	public void execute(Commandable c) {
		c.handler().handleChangeViewportCommand(this);
	}
}
