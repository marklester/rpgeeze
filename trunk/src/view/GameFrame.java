package view;

import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame {
	static final int PWIDTH = 1000;
	static final int PHEIGHT = 800;
	
	public GameFrame() {
		super("Game");
		setBackground(Color.white);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setFocusable(true);
	    setSize(new Dimension(PWIDTH, PHEIGHT));
 	    setResizable(false);
//      readyForTermination();
	}
}
