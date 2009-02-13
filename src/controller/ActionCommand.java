package controller;

import model.*;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class ActionCommand extends AbstractAction {
	private Model model;
	private Command command;
	
	protected ActionCommand(Model model, Command command) {
		this.model = model;
		this.command = command;
	}
			
	public void actionPerformed(ActionEvent ae) {
		model.invoke(command);
	}
}