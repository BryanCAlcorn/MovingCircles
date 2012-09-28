import javax.swing.*;
import java.awt.*;
import java.util.*;


/* object to be moving around on the panel. */
public class Circle extends JPanel{
    
    public int width;
    public int height;
    public int x;
    public int y;
    private String dir;
    
    public Circle(int x, int y,int width, int height){
        this.x = x;
        this.y = y;
        this.width  = width;
        this.height = height;
    }
    
    public void paintComponent(Graphics g){
        
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(new Color(0,0,255));
        //System.out.println(x + "," + y); // used for testing
        g2.fillOval(x,y,width,height);
        
    }
// Methods to move the square object
    
    public void moveUp(int y1){
    		y -= y1;
    }
    
    public void moveLeft(int x1){
    		x -= x1;
    }
    
    public void moveDown(int y1){
    		y += y1;
    }
    
    public void moveRight(int x1){
			x += x1;
    }
    
        
    /*Method used to detect collisions:
     *1: Checks to see which side of the line the circle is on.
     *2: Checks if the edge of the circle hits the line in between the two points.
     */
    public String[] CheckAll(){
    	
    	ArrayList<String> directions = new ArrayList<String>();
    	String[] dirArray = new String[4];
    	
    	//All the Points from the Drawing Panel class.
    	
    	Point upperLeft  = new Point(40, 40);
    	Point upperRight = new Point(500, 40);
    	Point lowerRight = new Point(500, 500);
    	Point lowerLeft  = new Point(40, 500);
    	
    	Point lowerWall1 = new Point(250,250);
    	Point upperWall1 = new Point(40,250);
    	Point cap1       = new Point(250,250);
    	Point cap2       = new Point(250,260);
    	
    	Point lowerWall2 = new Point(390,40);
    	Point upperWall2 = new Point(390,300);
    	Point cap3       = new Point(390,300);
    	Point cap4       = new Point(400,300);
    	
    	/*
    	 *Puts the points into a 2-D Array:
    	 *First dimension is the number of the wall.
    	 *Second dimension is the end-points of the wall.
    	 */
    	
    	Point[][] pointArray = new Point[8][2];
    	
    	//Border Square
    	pointArray[0][0] = upperLeft;  
    	pointArray[0][1] = upperRight;
    	
    	pointArray[1][0] = lowerRight; 
    	pointArray[1][1] = lowerLeft;
    	
    	pointArray[2][0] = upperLeft;  
    	pointArray[2][1] = lowerLeft;
    	
    	pointArray[3][0] = upperRight; 
    	pointArray[3][1] = lowerRight;
    	
    	//Wall on Left
    	pointArray[4][0] = lowerWall1;
    	pointArray[4][1] = upperWall1;
    	
    	pointArray[5][0] = cap1;
    	pointArray[5][1] = cap2;
    	
    	//Wall on Top
    	pointArray[6][0] = lowerWall2;
    	pointArray[6][1] = upperWall2;
    	
    	pointArray[7][0] = cap3;
    	pointArray[7][1] = cap4;
    	
    	//Checks all the points used in the graphic by calling the detect method.
    	for(int a = 0;a < pointArray.length;a++){            
    		if(detect(pointArray[a][0],pointArray[a][1])){
    					directions.add(dir);
    		}
    	}
    		//returns invalid directions in a String array
    		return directions.toArray(dirArray);
    	
    }
    
    public boolean detect(Point a, Point b){
    	
    	int Cx = this.x;
    	int Cy = this.y;
    	
    	//Checks to see if the circle is Above, Below, Left, or Right of the end of the line
    	
    	if(a.y > b.y && Cy > a.y){
    		return false;
    	}else if(b.y > a.y && Cy > b.y){
    		return false;
    	}else if(a.y < b.y && Cy < a.y){
    		return false;
    	}else if(b.y < a.y && Cy < b.y){
    		return false;
    	}else if(a.x > b.x && Cx > a.x){
    		return false;
    	}else if(b.x > a.x && Cx > b.x){
    		return false;
    	}else if(a.x < b.x && Cx < a.x){
    		return false;
    	}else if(b.x < a.x && Cx < b.x){
    		return false;	
    	}
    	
    	//Checks if a collision occurs and updates the dir variable with invalid directions
    	
    	if(Cx > a.x && Cx > b.x){ 		//Checks Left
    		Cx -= this.width;
    		if(Cx == a.x && Cx == b.x){
    			dir = "l";
    			return true;
    		}
    	}else if(Cx < a.x && Cx < b.x){ //Checks Right
    		Cx += this.width;
    		if(Cx == a.x && Cx == b.x){
    			dir = "r";
    			return true;
    		}
    	}else if(Cy > a.y && Cy > b.y){ //Checks Top
    		Cy -= this.height;
    		if(Cy == a.y && Cy == b.y){
    			dir = "u";
    			return true;
    		}
    	}else if(Cy < a.y && Cy < b.y){ //Checks Bottom
    		Cy += this.height;
    		if(Cy == a.y && Cy == b.y){
    			dir = "d";
    			return true;
    		}
    	}
    	return false;
    }
    
}
