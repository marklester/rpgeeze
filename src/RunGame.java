import model.*;
import view.*;
import controller.*;

public class RunGame {
    private RunGame() {
    	OccupationSelector os = new OccupationSelector();
    	os.setVisible(true);
    	
    	synchronized(os) {
    		try {
    			os.wait();
    		}
    		catch(InterruptedException e) {}
    	}
    	
    	Occupation o = os.getOccupation();
    	os.dispose();
    	
        Model m = new Model();
        View v = new View();
        Controller c = new Controller();
    }
    
    public static void main(String[] arg) {
    	new RunGame();
    }
}
