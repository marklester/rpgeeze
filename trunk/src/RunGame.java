import model.*;
import view.*;
import controller.*;

public class RunGame {
    public static void main(String[] arg) {
    	//ClassLoader loader = RunGame.class.getClassLoader();
    	//loader.getResource("res/example.txt");
    	
    	Occupation o = getOccupation();
    	Entity e = new Entity(o);
        Model m = new Model(e);
        
        Controller c = Controller.createController(m);
        View v = new View(m);
        
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
