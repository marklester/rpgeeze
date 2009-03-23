package view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;

import model.entity2.*;

public class StatView implements Drawable {
	private final int menu_width = 300;
	private final int menu_height = 220;
	private final int left_indent = 20;
	private final int top_indent = 80;

	private final Image statsView;

	public StatView(Image statsView) {
		this.statsView = statsView;
	}

	//@Override
	public void draw(Drawer d) {
		// d.drawStatsView(this);
	}

	public void drawStatsView(Graphics2D graphics, PC entity, int width, int height) {

		graphics.setColor(Color.black);
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .9f));
		graphics.drawImage(this.statsView, width - this.menu_width, height - this.menu_height, null);
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		graphics.setColor(Color.black);
		graphics.setFont(new Font("SansSerif", Font.BOLD, 16));

		// Text Formatting Numbers
		int text_width = width - this.menu_width + this.left_indent;
		int text_height = height - this.menu_height + this.top_indent;
		// Freaking ugly tedious code
		int current_line = 0;
		graphics.drawString("Primary Stats", text_width, text_height);
		current_line += 18;
		graphics.drawString("Lives Left:" + entity.getStats().getPrimary().getLivesLeft(), text_width, text_height + current_line);
		current_line += 18;
		graphics.drawString("Strength:" + entity.getStats().getPrimary().getStrength(), text_width, text_height + current_line);
		current_line += 18;
		graphics.drawString("Agility:" + entity.getStats().getPrimary().getAgility(), text_width, text_height + current_line);
		current_line += 18;
		graphics.drawString("Experience:" + entity.getStats().getPrimary().getExperience(), text_width, text_height + current_line);
		current_line += 18;
		graphics.drawString("Hardiness:" + entity.getStats().getPrimary().getHardiness(), text_width, text_height + current_line);
		current_line += 18;
		graphics.drawString("Intellect:" + entity.getStats().getPrimary().getIntellect(), text_width, text_height + current_line);
		current_line += 18;
		graphics.drawString("Movement:" + (30 - entity.getStats().getMovement()), text_width, text_height + current_line);
		current_line += 18;
		//graphics.drawString("nanoTime:" + System.nanoTime(), text_width, text_height + current_line);
		// More Ugly Code for the Second Column of Stats this will show Derived
		// Stats
		text_width = width - this.menu_width / 2;
		current_line = 0;
		graphics.drawString("Derived Stats ", text_width, text_height);
		current_line += 18;
		graphics.drawString("Level:" + entity.getStats().getLevel(), text_width, text_height + current_line);
		current_line += 18;
		graphics.drawString("HP(Life):" + entity.getStats().getLife(), text_width, text_height + current_line);
		current_line += 18;
		graphics.drawString("Mana:" + entity.getStats().getMana(), text_width, text_height + current_line);
		current_line += 18;
		graphics.drawString("Offensive:" + entity.getStats().getOffensiveRating(), text_width, text_height + current_line);
		current_line += 18;
		graphics.drawString("Defensive:" + entity.getStats().getDefensiveRating(), text_width, text_height + current_line);
		current_line += 18;
		graphics.drawString("Armor Rating:" + entity.getStats().getArmorRating(), text_width, text_height + current_line);
		current_line += 18;
	}
	

}