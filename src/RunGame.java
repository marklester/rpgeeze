import model.*;
import view.*;
import controller.*;

import java.util.*;

public class RunGame {
    public static void main(String[] arg) {
    	ClassLoader loader = RunGame.class.getClassLoader();
    	if(loader.getResource("res/example.txt") != null) {
    		Scanner scan = new Scanner(loader.getResourceAsStream("res/example.txt"));
    		while(scan.hasNext())
    			System.out.println(scan.next());
    	}
    	
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
