package view;

import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame {
	static int PWIDTH = 1000;
	static int PHEIGHT = 800;
	
	public GameFrame() {
		super("Game");	
		initFrame();
//      readyForTermination();
	}
	public GameFrame(GraphicsConfiguration gc)
	{
		super(gc);
		initFrame();
	}
	
	private void initFrame()
	{
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screen = tk.getScreenSize();
		
		PWIDTH = screen.width;
		PHEIGHT = screen.height;
		
		setBackground(Color.white);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setFocusable(true);
	    setSize(new Dimension(PWIDTH, PHEIGHT));
	    setUndecorated(true);
 	    setResizable(false);
 	    setVisible(true);
	}
}
