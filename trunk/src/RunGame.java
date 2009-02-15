import model.Entity;
import model.Map;
import model.Model;
import model.Occupation;
import view.Time;
import view.View;
import controller.Controller;
import controller.OccupationSelector;
import util.ResourceLoader;

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
		Occupation occ = getOccupation();
		Entity avatar = new Entity(occ);
		Map map = new Map(ResourceLoader.getInstance().getStream("map.txt"));
		Model model = new Model(map, avatar);

		View view = new View(model);
		//Controller controller = Controller.createController(model, view);

		Time time = new Time(model, view);
		time.start();
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
}
