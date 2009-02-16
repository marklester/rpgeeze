package controller;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.WindowConstants;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class WelcomeScreen extends JFrame {
	private String action = new String("");
	public WelcomeScreen() {
		super("RPGEEZE");
		JPanel panel = new JPanel(new BorderLayout());
		
		JButton newGame = new JButton("New Game");
		newGame.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){
			synchronized(WelcomeScreen.this) {
				if(getAction() != null) {
					setAction("New");
					WelcomeScreen.this.setVisible(false);
					WelcomeScreen.this.notifyAll();
					WelcomeScreen.this.dispose();
				}
		}
		}
		});
		
		JButton loadGame = new JButton("Load Game");
		loadGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				synchronized(WelcomeScreen.this) {
					if(getAction() != null) {
						setAction("Load");
						WelcomeScreen.this.setVisible(false);
						WelcomeScreen.this.notifyAll();
						WelcomeScreen.this.dispose();
					}
				}
			}
		});
		
		JButton quitGame = new JButton("Quit Game");
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
		
		JButton selectSmasher = new JButton("Smasher");
		selectSmasher.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				synchronized(WelcomeScreen.this) {
					if(getAction() != null) {
						setAction("Smasher");
						WelcomeScreen.this.setVisible(false);
						WelcomeScreen.this.notifyAll();
						WelcomeScreen.this.dispose();
					}
				}
			}
		});
		JButton selectSummoner = new JButton("Summoner");
		selectSummoner.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				synchronized(WelcomeScreen.this) {
					if(getAction() != null) {
						setAction("Summoner");
						WelcomeScreen.this.setVisible(false);
						WelcomeScreen.this.notifyAll();
						WelcomeScreen.this.dispose();
					}
				}
			}
		});
		JButton selectSeak = new JButton("Seak");
		selectSeak.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				synchronized(WelcomeScreen.this) {
					if(getAction() != null) {
						setAction("Seak");
						WelcomeScreen.this.setVisible(false);
						WelcomeScreen.this.notifyAll();
						WelcomeScreen.this.dispose();
					}
				}
			}
		});
		JPanel p1 = new JPanel(new GridLayout(2,3));
		
		//p1.add(newGame);
		p1.add(selectSmasher);
		p1.add(selectSummoner);
		p1.add(selectSeak);
		p1.add(loadGame);
		p1.add(quitGame);
	
		
		panel.add(p1, BorderLayout.SOUTH);
		
		JLabel l = new JLabel();
		l.setIcon(new javax.swing.ImageIcon("res/img/LoadGameBG.png"));
		panel.add(l,BorderLayout.CENTER);
		panel.setOpaque(true);
		
		
        
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setContentPane(panel);
		setResizable(false);
		
		pack();
		setSize(500,500);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void setAction(String action)
	{
		this.action = action;
	}
	
	public String getAction()
	{
		return this.action;
	}

}
