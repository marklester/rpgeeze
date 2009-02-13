package view;

import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame {
	static final int PWIDTH = 500;
	static final int PHEIGHT = 400;
	
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
