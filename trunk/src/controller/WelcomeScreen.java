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
		String[] buttons = {"Smasher", "Summoner", "Sneak", "Load Game", "Quit Game"};
		JPanel p1 = new JPanel(new GridLayout(2,3));
		
		
		for(final String s : buttons){
			
			JButton button = new JButton(s);
			button.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					synchronized(WelcomeScreen.this) {
						if(getAction() != null) {
							setAction(s);
							WelcomeScreen.this.setVisible(false);
							WelcomeScreen.this.notifyAll();
							WelcomeScreen.this.dispose();
						}
					}
				}
			});
			p1.add(button);
		}
		
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
