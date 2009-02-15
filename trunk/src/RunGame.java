import model.Entity;
import model.Map;
import model.Model;
import model.Occupation;
import view.Time;
import view.View;
import controller.Controller;
import controller.OccupationSelector;

public class RunGame {
	public static void main(String[] arg) {
		ClassLoader loader = RunGame.class.getClassLoader();

		Occupation occ = getOccupation();
		Entity avatar = new Entity(occ);
		Map map = new Map(loader.getResourceAsStream("res/map.txt"));
		Model model = new Model(map, avatar);

		Controller controller = Controller.createController(model);
		View view = new View(model, controller);

		Time time = new Time(model, view);
		time.start();
	}

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
