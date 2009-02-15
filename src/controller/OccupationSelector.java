package controller;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;

import model.Occupation;
import model.PrimaryStats;
import model.Smasher;
import model.Sneak;
import model.Summoner;
import model.Stats;
import model.PrimaryStats;

public class OccupationSelector extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4277342427526928028L;

	private Occupation occupation = null;

	private static final Occupation[] occs = { new Smasher(new Stats(1,100,20,15,(new PrimaryStats(3,20,5,5,2,1)))), new Summoner(new Stats(1,100,20,15,new PrimaryStats(3,5,5,20,2,1))), new Sneak(new Stats(1,100,20,15,new PrimaryStats(3,5,20,5,2,1))) };

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
						OccupationSelector.this.dispose();
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

		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setContentPane(panel);
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}

	public Occupation getOccupation() {
		return this.occupation;
	}

	public void setOccupation(Occupation o) {
		this.occupation = o;
		System.err.println("Occupation set to " + o);
	}
}
