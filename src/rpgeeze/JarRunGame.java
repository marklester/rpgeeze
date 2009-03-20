package rpgeeze;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class JarRunGame {
	public static void main(String[] arg) throws IOException {
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
