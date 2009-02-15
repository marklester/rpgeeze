import model.Entity;
import model.Map;
import model.Model;
import model.Occupation;
import view.Time;
import view.View;
import controller.OccupationSelector;
import controller.WelcomeScreen;
import util.ResourceLoader;
import javax.swing.JFileChooser;
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
	public static void main(String[] arg) {
		String w = new String("");
		while(true)
		{
		 w = getWelcome();
		if(w.equals("New"))
		{
		 break;
		}
		else if(w.equals("Load"))
		{
			String message = loadGame();
			if(message.equals("Open"))
			{
			 break;
			}
		}
		else if(w.equals("Quit"))
		{
			break;
		}
	}
		
		if(w.equals("New"))
		{
			newGame();
		}
	}

	/**
	 * Presents the user with a dialog, waits for the user to select an
	 * Occupation, then returns the user's selection.
	 */
	public static Occupation getOccupation() {
		OccupationSelector os = new OccupationSelector();
		os.setVisible(true);
		synchronized(os) {
			try {
				os.wait();
			}
			catch(InterruptedException e) {
			}
		}
		return os.getOccupation();
	}
	
	public static String getWelcome() {
		WelcomeScreen welcome = new WelcomeScreen();
		synchronized(welcome) {
			try {
				welcome.wait();
			}
			catch(InterruptedException e) {
				
			}
		}
		return welcome.getAction();
	}
	
	public static void newGame()
	{
		Occupation occ = getOccupation();
		 Entity avatar = new Entity(occ);
		 Map map = new Map(ResourceLoader.getInstance().getStream("map.txt"));
		 Model model = new Model(map, avatar);

		 View view = new View(model);
		 //Controller controller = Controller.createController(model, view);

		 Time time = new Time(model, view);
		 time.start();
	}
	
	public static String loadGame()
	{
		String message = new String("");
        JFileChooser chooser = new JFileChooser(); 
		
		//chooser.setFileFilter(new JFileChooser.FileFilter(new String[] { "rpg" }, "rpeeze_Files")); //creates file filter
		    
		    int status = chooser.showOpenDialog(null);
		    
		    if (status == JFileChooser.APPROVE_OPTION) {
		      File selectedFile = chooser.getSelectedFile();
		     message = "Open";
		    } else if (status == JFileChooser.CANCEL_OPTION) {
                message = "Cancel";
                

		    }
		    return message;
	}
   
	
	
}
