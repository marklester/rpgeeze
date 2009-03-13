import model.Entity;
import model.Map;
import model.Model;
import model.Occupation;
import view.AudioThread;
import view.Time;
import view.View;
import controller.WelcomeScreen;
import util.ResourceLoader;

import java.awt.DisplayMode;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.Scanner;



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
	public static WelcomeScreen welcome = null;
	
	public static void main(String[] arg) {
		/*
		*/		
		String w = new String("");
		
		while(!Thread.interrupted()) {
			while(true) {
				w = getWelcome();
				if(w.equals("Open") || w.equals("Quit") || w.equals("New")) {
					break;
				}
			}
			
			if(w.equals("New"))
			{
				welcome.initOcc();
				synchronized(welcome) {
					try {
						welcome.wait();
					}
					catch(InterruptedException e) {
						
					}
				}
				Thread t = newGame(welcome.getOccupation(), ResourceLoader.getInstance().getScanner("model.xml"));
				welcome.setVisible(false);		
				welcome.dispose();
				try {
					t.join();
				}
				catch(InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}		
			else if(w.equals("Open")) {
				Thread t = newGame(null, welcome.scanner);
				welcome.setVisible(false);		
				welcome.dispose();
				try {
					t.join();
				}
				catch(InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
			else if(w.equals("Quit"))
			{
				Thread.currentThread().interrupt();
				System.exit(0);
			}
		}
	}

	
	public static String getWelcome() {

		GraphicsDevice dev = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		GraphicsConfiguration gc = dev.getDefaultConfiguration();
		DisplayMode mode = new DisplayMode(1024, 768, 32, DisplayMode.REFRESH_RATE_UNKNOWN);
		if(dev.isDisplayChangeSupported()) {
			welcome = new WelcomeScreen(gc);
			dev.setDisplayMode(mode);
		} else
			welcome = new WelcomeScreen();
		synchronized(welcome) {
			try {
				welcome.wait();
			}
			catch(InterruptedException e) {
				
			}
		}
		return welcome.getAction();
	}
	
	public static Thread newGame(Occupation occ, Scanner scanner) {
		StringBuilder xml = new StringBuilder();
		while(scanner.hasNextLine()) 
			xml.append(scanner.nextLine().replaceAll("\\t", ""));
		Model model = Model.fromXml(occ, xml.toString());
		
		View view = new View(model);
		
		Time time = new Time(model, view);
		time.start();
		return time;
	}
}
