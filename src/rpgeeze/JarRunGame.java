package rpgeeze;

import java.util.List;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * Entry point for execution via the jar file. 
 * 
 */
public class JarRunGame {
	private JarRunGame() {
	}
	
	/**
	 * Spawns a process that runs the proper system-specific script instead.
	 * 
	 * @param arg command-line options
	 */
	public static void main(String[] arg) {
		List<String> command = new ArrayList<String>();
		if(System.getProperty("os.name").equals("Windows"))
			command.add("RunGame");
		else
			command.add("RunGame.sh");
		for(String s: arg)
			command.add(s);
		try {
			Runtime.getRuntime().exec(command.toArray(new String[0]));
		}
		catch(Exception e0) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}
			catch(Exception e1) {
			}
			JOptionPane.showMessageDialog(null, "ERROR: " + e0.getMessage(), "rpgeeze", JOptionPane.ERROR_MESSAGE, null);
		}
	}
}
