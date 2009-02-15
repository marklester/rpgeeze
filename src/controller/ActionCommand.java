package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import model.Command;
import model.Model;

public class ActionCommand extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4146539588574258362L;
	private final Model model;
	private final Command command;

	protected ActionCommand(Model model, Command command) {
		this.model = model;
		this.command = command;
	}

	public void actionPerformed(ActionEvent ae) {
		this.model.invoke(this.command);
	}
}