package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4767904698909349893L;
	static int PWIDTH = 1024;
	static int PHEIGHT = 768;

	public GameFrame() {
		super("Game");
		initFrame();
		// readyForTermination();
	}

	public GameFrame(GraphicsConfiguration gc) {
		super(gc);
		initFrame();
	}

	private void initFrame() {
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
