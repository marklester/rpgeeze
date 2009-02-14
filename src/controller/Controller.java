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
 * inner class Op1 extends command
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

public class Controller extends JComponent {
	public static final int MAP_VIEW =0 ;
	public static final int INVENTORY_VIEW=1;
	public static final int MENU_VIEW=2;
	public static final int STAT_VIEW=3;
	private int contolled_view = MAP_VIEW;
//	private final Model model;
	
	private static Controller controller;
	
	//constructor is private
	//can only be accessed by the static method
	//to ensure creation of only one controller
	//One controller = One set of key bindings
	//		--Jose
	private Controller(Model model) {
//		this.model = model;	
		
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_W)), "North");//Yeah got w to work as a key
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('s'), "South");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('d'), "East");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('a'), "West");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke((char)KeyEvent.VK_F8), "UnequipLH");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke((char)KeyEvent.VK_F9), "UnequipRH");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke((char)KeyEvent.VK_F10), "UnequipHEAD");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke((char)KeyEvent.VK_F11), "UnequipARMOR");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke((char)KeyEvent.VK_F12), "UnequipFEET");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('l'),"DropITEM");
		
		//** We need commands for selecting items to equip... May need to be selected by mouse
		//** Once that is established, the command object is EquipCommand(model, index_of_item)
		//** -Jason
		
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('m'), "Stats");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('i'), "Inventory");
		
		this.getActionMap().put("North", new ActionCommand(model, new MoveCommand(model, Direction.NORTH)));
		this.getActionMap().put("South", new ActionCommand(model, new MoveCommand(model, Direction.SOUTH)));
		this.getActionMap().put("East", new ActionCommand(model, new MoveCommand(model, Direction.EAST)));
		this.getActionMap().put("West", new ActionCommand(model, new MoveCommand(model, Direction.WEST)));
		this.getActionMap().put("UnequipLH", new ActionCommand(model, new UnequipCommand(model, Entity.ENT_LEFT_H)));
		this.getActionMap().put("UnequipRH", new ActionCommand(model, new UnequipCommand(model, Entity.ENT_RIGHT_H)));
		this.getActionMap().put("UnequipHEAD", new ActionCommand(model, new UnequipCommand(model, Entity.ENT_HEAD)));
		this.getActionMap().put("UnequipFEET", new ActionCommand(model, new UnequipCommand(model, Entity.ENT_FEET)));
		
		this.getActionMap().put("DropITEM", new ActionCommand(model, new ItemCommand(model,0)));
		//For Stats;*/
		this.getActionMap().put("Stats", new ActionCommand(model, new MenuCommand(model, controller,Controller.STAT_VIEW)));
		this.getActionMap().put("Inventory", new ActionCommand(model, new MenuCommand(model, controller,Controller.INVENTORY_VIEW)));
	}
	
	//Keeping one instance of controller
	public static Controller createController(Model m) {
		if(controller == null)
			controller = new Controller(m);
		return controller;
	}
	public void setView(int view){
		this.contolled_view=view;
	}
	public int getView(){
		return this.contolled_view;
	}
}

