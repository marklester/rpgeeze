package view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.*;

import model.*;
import model.items.Item;


public class InventoryView{
	
	private final int spacer = 10;
	private final int ibox_size = 30;
	private final int atHeight = 250;
	private final int inventory_width = 300; 
	
	private final int tableHeight = 12;
	private final int tableWidth = 6;
	private final int xOffset = spacer * 3;
	private final int yOffset = atHeight;
	
	private Inventory inventory;
	
	public InventoryView()
	{
		//this.inventory = inventory;
	}
	
	public void drawInventoryView(Graphics2D graphics, Inventory inv, int width,int height){
		
		int inventory_height = height;
		
		java.util.Iterator<Item> items = inv.iterator();
		//Location mouse_clicked=new Location(35,255);
		
		graphics.setColor(Color.black);
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .3f));
		graphics.fillRoundRect(0, 0, inventory_width,inventory_height, 3, 3);		
				
		graphics.setColor(Color.RED);
		graphics.fillRect(0, 0, inventory_width, 200);
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		graphics.setColor(Color.white);
		graphics.setFont(new Font("SansSerif", Font.BOLD, 24));
		graphics.drawString("Inventory", spacer * 7.5f , atHeight + spacer * 3f - spacer * 5f);
			
		
		for(int i = 0; i < tableHeight; i++){
			
			graphics.setColor( (i%2==0 ? Color.BLACK : Color.GRAY) );
			
			for(int j = 0; j < tableWidth; j++){
				Image img =null;
				if(items.hasNext()){
					Item citem = items.next();
					if(citem.toString().compareTo("Sword")==0){
						//img = this.sword;
					}
					if(citem.toString().compareTo("Potion Life")==0){
						//img Potion image
					}
				}
				int startx = (j*(ibox_size + spacer))+ xOffset;
				int starty = (i*(ibox_size + spacer))+ yOffset;
				Color prev = graphics.getColor();
				//if(mouse_clicked.getX() >= startx && mouse_clicked.getX()<=startx+ibox_size &&
				//	mouse_clicked.getY() >= starty && mouse_clicked.getY()<=starty+ibox_size){
				//	graphics.setColor(Color.YELLOW);
					//do Damage to Item
				//}
				graphics.fillRoundRect(startx, starty , ibox_size,ibox_size, 3, 3);
				if(img != null){
					graphics.drawImage(img, startx, starty, null);
				}
				graphics.setColor(prev);
			}
		}
	}
	public boolean isOnInventory(Point p)
	{
		//Logic to find if clicking was done on the inventory 
		int startx = (tableWidth  * (ibox_size + spacer)) + xOffset;
		return p.x < startx;
	}
	private Point click(Point p)
	{
		int xIndex = -1;
		int yIndex = -1;
		//figuring out the logic here
		if( p.x > xOffset && p.y > yOffset)
		{
			p.x -= xOffset;
			p.y -= yOffset;
			xIndex = (p.x % (ibox_size + spacer)) <= ibox_size ? (int)(p.x / (ibox_size + spacer)) : -1;
			yIndex = (p.y % (ibox_size + spacer)) <= ibox_size ? (int)(p.y / (ibox_size + spacer)) : -1;
		}
		
		if(xIndex == -1 || yIndex == -1 || xIndex >= tableWidth || yIndex >= tableHeight) {
			//check for a different item press
			return null;
		}
		
		return new Point(xIndex, yIndex);
	}
	
	private Item getItemAt(Point index)
	{
		return null;
	}
	
	public void leftClick(Point p)
	{
		Point index = click(p);
		Item i = null;
		if (index != null)
			i = getItemAt(p);
		Console.getInstance().writeLine( (i != null ) ? i.toString() : "No item there to equip");
	}
	public void rightClick(Point p) 
	{
		Point index = click(p);
		Item i = null;
		if (index != null)
		{
			i = getItemAt(p);
			//equip this thang!
		}
						
	}
}
