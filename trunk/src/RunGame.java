import model.*;
import view.*;
import controller.*;

public class RunGame {
    public static void main(String[] arg) {
    	ClassLoader loader = RunGame.class.getClassLoader();
    	
    	Occupation occ = getOccupation();
    	Entity avatar = new Entity(occ);
    	Map map = new Map(loader.getResourceAsStream("res/map.txt"));
        Model model = new Model(map, avatar);
        
        Controller controller = Controller.createController(model);
        View view = new View(model, controller);
        
        model.start();
        view.start();
    }
    
    public static Occupation getOccupation() {
    	OccupationSelector os = new OccupationSelector();
    	os.setVisible(true);
    	synchronized(os) {
    		try {
    			os.wait();
    		}
    		catch(InterruptedException e) {}
    	}
    	return os.getOccupation();
    }
}
