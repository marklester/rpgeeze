package controller;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.Occupation;
import model.Smasher;
import model.Summoner;
import model.Sneak;
import util.ResourceLoader;

public class WelcomeScreen extends JFrame {
	public Scanner scanner = null;
	
	private String action = new String("");
	private JPanel panel = new JPanel(new BorderLayout());
	private JPanel p1 = new JPanel(new FlowLayout());

	private Occupation occ = null;
    private Color mainScreenColor = new Color(254,72,72);
    private Border buttonBorder = new LineBorder(mainScreenColor, 0);
    
	public WelcomeScreen(GraphicsConfiguration gc) {
		super("rpgeeze",gc);
		initFrame();
		
	}
	
	public WelcomeScreen(){
		super("rpgeeze");
		initFrame();
	}
	
	public void initFrame() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screen = tk.getScreenSize();
				
        JButton newGame = new JButton(new ImageIcon(ResourceLoader.getInstance().getImage("img/buttons/NewGame.png")));
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				synchronized(WelcomeScreen.this) {
					if(getAction() != null) {
						setAction("New");
						WelcomeScreen.this.notifyAll();
					}
				}
			}
		});
		newGame.setOpaque(false);
		newGame.setContentAreaFilled(false);
		newGame.setBorderPainted(false);
		newGame.setBorder(buttonBorder);
		
		JButton loadGame = new JButton(new ImageIcon(ResourceLoader.getInstance().getImage("img/buttons/LoadGame.png")));
		loadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				synchronized(WelcomeScreen.this) {
					JFileChooser chooser = new JFileChooser(); 
					chooser.addChoosableFileFilter(new GameFilter());
					int status = chooser.showOpenDialog(null);
					    
					if(status == JFileChooser.APPROVE_OPTION) {
						File selectedFile = chooser.getSelectedFile();
					    if(getAction() != null) {
					    	setAction("Open");
							WelcomeScreen.this.setVisible(false);
							WelcomeScreen.this.notifyAll();
							WelcomeScreen.this.dispose();
							try {
								scanner = new Scanner(selectedFile);
							}
							catch(FileNotFoundException ex) {
							}
							if(scanner != null) {
								WelcomeScreen.this.notifyAll();
							}
					    }
					} 
					else if(status == JFileChooser.CANCEL_OPTION) {
						//message = "Cancel";					    	
					}
				}
			}
		});
		loadGame.setOpaque(false);
		loadGame.setContentAreaFilled(false);
		loadGame.setBorderPainted(false);
		loadGame.setBorder(buttonBorder);
		
		JButton quitGame = new JButton(new ImageIcon(ResourceLoader.getInstance().getImage("img/buttons/QuitGame.png")));
		quitGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				synchronized(WelcomeScreen.this) {
					if(getAction() != null) {
						setAction("Quit");
						WelcomeScreen.this.setVisible(false);
						WelcomeScreen.this.notifyAll();
						WelcomeScreen.this.dispose();
					}
				}
			}
		});
		quitGame.setOpaque(false);
		quitGame.setContentAreaFilled(false);
		quitGame.setBorderPainted(false);
		quitGame.setBorder(buttonBorder);
			
		p1.add(newGame);
		p1.add(loadGame);
		p1.add(quitGame);

		p1.setBackground(mainScreenColor);
		panel.setBackground(mainScreenColor);
		panel.add(p1, BorderLayout.SOUTH);
		
		JLabel l = new JLabel(" ", new ImageIcon(ResourceLoader.getInstance().getImage("img/IntroOccupationTypes.png")), SwingConstants.CENTER);
		panel.add(l,BorderLayout.CENTER);
		panel.setOpaque(true);
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setContentPane(panel);
		setResizable(false);
		setUndecorated(true);
		pack();
		setSize(new Dimension(screen.width, screen.height));
		
		setVisible(true);		
	}
	
	public void initOcc() {
		JPanel p2 = new JPanel(new FlowLayout());
		
		for(final Occupation occ: new Occupation[] {new Smasher(), new Summoner(), new Sneak()}) {
			JButton button = new JButton(new ImageIcon(ResourceLoader.getInstance().getImage("img/buttons/" + occ.toString() + ".png")));
			button.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					synchronized(WelcomeScreen.this) {
						setOccupation(occ);
						WelcomeScreen.this.notifyAll();
					}
				}
			});
			button.setBackground(mainScreenColor);
			button.setBorderPainted(false);
			button.setBorder(buttonBorder);
			button.setContentAreaFilled(false);
			p2.add(button);
		}
		
		p2.setBackground(new Color(254, 72, 72));
		 
		panel.remove(p1);
		panel.add(p2, BorderLayout.SOUTH);
		panel.revalidate();
	}
	
	public void setAction(String action) {
		this.action = action;
	}
	
	public String getAction() {
		return this.action;
	}
	
	public void setOccupation(Occupation occ) {
		this.occ = occ;
	}
	
	public Occupation getOccupation() {
		return occ;
	}
}
