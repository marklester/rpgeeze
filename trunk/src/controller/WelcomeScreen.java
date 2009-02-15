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
		JPanel p1 = new JPanel(new GridLayout(1,3));
		
		p1.add(newGame);
		p1.add(loadGame);
	
		
		panel.add(p1, BorderLayout.SOUTH);
		
		JLabel l = new JLabel();
		l.setIcon(new javax.swing.ImageIcon("res/img/LoadGameBG.png"));
		panel.add(l,BorderLayout.CENTER);
		panel.setOpaque(true);
		
		
        
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setContentPane(panel);
		setResizable(false);
		setLocationRelativeTo(null);
		pack();
		setSize(500,500);
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
