package view;

import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Point;

import model.Model;
import model.Command;
import util.Observer;
import util.Subject;
import controller.Controller;

public class View extends Thread implements Observer {
	private final Model model;
	private final GameFrame frame;
	private InventoryView inventoryView;
	private SkillView skillView;
	
	private Image dbImage = null;

	private boolean inventoryVisible = false;
	private boolean skillViewVisible = false;
	
	public View(Model model) {
		this.model = model;
		Drawer.view = this;
		inventoryView = new InventoryView();
		skillView = new SkillView();
		Drawer.getInstance().setInventoryView(inventoryView);
		Drawer.getInstance().setSkillView(skillView);
		
		
		GraphicsDevice dev = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		GraphicsConfiguration gc = dev.getDefaultConfiguration();
		DisplayMode mode = new DisplayMode(1024, 768, 32, DisplayMode.REFRESH_RATE_UNKNOWN);
		if(dev.isDisplayChangeSupported()) {
			this.frame = new GameFrame(gc);
			dev.setDisplayMode(mode);
		} else
			this.frame = new GameFrame();

		// ugly hack that works because Controller is a singleton
		Controller controller = Controller.createController(model, this);
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
		frame.dispose();
	}

	private void render() {
		if(this.dbImage == null) {
			this.dbImage = this.frame.createImage(this.frame.getWidth(), this.frame.getHeight());
			if(this.dbImage == null) {
				System.out.println("dbImage is null");
				return;
			}
		}
		Graphics2D g = (Graphics2D) this.dbImage.getGraphics();
		Drawer.getInstance().doDraw(g, this.model, this.frame.getWidth(), this.frame.getHeight());
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
	
	public boolean isInventoryVisible() {
		return inventoryVisible;
	}
	
	public boolean isSkillViewVisible() {
		return skillViewVisible;
	}
	
	public void setInventoryVisible(boolean value) {
		inventoryVisible = value;
	}
	
	public void setSkillViewVisible(boolean value) {
		skillViewVisible = value;
	}
	
	public void toggleInventoryVisible() {
		setInventoryVisible(!isInventoryVisible());
	}
	
	public void toggleSkillViewVisible() {
		setSkillViewVisible(!isSkillViewVisible());
	}
	
	public void mouseLeftClickAt(Point p) {
		
		if(isInventoryVisible() && inventoryView.isOnEquippedItems(p)) {
			Command c = inventoryView.clickEquipment(p);
			if(c == null) return;
			model.invoke(c);
		}
		else if (isSkillViewVisible() && skillView.isOnSkillView(p)) {
			Command c = skillView.click(p);
			if(c == null) return;
			model.invoke(c);
		}
		else {
			if(isInventoryVisible() && inventoryView.isOnInventory(p)) {
				final Point point = inventoryView.click(p);			
				if(point == null) return;
				final int index = point.x + point.y * 6;
				model.invoke(new Command() {
					public void execute(Model m) {
						m.getAvatar().equipActionAtIndex(index);
					}
				});
			}
		}
	}

	public void mouseRightClickAt(Point p) {		
		if(isInventoryVisible() && inventoryView.isOnInventory(p)) {
			final Point point = inventoryView.click(p);			
			if(point == null) return;
			final int index = point.x + point.y * 6;
			model.invoke(new Command() {
				public void execute(Model m) {
					m.getAvatar().dropActionAtIndex(index);
				}
			});
		}
	}
}
