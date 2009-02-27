package view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import util.Iterator;
import util.ResourceLoader;

import model.Entity;
import model.item.Item;
import model.Model;
import model.Command;
import model.Equipment;

public class InventoryView {

	private final int spacer = 10;
	private final int ibox_size = 30;
	private final int atHeight = 250;
	private final int inventory_width = 300;

	private final int tableHeight = 12;
	private final int tableWidth = 6;
	private final int xOffset = this.spacer * 3;
	private final int yOffset = this.atHeight;
	
	final private int chest_x = 125;
	final private int chest_y = 60;
	final private int width = 40;
	final private int height = 40;
	
	final private Point chest = new Point(chest_x, chest_y);
	final private Point head = new Point(chest_x, chest_y - height - spacer);
	final private Point feet = new Point(chest_x, chest_y + height + spacer + spacer + spacer);
	final private Point aux = new Point(chest_x - width - spacer/2, chest_y);
	final private Point weapon = new Point(chest_x + width + spacer/2, chest_y);

	public InventoryView() {
		// this.inventory = inventory;
	}

	public void drawInventoryView(Graphics2D graphics, Entity e, int width, int height) {

		int inventory_height = height;

		Iterator<Item> items = e.getInventory().iterator();
		items.reset();
		// Location mouse_clicked=new Location(35,255);

		drawEquippedItems(graphics, e);
		
		graphics.setColor(Color.black);
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .3f));
		graphics.fillRect(0, 0, this.inventory_width, inventory_height);
		
		
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		graphics.setColor(Color.white);
		graphics.setFont(new Font("SansSerif", Font.BOLD, 24));
		graphics.drawString("Inventory", this.spacer * 7.5f, this.atHeight + this.spacer * 3f - this.spacer * 5f);

		for(int i = 0; i < this.tableHeight; i++) {

			graphics.setColor((i % 2 == 0 ? Color.BLACK : Color.GRAY));

			for(int j = 0; j < this.tableWidth; j++) {
				Image img = null;
				if(!items.isDone()) {
					Item citem = items.current();
					img =ResourceLoader.getInstance().getItemImage(citem.toString());
					items.advance();
				}
				int startx = j * (this.ibox_size + this.spacer) + this.xOffset;
				int starty = i * (this.ibox_size + this.spacer) + this.yOffset;
				Color prev = graphics.getColor();

				graphics.fillRoundRect(startx, starty, this.ibox_size, this.ibox_size, 3, 3);
				if(img != null)
					graphics.drawImage(img, startx, starty, null);
				graphics.setColor(prev);
			}
		}
	}

	public void drawEquippedItems(Graphics2D graphics, model.Entity e)
	{
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .3f));
		//Equip Area
		graphics.setColor(Color.RED);
		graphics.fillRect(0, 0, this.inventory_width, 200);
		graphics.setColor(Color.BLACK);
		
		//CHEST
		graphics.fillRect(chest.x, chest.y, width, height + spacer + spacer);
		//HEAD
		graphics.fillRect( head.x, head.y, width, height);
		//FEET
		graphics.fillRect(feet.x, feet.y, width, height);		
		//WEAPON
		graphics.fillRect(weapon.x, weapon.y,  width, height);
		//AUXILIARY
		graphics.fillRect(aux.x, aux.y,  width, height);
		
		
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		
		Image img;
		
		Equipment eq = e.getEquipment();
		eq = e.getEquipment().clone();
			
		//HEAD
		if(eq.head != null)
		{
			img = ResourceLoader.getInstance().getItemImage(eq.head.toString());
			graphics.drawImage(img, head.x, head.y, null);
		}
		if(eq.armor != null)
		{
			img = ResourceLoader.getInstance().getItemImage(eq.armor.toString());
			graphics.drawImage(img, chest.x, chest.y, null);
		}
		if(eq.boots != null)
		{
			img = ResourceLoader.getInstance().getItemImage(eq.boots.toString());
			graphics.drawImage(img, feet.x, feet.y, null);
		}
		if(eq.weapon != null)
		{
			img = ResourceLoader.getInstance().getItemImage(eq.weapon.toString());
			graphics.drawImage(img, weapon.x, weapon.y, null);
		}
		if(eq.auxiliary != null) {
			img = ResourceLoader.getInstance().getItemImage(eq.auxiliary.toString());
			graphics.drawImage(img, aux.x, aux.y, null);
		}
	}
	
	public boolean isOnInventory(Point p) {
		// Logic to find if clicking was done on the inventory
		int startx = this.tableWidth * (this.ibox_size + this.spacer) + this.xOffset;
		return p.x < startx;
	}
	public boolean isOnEquippedItems(Point p) 	{
		//int starty = this.tableHeight * (this.ibox_size + this.spacer);
		return (isOnInventory(p) && p.y < atHeight);
	}

	public Point click(Point p) {
		int xIndex = -1;
		int yIndex = -1;
		// figuring out the logic here
		if(p.x > this.xOffset && p.y > this.yOffset) {
			p.x -= this.xOffset;
			p.y -= this.yOffset;
			xIndex = p.x % (this.ibox_size + this.spacer) <= this.ibox_size ? (int) (p.x / (this.ibox_size + this.spacer)) : -1;
			yIndex = p.y % (this.ibox_size + this.spacer) <= this.ibox_size ? (int) (p.y / (this.ibox_size + this.spacer)) : -1;
		}

		if(xIndex == -1 || yIndex == -1 || xIndex >= this.tableWidth || yIndex >= this.tableHeight)
			// check for a different item press
			return null;
		return new Point(xIndex, yIndex);
	}

	public Command clickEquipment(Point p) {
		Command ret = null;
		if(p.x > chest.x && p.x < chest.x + width && p.y > chest.y && p.y < chest.y + height + spacer + spacer) {
			ret = new Command() {
				public void execute(Model m) {
					m.getAvatar().unequipArmor();
				}
			};
		}
		else if(p.x > head.x && p.x < head.x + width && p.y > head.y && p.y < head.y + height) {
			ret = new Command() {
				public void execute(Model m) {
					m.getAvatar().unequipHead();
				}
			};
		}
		else if(p.x > feet.x && p.x < feet.x + width && p.y > feet.y && p.y < feet.y + height) {
			ret = new Command() {
				public void execute(Model m) {
					m.getAvatar().unequipBoots();
				}
			};
		}
		else if(p.x > weapon.x && p.x < weapon.x + width && p.y > weapon.y && p.y < weapon.y + height) {
			ret = new Command() {
				public void execute(Model m) {
					m.getAvatar().unequipWeapon();
				}
			};
		}
		else if(p.x > aux.x && p.x < aux.x + width && p.y > aux.y && p.y < aux.y + height) {
			ret = new Command() {
				public void execute(Model m) {
					m.getAvatar().unequipAuxiliary();
				}
			};
		}
		return ret;
	}
}
