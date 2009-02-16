import model.Entity;
import model.Map;
import model.Model;
import model.Occupation;
import view.Time;
import view.View;
import controller.WelcomeScreen;
import util.ResourceLoader;
import javax.swing.JFileChooser;

import java.awt.DisplayMode;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.File;


/**
 * Entry point to the entire game!
 * 
 * The main method:
 * - asks the user to pick an Occupation
 * - creates an avatar
 * - reads a Map from a file
 * - initializes a Model with the avatar and Map
 * - creates a Controller
 * - initializes a View with the specified Model and Controller  
 * - starts the Time thread
 */

public class RunGame {
	/**
	 * Starts a new game. See the description above for what happens.
	 */
	public static WelcomeScreen welcome = null;
	
	public static void main(String[] arg) {
		String w = new String("");
		
		while(true)
		{
			 w = getWelcome();
			if(w.equals("Open") || w.equals("Quit") || w.equals("New"))
			{
				break;
			}
		}
		
		if(w.equals("New"))
		{
			welcome.initOcc();
			synchronized(welcome) {
				try {
					welcome.wait();
				}
				catch(InterruptedException e) {
					
				}
			}
			newGame(welcome.getOccupation());
			welcome.setVisible(false);
			welcome.dispose();
		}
		
		else if(w.equals("Open"))
		{
			
		}
	}

	
	public static String getWelcome() {

		GraphicsDevice dev = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		GraphicsConfiguration gc = dev.getDefaultConfiguration();
		DisplayMode mode = new DisplayMode(1024, 768, 32, DisplayMode.REFRESH_RATE_UNKNOWN);
		if(dev.isDisplayChangeSupported()) {
			welcome = new WelcomeScreen(gc);
			dev.setDisplayMode(mode);
		} else
			welcome = new WelcomeScreen();
		synchronized(welcome) {
			try {
				welcome.wait();
			}
			catch(InterruptedException e) {
				
			}
		}
		return welcome.getAction();
	}
	
	public static void newGame(Occupation occ)
	{		 
		 Map map = Map.fromStream(ResourceLoader.getInstance().getStream("map.xml"));
		 Entity avatar = new Entity(occ, map);
		 Model model = new Model(map, avatar);

		 View view = new View(model);
		 //Controller controller = Controller.createController(model, view);

		 Time time = new Time(model, view);
		 time.start();
	}
	
}
