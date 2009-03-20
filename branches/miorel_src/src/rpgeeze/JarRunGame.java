package rpgeeze;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class JarRunGame {
	public static void main(String[] arg) throws IOException {
		List<String> command = new ArrayList<String>();
		for(String s: arg)
			command.add(s);
		if(System.getProperty("os.name").equals("Windows"))
			command.add(0, "RunGame");
		else
			command.add(0, "./RunGame.sh");
//		Runtime.getRuntime().exec(command.toArray(new String[0]));
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e) {
		}
		JOptionPane.showMessageDialog(null, "Hello World!", "rpgeeze", JOptionPane.ERROR_MESSAGE, null);
	}
}
