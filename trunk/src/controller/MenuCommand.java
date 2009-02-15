package controller;

import model.Command;
import model.Model;

//Used to control Menu Popups
public class MenuCommand implements Command {
	private final int viewport;

	public MenuCommand(int viewport) {
		this.viewport = viewport;
	}

	public void execute(Model m) {
		switch(this.viewport) {
		case Controller.STAT_VIEW:
			m.getAvatar().getStats().setVisible(
					!m.getAvatar().getStats().isVisible());
			break;
		case Controller.INVENTORY_VIEW:
			m.getAvatar().getInventory().setVisible(
					!m.getAvatar().getInventory().isVisible());
			break;
		default:
			break;
		}

	}
}