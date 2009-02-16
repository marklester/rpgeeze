import model.Entity;
import model.Map;
import model.Model;
import model.Occupation;
import model.PrimaryStats;
import model.Smasher;
import model.Sneak;
import model.Summoner;
import model.Stats;
import view.Time;
import view.View;
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
		Occupation o = null;
		String w = new String("");
		while(true)
		{
			 w = getWelcome();
			if(w.equals("Load"))
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
			else if(w.equals("Smasher") || w.equals("Summoner")  || w.equals("Sneak"))
			{
				if (w.equals("Smasher")) o = new Smasher();
				else if (w.equals("Summoner")) o = new Summoner();
				else if (w.equals("Sneak")) o = new Sneak();
				break;
			}
		}
		
		if(w.equals("Smasher") || w.equals("Summoner")  || w.equals("Sneak")) {
			newGame(o);
		}
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
	
	public static void newGame(Occupation o)
	{
		 Entity avatar = new Entity(o);
		 Map map = Map.fromStream(ResourceLoader.getInstance().getStream("map.xml"));
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
