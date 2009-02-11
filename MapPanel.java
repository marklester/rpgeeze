import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.io.BufferedInputStream;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import bin.resource.images;

public class MapPanel extends JPanel {

	Image image[] = new Image[26];//Import JPEGs
	int movement = 10;//10 pixel movement every 50ms
	static int code = 0;//Value of key pressed
	static boolean moving = false; //Value to stop player when key is released
	static boolean stop = false;//Pauses movement until battle is over
	static final int grid = 50;//pixel size of grid
	static final int shiftx = 14;//x shift to view player entity
	static final int shifty = 7;//y shidt to view player entity
	static int level = 1;
		 					//1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15,16,17,17,18,19,20,21,22,23,24,25,26,27,28,29,30,32,33,34,35,36,37,38,39,40,41,42,43,44
	Integer map1[][] =    { {95,95,95,95,95,95,95,95,95,95,95,95,95,99,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95},//1
   						 	{99,99,95,95,95,95,95,95,95,95,95,99,-7,-1,-6,99,95,95,95,95,95,95,95,95,95,95,99,99,99,99,99,99,95,95,95,95,95,95,95,95,95,95,95,95},//2
   						 	{-1,-1,-6,99,99,99,99,99,99,99,-7,-1,-1,-1,-1,-1,-6,99,99,99,99,99,99,99,99,-7,-1,-1,-1,-1,-1,-1,-6,95,95,95,95,95,95,95,95,95,95,95},//3
   						 	{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-6,95,95,95,95,95,95,95,95,95,95},//4
   						 	{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-6,95,95,95,95,95,95,95,95,95},//5
   						 	{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,98,95,95,95,95,95,95,95,95},//6
   						 	{-1,-1,-1,-1,-1,-1,82,84,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-6,95,95,95,95,95,95,95,95},//7
   						 	{-1,-1,-1,-1,-1,-1,85,83,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-6,95,95,95,95,95,95,95},//8
   						 	{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-6,95,95,95,95,95,95},//9
						 	{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,82,91,-4,91,84,-1,-1,-1,-1,-1,-1,-1,98,95,95,95,95,95},//10
							{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-8,96,96,-9,-1,-1,-1,-1,-1,-1,-1,-1,-1,93,94,94,94,92,-1,-1,-1,-1,-1,-1,-1,-6,99,95,95,95,95},//11
							{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-8,96,95,95,95,97,-1,-1,-1,-1,-1,-1,-1,-1,-1,-2,94,94,94,-3,-1,-1,-1,-1,-1,-1,-1,-1,-1,-6,99,95,95},//12
							{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,98,95,95,95,95,-7,-1,-1,-1,-1,-1,-1,-1,-1,-1,93,94,94,94,92,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-6,95},//13
						 	{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,98,95,95,95,-7,-1,-1,-1,-1,-1,82,84,-1,-1,-1,85,90,-5,90,83,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-6},//14
						 	{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-6,99,99,-7,-1,-1,-1,-1,-1,82,89,86,91,84,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//15
						 	{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,93,94,94,94,92,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//16
						 	{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,85,88,87,90,83,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//17
						 	{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,85,83,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//18
						 	{-9,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//19
						 	{95,96,96,96,-9,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//20
						 	{95,95,95,95,95,96,96,-9,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//21
						 	{95,95,95,95,95,95,95,95,-9,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}};//22					
			 				//1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15,16,17,17,18,19,20,21,22,23,24,25,26,27,28,29,30,32,33,34,35,36,37,38,39,40,41,42,43,44
	Integer map2[][] =    { {87,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,88},//1
   						 	{92,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,93},//2
   						 	{92,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,93},//3
   						 	{92,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,93},//4
   						 	{92,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,93},//5
   						 	{92,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,93},//6
   						 	{92,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,93},//7
   						 	{92,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,93},//8
   						 	{92,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,93},//9
						 	{92,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,93},//10
							{92,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,82,91,91,91,84,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,93},//11
							{92,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,85,90,-5,90,83,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,93},//12
							{92,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,93},//13
						 	{92,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,93},//14
						 	{92,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,93},//15
						 	{92,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,93},//16
						 	{92,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,93},//17
						 	{92,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,93},//18
						 	{92,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,93},//19
						 	{92,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,93},//20
						 	{92,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,93},//21
						 	{86,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,89}};//22					
	
			 						//1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15,16,17,17,18,19,20,21,22,23,24,25,26,27,28,29,30,32,33,34,35,36,37,38,39,40,41,42,43,44
	Integer map3[][] =    { {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//1
   						 	{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//2
   						 	{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//3
   						 	{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//4
   						 	{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//5
   						 	{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//6
   						 	{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//7
   						 	{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//8
   						 	{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//9
						 	{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//10
							{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//11
							{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//12
							{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//13
						 	{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//14
						 	{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//15
						 	{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//16
						 	{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//17
						 	{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//18
						 	{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//19
						 	{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//20
						 	{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//21
						 	{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}};//22					


	int mapx = 0;
	int mapy = 0;


   	MapBadGuy badguy[] = new MapBadGuy[4];
   	Point position = new Point(24,20);;
   	Point Offset;
	MapFrame frame;

    public MapPanel(MapFrame k) {
    	frame = k;
    	setBackground(Color.BLACK);
		for(Integer i = 0; i < image.length;i++)
			image[i] = loadImage(i.toString() + ".jpg");		
		initLevel();
		PlayerThread p = new PlayerThread();
		p.start();
		BattleThread b = new BattleThread();
		b.start();
		PaintThread paint = new PaintThread();
		paint.start();
    }
    
    static Image loadImage(String imageName) {
        try {
            Image image = ImageIO.read(new BufferedInputStream(new images().getClass().getResourceAsStream(imageName)));
            return image;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }
    }
    
   	public void paintComponent( Graphics g )
   	{
    	super.paintComponent( g );
    	getTerrain(g);
    	g.setColor(Color.BLACK);
		for(int j = 0; j < badguy.length;j++)
      		badguy[j].getPlayer(g);
		getPlayer(g);
   }
	
    public void getPlayer(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillOval(22*grid-grid*shiftx, 11*grid-grid*shifty, grid, grid);
		g.setColor(Color.BLACK);
		g.fillOval(22*grid-grid*shiftx+10, 11*grid-grid*shifty+10, 10, 10);
		g.fillOval(22*grid-grid*shiftx+30, 11*grid-grid*shifty+10, 10, 10);
		g.fillOval(22*grid-grid*shiftx+10, 11*grid-grid*shifty+30, 30, 8);
		g.fillOval(22*grid-grid*shiftx+9, 11*grid-grid*shifty+27, 8, 8);
		g.fillOval(22*grid-grid*shiftx+33, 11*grid-grid*shifty+27, 8, 8);
    }

  	public void getGrid(Graphics g) {
  		for(int i = grid; i < 44*grid; i = i+grid)
  			g.drawLine(i, 0, i, 440);
  		for(int j = grid; j < 22*grid; j = j+grid)
  			g.drawLine(0, j, 880, j);
  		
  	}

   	public void getTerrain(Graphics g) {
   		Integer map[][] = getLevel();
   		int yl = position.y-5;
   		int yh = position.y+6;
   		int xl = position.x-9;
   		int xh = position.x+11;
   		if(yl < 0) yl = 0;
   		if(yh > mapy) yh = mapy;
   		if(xl < 0) xl = 0;
   		if(xh > mapx) xh = mapx;
   		for(int k = yl; k < yh; k++) {
   			for(int j = xl; j < xh; j++) {
   				switch(map[k][j]) {
   					case-1:g.drawImage(image[8],(j)*grid-Offset.x,(k)*grid-Offset.y,grid,grid,this);
   						break;
   					case 95:g.setColor(Color.BLUE);g.fillRect((j)*grid-Offset.x,(k)*grid-Offset.y,grid,grid);
   						break;
   					case-6:g.drawImage(image[0],(j)*grid-Offset.x,(k)*grid-Offset.y,grid,grid,this);
   						break;
   					case-7:g.drawImage(image[1],(j)*grid-Offset.x,(k)*grid-Offset.y,grid,grid,this);
   						break;
   					case-9:g.drawImage(image[2],(j)*grid-Offset.x,(k)*grid-Offset.y,grid,grid,this);
   						break;
   					case-8:g.drawImage(image[3],(j)*grid-Offset.x,(k)*grid-Offset.y,grid,grid,this);
   						break;
   					case 99:g.drawImage(image[4],(j)*grid-Offset.x,(k)*grid-Offset.y,grid,grid,this);
   						break;  
   					case 96:g.drawImage(image[5],(j)*grid-Offset.x,(k)*grid-Offset.y,grid,grid,this);
   						break;
   					case 97:g.drawImage(image[6],(j)*grid-Offset.x,(k)*grid-Offset.y,grid,grid,this);
   						break;
   					case 98:g.drawImage(image[7],(j)*grid-Offset.x,(k)*grid-Offset.y,grid,grid,this);
   						break;
   					case 94:g.drawImage(image[9],(j)*grid-Offset.x,(k)*grid-Offset.y,grid,grid,this);
   						break;
   					case 93:g.drawImage(image[14],(j)*grid-Offset.x,(k)*grid-Offset.y,grid,grid,this);
   						break;
   					case 92:g.drawImage(image[15],(j)*grid-Offset.x,(k)*grid-Offset.y,grid,grid,this);
   						break;
   					case 91:g.drawImage(image[16],(j)*grid-Offset.x,(k)*grid-Offset.y,grid,grid,this);
   						break;
   					case 90:g.drawImage(image[17],(j)*grid-Offset.x,(k)*grid-Offset.y,grid,grid,this);
   						break;
   					case 82:g.drawImage(image[18],(j)*grid-Offset.x,(k)*grid-Offset.y,grid,grid,this);
   						break;
   					case 83:g.drawImage(image[19],(j)*grid-Offset.x,(k)*grid-Offset.y,grid,grid,this);
   						break;
   					case 84:g.drawImage(image[20],(j)*grid-Offset.x,(k)*grid-Offset.y,grid,grid,this);
   						break;
   					case 85:g.drawImage(image[21],(j)*grid-Offset.x,(k)*grid-Offset.y,grid,grid,this);
   						break;
   					case 87:g.drawImage(image[10],(j)*grid-Offset.x,(k)*grid-Offset.y,grid,grid,this);
   						break;
   					case 88:g.drawImage(image[11],(j)*grid-Offset.x,(k)*grid-Offset.y,grid,grid,this);
   						break;
   					case 89:g.drawImage(image[12],(j)*grid-Offset.x,(k)*grid-Offset.y,grid,grid,this);
   						break;
   					case 86:g.drawImage(image[13],(j)*grid-Offset.x,(k)*grid-Offset.y,grid,grid,this);
   						break;			
   					case -2:g.drawImage(image[22],(j)*grid-Offset.x,(k)*grid-Offset.y,grid,grid,this);
   						break;
   					case -3:g.drawImage(image[23],(j)*grid-Offset.x,(k)*grid-Offset.y,grid,grid,this);
   						break;
   					case -4:g.drawImage(image[24],(j)*grid-Offset.x,(k)*grid-Offset.y,grid,grid,this);
   						break;
   					case -5:g.drawImage(image[25],(j)*grid-Offset.x,(k)*grid-Offset.y,grid,grid,this);
   						break;				
   					//case 8:g.setColor(Color.DARK_GRAY);g.fillOval((j)*20,(k)*20,40,40);
   					//	break;

   					//case 9:g.setColor(Color.CYAN);g.fillRect((j)*20,(k)*20,20,20);
   					//	break;   						
   					default:
   						System.out.println("Error, non-value terrain at " + "("+k+","+j+")");
   						System.exit(1);
   				}
   			}
   		}
   		
   	} 	
   	
	public class PlayerThread extends Thread {
		public void run() {
        char direction = 'S';
    		while(true){
    			if(moving) {
    				Point temp = new Point(position.x,position.y);
    				if(code == KeyEvent.VK_W | code == KeyEvent.VK_UP) {
    					direction = 'W';
    					position.y--;
       				} else if(code == KeyEvent.VK_D | code == KeyEvent.VK_RIGHT) {
       					direction = 'D';
       					position.x++;
        			} else if(code == KeyEvent.VK_X | code == KeyEvent.VK_DOWN) {
        				direction = 'X';
        			   	position.y++;
        			} else if(code == KeyEvent.VK_A | code == KeyEvent.VK_LEFT) {
        				direction = 'A';
        				position.x--;
        			} else if(code == KeyEvent.VK_Q) {
        				direction = 'Q';
        				position.x--;position.y--;
        			} else if(code == KeyEvent.VK_E) {
        				direction = 'E';
        			   	position.x++;position.y--;
        			} else if(code == KeyEvent.VK_Z) {
        				direction = 'Z';
        			   	position.x--;position.y++;
        			} else if(code == KeyEvent.VK_C) {
        				direction = 'C';
        			   	position.x++;position.y++;
        			} else {direction = 'S';}					
					Integer map[][] = getLevel();
					if(position.x < 44 && position.x >= 0 && position.y < 22 && position.y >= 0 && map[position.y][position.x] <= 50) {
						
						for(int i = 0; i < grid;i = i + movement) {
							switch(direction) {
								case 'W':	Offset.y = Offset.y-movement;
									break;
								case 'D':	Offset.x = Offset.x+movement;
									break;
								case 'X':	Offset.y = Offset.y+movement;
									break;
								case 'A':	Offset.x = Offset.x-movement;
									break;
								case 'Q':	Offset.x = Offset.x-movement;
											Offset.y = Offset.y-movement;
									break;
								case 'E':	Offset.x = Offset.x+movement;
											Offset.y = Offset.y-movement;
									break;
								case 'Z':	Offset.x = Offset.x-movement;
											Offset.y = Offset.y+movement;											
									break;
								case 'C':	Offset.x = Offset.x+movement;
											Offset.y = Offset.y+movement;											
											
									break;
								default :
							}
							try{
        					sleep(50);   
        					} catch( Exception e){}
						}
						doorCheck();
								
					} else {position = temp;}	
    			} else {
    				try{
        			sleep(50);   
        			} catch( Exception e){}	
        			
    			}
    		}
		}
	}

	public class BattleThread extends Thread {
   		public void run() {
   			Random randomNumber = new Random();
   			int count = 0;
        	while(true){
        		
        		for(int u = 0; u < badguy.length; u++){
        			if(position.x == badguy[u].position.x && position.y == badguy[u].position.y  && !badguy[u].dead){
						stop = true;
						moving = false;
						frame.hide();
						RPGFrame frame1 = new RPGFrame(randomNumber.nextInt(6)+1);
						new Thread(frame1).run();
						if(!Player.dead){
							badguy[u].dead = true;
						}
						Player.dead = false;//Just for presenting
						frame.show();
						try{
        				sleep(5000);   
        				} catch( Exception e){}
        				stop = false;        				
        			}
        		}
        		try{
        		sleep(50);   
        		} catch( Exception e){}
        	}    	
   		}
	}

	public class PaintThread extends Thread {
		public void run() {
			while(true) {
				try{
        		sleep(25);   
        		} catch( Exception e){}	
        		repaint();
			}
		}
	}
	
	public Integer[][] getLevel() {
		switch(level) {
			case 1:mapx = 44; mapy = 22;
				return map1;
			case 2:mapx = 44; mapy = 22;
				return map2;
				
			case 3:
			case 4:
		}
		return map1;
	}

	public void doorCheck() {
		System.out.println("("+position.x+","+position.y+")");
		switch(level) {
			case 1:
				if(position.x == 28 && position.y == 13){
					level++;
					position = new Point(21,11);
					initLevel();
				}
				break;
			case 2:
				if(position.x == 21 && position.y == 11){
					level--;
					position = new Point(28,13);
					initLevel();
				}
				break;
			case 3:
				break;
			case 4:
		}
	}

	public void initLevel() {
		switch(level){
			case 1:
				Offset = new Point(position.x*grid-map1[1].length/2*grid+grid*shiftx,position.y*grid-map1.length/2*grid+grid*shifty);
		 		Point point[] = {	new Point(35,10),
 									new Point(25,10),
 									new Point(10,25),
 									new Point(5,5)};
				for(int f = 0; f < badguy.length;f++){
					badguy[f] = new MapBadGuy(this, point[f]);
					badguy[f].start();			
				}
				break;
			case 2:
				Offset = new Point(position.x*grid-map1[1].length/2*grid+grid*shiftx,position.y*grid-map1.length/2*grid+grid*shifty);
		 		Point point2[] = {	new Point(5,5),
 									new Point(5,5),
 									new Point(5,5),
 									new Point(5,5)};
				for(int f = 0; f < badguy.length;f++){
					badguy[f] = new MapBadGuy(this, point2[f]);
					badguy[f].start();			
				}
			case 3:
				break;
			case 4:
				 break;
		}

	}
}

