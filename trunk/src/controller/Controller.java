package controller;

public class Controller {
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
	 */
}

