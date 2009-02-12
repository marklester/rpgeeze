package controller;

import view.Drawable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButtonMenuItem;

public class MenuBar extends JMenuBar implements Drawable {

	public MenuBar() {
    		JMenu fileMenu = new JMenu("File");
    		fileMenu.setMnemonic('F');
    			JMenuItem newgame = new JMenuItem("New Game");
    			newgame.setMnemonic('N');
    			fileMenu.add(newgame);
    			JMenuItem savegame = new JMenuItem("Save Game");
    			savegame.setMnemonic('S');
    			fileMenu.add(savegame);
    			fileMenu.addSeparator();
    			JMenuItem exitgame = new JMenuItem("Exit Game");
    			exitgame.setMnemonic('X');
    			fileMenu.add(exitgame);
    			exitgame.addActionListener(
					new ActionListener(){
						public void actionPerformed(ActionEvent event){
							System.exit(0);
						}
					}
				);
    	add(fileMenu);
    	
    		JMenu toolMenu = new JMenu("Tools");
    		toolMenu.setMnemonic('T');
    	add(toolMenu);

    		JMenu itemMenu = new JMenu("Items");
    		itemMenu.setMnemonic('I');
    	add(itemMenu);
    	
    		JMenu weaponMenu = new JMenu("Weapons");
    		weaponMenu.setMnemonic('W');
    		ButtonGroup weaponbutton = new ButtonGroup();
    			JRadioButtonMenuItem sword = new JRadioButtonMenuItem("Sword");
    			weaponbutton.add(sword);
    			weaponMenu.add(sword);
    			sword.setMnemonic('D');
      			JRadioButtonMenuItem bow = new JRadioButtonMenuItem("Bow");
    			weaponbutton.add(bow);
    			weaponMenu.add(bow);
    			bow.setMnemonic('B');		
    	add(weaponMenu);

		
	}

}

