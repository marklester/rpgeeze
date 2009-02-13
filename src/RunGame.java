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
        
        Controller c = Controller.createController(model);
        View v = new View(model);
        
        v.run();
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
