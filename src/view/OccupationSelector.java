package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import model.*;

public class OccupationSelector extends JFrame {
	private Occupation occupation = null;
	
	private static final Occupation[] occs = {
		new Smasher(),
		new Summoner(),
		new Sneak()
	};
	
    public OccupationSelector() {
    	super("Select Occupation");
    	JPanel panel = new JPanel(new GridLayout(0, 1));
    	
        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		synchronized(OccupationSelector.this) {
        			if(getOccupation() != null) {
        				OccupationSelector.this.setVisible(false);
        				OccupationSelector.this.notifyAll();
        			}
        		}
        	}
        });

        ButtonGroup group = new ButtonGroup();
        
        for(final Occupation occ: occs) {
        	JRadioButton button = new JRadioButton(occ.toString());
        	group.add(button);
        	panel.add(button);
        	button.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			OccupationSelector.this.setOccupation(occ);
        		}
        	});
        }
        
        panel.add(ok);
        
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setOpaque(true);
        
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setContentPane(panel);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }
    
    public Occupation getOccupation() {
    	return this.occupation;
    }
    
    public void setOccupation(Occupation o) {
    	this.occupation = o;
    	System.err.println("Occupation set to " + o);
    }
}



