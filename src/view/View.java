package view;

import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;

import model.Model;
import util.Observer;
import util.Subject;
import controller.Controller;

public class View extends Thread implements Observer {
	private final Model model;
	private volatile Image dbImage = null;
	public final GameFrame frame;

	public View(Model model, Controller controller) {
		this.model = model;

		GraphicsDevice dev = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		GraphicsConfiguration gc = dev.getDefaultConfiguration();
		DisplayMode mode = new DisplayMode(1024, 768, 32,
				DisplayMode.REFRESH_RATE_UNKNOWN);
		if(dev.isDisplayChangeSupported()) {
			this.frame = new GameFrame(gc);
			dev.setDisplayMode(mode);
		} else
			this.frame = new GameFrame();

		this.frame.add(controller);
		this.frame.addMouseListener(controller);
		model.register(this);

	}

	public void run() {
		// Handlers were intended to be in their own thread
		// Not on the drawing thread.
		this.frame.setVisible(true);
		this.frame.requestFocus();
		while(!interrupted()) {
			synchronized(this) {
				try {
					this.wait();
				}
				catch(InterruptedException e) {
					interrupt();
				}
			}
			render();
			paint();
		}
	}

	private void render() {
		if(this.dbImage == null) {
			this.dbImage = this.frame.createImage(this.frame.getWidth(),
					this.frame.getHeight());
			if(this.dbImage == null) {
				System.out.println("dbImage is null");
				return;
			}
		}
		Graphics2D g = (Graphics2D) this.dbImage.getGraphics();
		// g.setColor(Color.white);
		// g.fillRect (0, 0, PWIDTH, PHEIGHT);
		// g.setColor(Color.blue);
		Drawer.getInstance().doDraw(g, this.model, this.frame.getWidth(),
				this.frame.getHeight());
		g.dispose();
	}

	private void paint() {
		try {
			Graphics g = this.frame.getGraphics();
			if(g != null && this.dbImage != null)
				g.drawImage(this.dbImage, 0, 0, null);
			Toolkit.getDefaultToolkit().sync();
			g.dispose();
		}
		catch(Exception e) {
			System.out.println("Graphics context error: " + e);
		}
	}

	public void update(Subject s) {
		Drawer.getInstance().update(s);
	}
}
