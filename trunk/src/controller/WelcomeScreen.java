package controller;

import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.ImageIcon;

import view.Console;
import model.*;
import java.awt.Color;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class WelcomeScreen extends JFrame {
	private String action = new String("");
	JPanel panel = new JPanel(new BorderLayout());
    JPanel p1 = new JPanel(new FlowLayout());
    Occupation occ = null;
    
    
	public WelcomeScreen(GraphicsConfiguration gc) {
		super("rpgeeze",gc);
		initFrame();
		
	}
	
	public WelcomeScreen(){
		super("rpgeeze");
		initFrame();
	}
	
	public void initFrame()
	{
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screen = tk.getScreenSize();
        
        
        JButton newGame = new JButton(new ImageIcon("res/img/buttons/NewGame.png"));
		newGame.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){
			synchronized(WelcomeScreen.this) {
				if(getAction() != null) {
					setAction("New");
					WelcomeScreen.this.notifyAll();
					}
				}
		}
		}
		);
		
		JButton loadGame = new JButton(new ImageIcon("res/img/buttons/LoadGame.png"));
		loadGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				synchronized(WelcomeScreen.this) {
				
					JFileChooser chooser = new JFileChooser(); 
					
					    int status = chooser.showOpenDialog(null);
					    
					    if (status == JFileChooser.APPROVE_OPTION) {
					      File selectedFile = chooser.getSelectedFile();
					      if(getAction() != null) {
								setAction("Open");
								WelcomeScreen.this.setVisible(false);
								WelcomeScreen.this.notifyAll();
								WelcomeScreen.this.dispose();
								
					      }
					    } 
					    else if (status == JFileChooser.CANCEL_OPTION) {
			                //message = "Cancel";
					    	
					    }
					
				}
			}
		  }
		);
		
		JButton quitGame = new JButton(new ImageIcon("res/img/buttons/QuitGame.png"));
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
		
	
		p1.add(newGame);
		p1.add(loadGame);
		p1.add(quitGame);

		
		p1.setBackground(new Color(254,72,72));
		panel.setBackground(new Color(254,72,72));
		panel.add(p1, BorderLayout.SOUTH);
		
		JLabel l = new JLabel(" ",new ImageIcon("res/img/IntroOccupationTypes.png"),SwingConstants.CENTER);
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
	
	public void initOcc()
	{
		JPanel p2 = new JPanel(new FlowLayout());
		JButton smasher = new JButton(new ImageIcon("res/img/buttons/Smasher.png"));
		smasher.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				synchronized(WelcomeScreen.this) {
					setOccupation(new Smasher());
					WelcomeScreen.this.setVisible(false);
					WelcomeScreen.this.notifyAll();
					WelcomeScreen.this.dispose();
					}
				}
			}
		);
		
		JButton summoner = new JButton(new ImageIcon("res/img/buttons/Summoner.png"));
		summoner.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				synchronized(WelcomeScreen.this) {
					setOccupation(new Summoner());
					WelcomeScreen.this.setVisible(false);
					WelcomeScreen.this.notifyAll();
					WelcomeScreen.this.dispose();
					}
				}
			}
		);
		
		JButton sneak = new JButton(new ImageIcon("res/img/buttons/Sneak.png"));
		sneak.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				synchronized(WelcomeScreen.this) {
					setOccupation(new Sneak());
					WelcomeScreen.this.setVisible(false);
					WelcomeScreen.this.notifyAll();
					WelcomeScreen.this.dispose();
					}
				}
			}
		);
		 p2.add(smasher);
		 p2.add(summoner);
		 p2.add(sneak);
		 p2.setBackground(new Color(254,72,72));
		 
	 panel.remove(p1);
	 panel.add(p2,BorderLayout.SOUTH);
	 panel.revalidate();
	 
	}
	
	public void setAction(String action)
	{
		this.action = action;
	}
	
	public String getAction()
	{
		return this.action;
	}
	
	public void setOccupation(Occupation occ)
	{
		this.occ = occ;
	}
	
	public Occupation getOccupation()
	{
		return occ;
	}

}
