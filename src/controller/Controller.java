package controller;

import model.*;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/*
 * Controller is going to pass messages in somewhat of a non conventional manner
 * when receiving an action, the respective handler will be set off.
 * The handler should not call expensive operations as the handlers will be executing on
 * the GUI thread. If handlers are caught up doing complex operations then the gui
 * will become <strong>unresponsive</strong>.
 * 
 * In order to forward messages to the Model I have included a system where the model
 * executes a list of Tasks. This can be done by 
 * <code> 
 * // assume the variable model is an instance of Model.
 * 
 * 
 * inner class Op1 extends Task
 * {
 * 		operation()
 * 		{
 * 			//code that the model thread will execute
 * 			//such as forwarding a move request
 * 		}
 * }
 * 
 * public somehandler(EventArgs e)
 * {
 * 		model.invoke( new Op1());
 * }
 * </code>
 * 
 * please be careful about referencing internal state variables within the controller when
 * writing these cross threaded operations.
 * 		--Jose
 */

/*
Here is an example of the usage that is appropriate to allow the
model's thread to execute whatever message you need it to.

	1. create an action object for the key bindings
	2. add the key bindings to this Component
	3. create a Task object that can be put on the models message queue
		in order for the model thread to execute it.
		
If you have any questions about the crap I am typing, feel free to call me
561-386-0083

	--Jose

*/ 

/*
 * OK, so I wasn't quite sure how the ActionMap was supposed to work so I'm making
 * this class a KeyListener. -- Miorel 
 * 
 */


public class Controller implements KeyListener {
	
	private final Model model;
	
	private static Controller controller;
	
	//constructor is private
	//can only be accessed by the static method
	//to ensure creation of only one controller
	//One controller = One set of key bindings
	//		--Jose
	private Controller(Model model) {
		this.model = model;
		
		/*Action move = new AbstractAction() {
			public void actionPerformed(ActionEvent ae) {
				System.err.println("The D key has been pressed.");
				Controller.this.model.invoke(new moveRight());
			}
		};*/
		
//		this.getInputMap().put(KeyStroke.getKeyStroke("d"), "D");
//		this.getActionMap().put("D", move);
	}
	
	//Keeping one instance of controller
	public static Controller createController(Model m) {
		if(controller == null)
			controller = new Controller(m);
		return controller;
	}
	
	public void keyTyped(KeyEvent e) {}

	public void keyReleased(KeyEvent e) {}
	
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W:
			model.invoke(new MoveCommand(model, Direction.NORTH));
			break;
		case KeyEvent.VK_A:
			model.invoke(new MoveCommand(model, Direction.WEST));
			break;
		case KeyEvent.VK_S:
			model.invoke(new MoveCommand(model, Direction.SOUTH));
			break;
		case KeyEvent.VK_D:
			model.invoke(new MoveCommand(model, Direction.EAST));
			break;
		default:
			break;
		}
	}
}

