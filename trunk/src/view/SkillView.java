package view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import util.ResourceLoader;
import java.util.List;


import model.Model;
import model.Command;
import model.skill.*;

public class SkillView {
	
	private final int spacer = 10;
	private final int view_width = 300;
	private int view_height = 600;
	private int boxHeight = 150;

	private final int tableHeight = 12;
	private final int tableWidth = 6;
	private final int textOffset = 30;
	private final int descrHeight = 200;


	public SkillView() {
	}

	public void drawSkillView(Graphics2D graphics, SkillContainer s, int width, int height) {

		java.util.Iterator<Skill> skills = s.iterator();
		view_height = height;
		boxHeight = view_height / s.size() - ((textOffset+descrHeight) / s.size());
		
		graphics.setColor(Color.black);
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .3f));
		graphics.fillRect(0, 0, this.view_width, view_height);
		
		
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		graphics.setColor(Color.white);
		graphics.setFont(new Font("SansSerif", Font.BOLD, 24));
		graphics.drawString("Skills Available", this.spacer * 7, textOffset-5);

		int j=0;
		for(int i = 0; i < this.tableHeight; i++) {

			graphics.setColor((i % 2 == 0 ? Color.BLACK : Color.GRAY));
			Image icon = ResourceLoader.getInstance().getItemImage("Skill Button");
			
			for(; j < this.tableWidth; j++) {
				graphics.setColor(Color.RED);
				int topOfBox = j*boxHeight+textOffset;
				graphics.drawRect(0, topOfBox, view_width, boxHeight);
				if(skills.hasNext()) {
					Skill cSkill = skills.next();
					graphics.setFont(new Font("SansSerif", Font.BOLD, 18));
					graphics.drawString(cSkill.toString(), spacer, topOfBox + 20);
					
					graphics.drawImage(icon, spacer, topOfBox + icon.getHeight(null), null);
					graphics.drawString(cSkill.getPoints() + " / 100", spacer + 200, topOfBox+20);
					
				}
			}
		}
		
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .3f));
		//Description Area
		graphics.setColor(Color.RED);
		graphics.fillRect(0, j*boxHeight+textOffset, view_width, descrHeight);
		
		
	}
	

	
	public boolean isOnSkillView(Point p) {
		// Logic to find if clicking was done on the skill view
		return p.x < view_width;
	}

	public Command click(Point p) {
		Command ret = null;
		// figuring out the logic here
		if(p.y > textOffset && p.y < (view_height - descrHeight) ) {
			final int index = (int) p.y / boxHeight;
			ret = new Command() {
				public void execute(Model m) {
					try { m.getAvatar().getSkillAt(index).incPoints(); }
					catch (MaxPointsAllocatedException e) {}
				}
			};
		}
		return ret;
	}
	
	public Command rClick(Point p) {
		Command ret = null;
		// figuring out the logic here
		if(p.y > textOffset && p.y < (view_height - descrHeight) ) {
			final int index = (int) p.y / boxHeight;
			ret = new Command() {
				public void execute(Model m) {					
					Skill s = m.getAvatar().getSkillAt(index); 
					UseSkillVisitor myVisitor = new UseSkillVisitor(m.getAvatar());
					if (s instanceof UsableSkill)
						((UsableSkill) s).accept(myVisitor);
				}
			};
		}
		return ret;
	}

}
