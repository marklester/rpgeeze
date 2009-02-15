package view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import model.Inventory;
import model.items.Item;

import util.Iterator;

public class InventoryView {

	private final int spacer = 10;
	private final int ibox_size = 30;
	private final int atHeight = 250;
	private final int inventory_width = 300;

	private final int tableHeight = 12;
	private final int tableWidth = 6;
	private final int xOffset = this.spacer * 3;
	private final int yOffset = this.atHeight;

	public InventoryView() {
		// this.inventory = inventory;
	}

	public void drawInventoryView(Graphics2D graphics, Inventory inv, int width, int height) {

		int inventory_height = height;

		Iterator<Item> items = inv.iterator();
		items.reset();
		// Location mouse_clicked=new Location(35,255);

		graphics.setColor(Color.black);
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .3f));
		graphics.fillRoundRect(0, 0, this.inventory_width, inventory_height, 3, 3);

		graphics.setColor(Color.RED);
		graphics.fillRect(0, 0, this.inventory_width, 200);
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		graphics.setColor(Color.white);
		graphics.setFont(new Font("SansSerif", Font.BOLD, 24));
		graphics.drawString("Inventory", this.spacer * 7.5f, this.atHeight + this.spacer * 3f - this.spacer * 5f);

		for(int i = 0; i < this.tableHeight; i++) {

			graphics.setColor((i % 2 == 0 ? Color.BLACK : Color.GRAY));

			for(int j = 0; j < this.tableWidth; ++j) {
				Image img = null;
				if(!items.isDone()) {
					Item citem = items.current();
					if(citem.toString().compareTo("Sword") == 0) {
						// img = this.sword;
					}
					if(citem.toString().compareTo("Potion Life") == 0) {
						// img Potion image
					}
					items.advance();
				}
				int startx = j * (this.ibox_size + this.spacer) + this.xOffset;
				int starty = i * (this.ibox_size + this.spacer) + this.yOffset;
				Color prev = graphics.getColor();
				// if(mouse_clicked.getX() >= startx &&
				// mouse_clicked.getX()<=startx+ibox_size &&
				// mouse_clicked.getY() >= starty &&
				// mouse_clicked.getY()<=starty+ibox_size){
				// graphics.setColor(Color.YELLOW);
				// do Damage to Item
				// }
				graphics.fillRoundRect(startx, starty, this.ibox_size, this.ibox_size, 3, 3);
				if(img != null)
					graphics.drawImage(img, startx, starty, null);
				graphics.setColor(prev);
			}
		}
	}

	public boolean isOnInventory(Point p) {
		// Logic to find if clicking was done on the inventory
		int startx = this.tableWidth * (this.ibox_size + this.spacer) + this.xOffset;
		return p.x < startx;
	}

	private Point click(Point p) {
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

	private Item getItemAt(Point index) {
		return null;
	}

	public void leftClick(Point p) {
		Point index = click(p);
		Item i = null;
		if(index != null)
			i = getItemAt(p);
		Console.getInstance().writeLine(i != null ? i.toString() : "No item there to equip");
	}

	public void rightClick(Point p) {
		Point index = click(p);
		Item i = null;
		if(index != null)
			i = getItemAt(p);
		// equip this thang!

	}
}
