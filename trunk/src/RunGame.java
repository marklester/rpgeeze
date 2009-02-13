import model.*;
import view.*;
import controller.*;

public class RunGame {
    public static void main(String[] arg) {
    	Occupation o = getOccupation();
    	Entity e = new Entity(o);
        Model m = new Model(e);
        View v = new View();
        Controller c = new Controller();
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
