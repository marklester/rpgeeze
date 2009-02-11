import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JFrame;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;


public class MapFrame extends JFrame {

    public MapFrame() {
    	super("Happy World");

      	MapPanel map = new MapPanel(this);
    	BorderLayout layout = new BorderLayout();
    	this.setLayout(layout);
      	this.add( map ,BorderLayout.CENTER); // in center
      	this.add(getMenuPanel(), BorderLayout.EAST);
    	this.setJMenuBar(getMenubar());
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setSize(995,495);
    	this.setLocation(125,100);
    	this.setResizable(false);
    	this.setVisible(true);

		/******************************************************************/
		KeyListener keyHandler = new KeyAdapter(){
            public void keyPressed(KeyEvent e) {
            		MapPanel.code = e.getKeyCode();
                	MapPanel.moving = true;
            } 
            public void keyReleased(KeyEvent e) {
				MapPanel.moving = false;
            } 
        };
        addKeyListener(keyHandler);
		/******************************************************************/

    }

	public JMenuBar getMenubar() {
	    	JMenuBar bar = new JMenuBar();
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
    	bar.add(fileMenu);
    	
    		JMenu toolMenu = new JMenu("Tools");
    		toolMenu.setMnemonic('T');
    	bar.add(toolMenu);

    		JMenu itemMenu = new JMenu("Items");
    		itemMenu.setMnemonic('I');
    	bar.add(itemMenu);
    	
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
    	bar.add(weaponMenu);
    	return(bar);
	}

    public JPanel getMenuPanel() {
        JPanel r = new JPanel();
        r.setBackground(Color.BLACK);
        BoxLayout rL = new BoxLayout(r,BoxLayout.Y_AXIS);
        r.setLayout(rL);
        Dimension ra = new Dimension(5, 0);
        r.add(Box.createRigidArea(new Dimension(100, 10)));
        /*****************************************************/
        JPanel jp = new JPanel();
        jp.setBackground(Color.BLACK);
        jp.setLayout(new BoxLayout(jp, BoxLayout.LINE_AXIS));
        jp.add(Box.createRigidArea(ra));

        JLabel p = new JLabel("Primary Stat");
        p.setForeground(Color.WHITE);
        jp.add(p);
        r.add(jp);
        r.add(Box.createVerticalStrut(5));
        /*****************************************************/
        jp = new JPanel();
        jp.setBackground(Color.BLACK);
        jp.setLayout(new BoxLayout(jp, BoxLayout.LINE_AXIS));
        jp.add(Box.createRigidArea(ra));
        p = new JLabel("LivesLeft:");
        p.setForeground(Color.WHITE);
        jp.add(p);
        jp.add(Box.createHorizontalGlue());
        r.add(jp);
        
        JLabel livesleft = new JLabel((Player.lives).toString());
        livesleft.setForeground(Color.BLUE);
        
        jp = new JPanel();
        jp.setBackground(Color.BLACK);
        jp.setLayout(new BoxLayout(jp, BoxLayout.LINE_AXIS));
        jp.add(Box.createRigidArea(ra));
        jp.add(livesleft);
        jp.add(Box.createHorizontalGlue());
        r.add(jp);
        /*****************************************************/
        jp = new JPanel();
        jp.setBackground(Color.BLACK);
        jp.setLayout(new BoxLayout(jp, BoxLayout.LINE_AXIS));
        jp.add(Box.createRigidArea(ra));
        p = new JLabel("Strength:");
        p.setForeground(Color.WHITE);
        jp.add(p);
        jp.add(Box.createHorizontalGlue());
        r.add(jp);
        
        JLabel strength = new JLabel((Player.strength).toString());
        strength.setForeground(Color.BLUE);
        
        jp = new JPanel();
        jp.setBackground(Color.BLACK);
        jp.setLayout(new BoxLayout(jp, BoxLayout.LINE_AXIS));
        jp.add(Box.createRigidArea(ra));
        jp.add(strength);
        jp.add(Box.createHorizontalGlue());
        r.add(jp);
        /*****************************************************/
        jp = new JPanel();
        jp.setBackground(Color.BLACK);
        jp.setLayout(new BoxLayout(jp, BoxLayout.LINE_AXIS));
        jp.add(Box.createRigidArea(ra));
        p = new JLabel("Agility:");
        p.setForeground(Color.WHITE);
        jp.add(p);
        jp.add(Box.createHorizontalGlue());
        r.add(jp);
        JLabel agility = new JLabel((Player.agility).toString());
        agility.setForeground(Color.BLUE);
        
        jp = new JPanel();
        jp.setBackground(Color.BLACK);
        jp.setLayout(new BoxLayout(jp, BoxLayout.LINE_AXIS));
        jp.add(Box.createRigidArea(ra));
        jp.add(agility);
        jp.add(Box.createHorizontalGlue());
        r.add(jp);
        /*****************************************************/
        jp = new JPanel();
        jp.setBackground(Color.BLACK);
        jp.setLayout(new BoxLayout(jp, BoxLayout.LINE_AXIS));
        jp.add(Box.createRigidArea(ra));
        p = new JLabel("Intellect:");
        p.setForeground(Color.WHITE);
        jp.add(p);
        jp.add(Box.createHorizontalGlue());
        r.add(jp);
        
        JLabel intellect = new JLabel((Player.intellect).toString());
        intellect.setForeground(Color.BLUE);
        
        jp = new JPanel();
        jp.setBackground(Color.BLACK);
        jp.setLayout(new BoxLayout(jp, BoxLayout.LINE_AXIS));
        jp.add(Box.createRigidArea(ra));
        jp.add(intellect);
        jp.add(Box.createHorizontalGlue());
        r.add(jp);
        /*****************************************************/
        jp = new JPanel();
        jp.setBackground(Color.BLACK);
        jp.setLayout(new BoxLayout(jp, BoxLayout.LINE_AXIS));
        jp.add(Box.createRigidArea(ra));
        p = new JLabel("Hardness:");
        p.setForeground(Color.WHITE);
        jp.add(p);
        jp.add(Box.createHorizontalGlue());
        r.add(jp);
        
        JLabel hardness = new JLabel((Player.hardness).toString());
        hardness.setForeground(Color.BLUE);
        
        jp = new JPanel();
        jp.setBackground(Color.BLACK);
        jp.setLayout(new BoxLayout(jp, BoxLayout.LINE_AXIS));
        jp.add(Box.createRigidArea(ra));
        jp.add(hardness);
        jp.add(Box.createHorizontalGlue());
        r.add(jp);
        /*****************************************************/
        jp = new JPanel();
        jp.setBackground(Color.BLACK);
        jp.setLayout(new BoxLayout(jp, BoxLayout.LINE_AXIS));
        jp.add(Box.createRigidArea(ra));
        p = new JLabel("Experience:");
        p.setForeground(Color.WHITE);
        jp.add(p);
        jp.add(Box.createHorizontalGlue());
        r.add(jp);
        
        JLabel experience = new JLabel((Player.experience).toString());
        experience.setForeground(Color.BLUE);
        
        jp = new JPanel();
        jp.setBackground(Color.BLACK);
        jp.setLayout(new BoxLayout(jp, BoxLayout.LINE_AXIS));
        jp.add(Box.createRigidArea(ra));
        jp.add(experience);
        jp.add(Box.createHorizontalGlue());
        r.add(jp);
        /*****************************************************/   
        jp = new JPanel();
        jp.setBackground(Color.BLACK);
        jp.setLayout(new BoxLayout(jp, BoxLayout.LINE_AXIS));
        jp.add(Box.createRigidArea(ra));
        p = new JLabel("Movement:");
        p.setForeground(Color.WHITE);
        jp.add(p);
        jp.add(Box.createHorizontalGlue());
        r.add(jp);
        
        JLabel movement = new JLabel((Player.movement).toString());
        movement.setForeground(Color.BLUE);
        
        jp = new JPanel();
        jp.setBackground(Color.BLACK);
        jp.setLayout(new BoxLayout(jp, BoxLayout.LINE_AXIS));
        jp.add(Box.createRigidArea(ra));
        jp.add(movement);
        jp.add(Box.createHorizontalGlue());
        r.add(jp);        
        return r;
    }
    
}