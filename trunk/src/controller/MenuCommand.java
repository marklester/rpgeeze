package controller;

import model.Command;
import model.Model;
import view.View;

//Used to control Menu Popups
public class MenuCommand implements Command {
	private final int viewport;
	private final View view;

	public MenuCommand(View view, int viewport) {
		this.viewport = viewport;
		this.view = view;
	}

	public void execute(Model m) {
		switch(this.viewport) {
		case Controller.STAT_VIEW:
			m.getAvatar().getStats().setVisible(!m.getAvatar().getStats().isVisible());
			break;
		case Controller.INVENTORY_VIEW:
			view.toggleInventoryVisible();
			break;
		default:
			break;
		}

	}
}