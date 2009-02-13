package util;

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
        View v = new View(m);
        Controller c = new Controller();
        
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
