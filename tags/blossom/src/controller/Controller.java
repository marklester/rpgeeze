package controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;

import model.Command;
import model.Direction;
import model.Model;
import view.View;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


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

public class Controller extends JComponent implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9202150360931946162L;
	public static final int MAP_VIEW = 0;
	public static final int INVENTORY_VIEW = 1;
	public static final int MENU_VIEW = 2;
	public static final int STAT_VIEW = 3;
	
	private final Model model;
	private final View view;

	private static Controller controller;

	// constructor is private
	// can only be accessed by the static method
	// to ensure creation of only one controller
	// One controller = One set of key bindings
	// --Jose
	private Controller(Model model, View view) {
		this.model = model;
		this.view = view;

		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_W)), "MoveNorth");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_S)), "MoveSouth");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_D)), "MoveEast");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_A)), "MoveWest");

		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_1)), "MoveSouthWest");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_2)), "MoveSouth");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_3)), "MoveSouthEast");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_4)), "MoveWest");		
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_6)), "MoveEast");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_7)), "MoveNorthWest");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_8)), "MoveNorth");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_9)), "MoveNorthEast");
		
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_F12)), "SaveGame");
		
//		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke((char) KeyEvent.VK_F8), "UnequipLH");
//		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke((char) KeyEvent.VK_F9), "UnequipRH");
//		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke((char) KeyEvent.VK_F10), "UnequipHEAD");
//		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke((char) KeyEvent.VK_F11), "UnequipARMOR");
//		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke((char) KeyEvent.VK_F12), "UnequipFEET");
		//this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('l'), "DropITEM");

		// ** We need commands for selecting items to equip... May need to be
		// selected by mouse
		// ** Once that is established, the command object is
		// EquipCommand(model, index_of_item)
		// ** -Jason

		// 'c' is closer to the awsd pad
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_C)), "Stats");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_T)), "Inventory");

		this.getActionMap().put("MoveNorth", new ActionCommand(model, Direction.NORTH.moveCommand()));
		this.getActionMap().put("MoveSouth", new ActionCommand(model, Direction.SOUTH.moveCommand()));
		this.getActionMap().put("MoveEast", new ActionCommand(model, Direction.EAST.moveCommand()));
		this.getActionMap().put("MoveWest", new ActionCommand(model, Direction.WEST.moveCommand()));
		this.getActionMap().put("MoveNorthWest", new ActionCommand(model, Direction.NORTHWEST.moveCommand()));
		this.getActionMap().put("MoveNorthEast", new ActionCommand(model, Direction.NORTHEAST.moveCommand()));
		this.getActionMap().put("MoveSouthWest", new ActionCommand(model, Direction.SOUTHWEST.moveCommand()));
		this.getActionMap().put("MoveSouthEast", new ActionCommand(model, Direction.SOUTHEAST.moveCommand()));

		this.getActionMap().put("SaveGame", new ActionCommand(model, new Command() {
			public void execute(Model model) {
				JFileChooser chooser = new JFileChooser(); 
				chooser.setApproveButtonText("Save");
				chooser.setDialogTitle("Save");
				int status = chooser.showOpenDialog(null);
				    
				if(status == JFileChooser.APPROVE_OPTION) {
					File selectedFile = chooser.getSelectedFile();
					java.io.PrintWriter writer = null;
					try {
						writer = new java.io.PrintWriter(selectedFile); 
					}
					catch(FileNotFoundException e) {
					}
					if(writer != null) {
						writer.println(model.toXml());
						writer.flush();
						writer.close();
					}
				} 
				else if(status == JFileChooser.CANCEL_OPTION) {
					//message = "Cancel";					    	
				}
			}
		}));
		
//		this.getActionMap().put("UnequipLH", new ActionCommand(model, new UnequipCommand(Entity.ENT_LEFT_H)));
//		this.getActionMap().put("UnequipRH", new ActionCommand(model, new UnequipCommand(Entity.ENT_RIGHT_H)));
//		this.getActionMap().put("UnequipHEAD", new ActionCommand(model, new UnequipCommand(Entity.ENT_HEAD)));
//		this.getActionMap().put("UnequipFEET", new ActionCommand(model, new UnequipCommand(Entity.ENT_FEET)));

		//this.getActionMap().put("DropITEM", new ActionCommand(model, new ItemCommand(0)));

		// For Stats
		this.getActionMap().put("Stats", new ActionCommand(model, new Command() {
			public void execute(Model model) {
				model.getAvatar().getStats().setVisible(!model.getAvatar().getStats().isVisible());
			}
		}));
		
		// For Inventory
		this.getActionMap().put("Inventory", new ActionCommand(model, new Command() {
			public void execute(Model model) {
				Controller.this.view.toggleInventoryVisible();
			}
		}));
	}

	public static Controller createController(Model model, View view) {
		// what kind of screwed up Singleton is this?
		//if(controller == null)
			controller = new Controller(model, view);
		return controller;
	}

	public void mouseClicked(final MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			this.model.invoke(new Command() {
				public void execute(Model m) {
					view.mouseLeftClickAt(new Point(e.getX(), e.getY()));
				}
			});
		else if(e.getButton() == MouseEvent.BUTTON3)
			this.model.invoke(new Command() {
				public void execute(Model m) {
					view.mouseRightClickAt(new Point(e.getX(), e.getY()));
				}
			});
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}
}
