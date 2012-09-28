import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel {
    
    public Point upperLeft,upperRight,lowerRight,lowerLeft;
    public Point lowerWall1,upperWall1,cap1,cap2;
    public Point lowerWall2,upperWall2,cap3,cap4;
    
    
    /**
     *  Constructs an initial drawing panel.
     */
    public DrawingPanel() {
        setSize(600, 600); 
    }
    
    /**
     * Invoked automatically whenever the drawing panel needs to be painted.
     * This happens when the user chooses a new shape or a new nesting level.
     * This also happens when the user resizes the entire GUI frame.
     */
    public void paintComponent(Graphics g) {
    	//initializes the graphics engine and sets the color to black
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(0,0,0));
        
        //Creates points that are used for the black border square    
    	upperLeft  = new Point(40, 40);
    	upperRight = new Point(500, 40);
    	lowerRight = new Point(500, 500);
    	lowerLeft  = new Point(40, 500);
    	
    	//Points used for the walls.
    	lowerWall1 = new Point(250,250);
    	upperWall1 = new Point(40,250);
    	cap1       = new Point(260,280);
    	cap2       = new Point(260,290);
    	
    	lowerWall2 = new Point(390,40);
    	upperWall2 = new Point(390,300);
    	cap3       = new Point(390,300);
    	cap4       = new Point(400,300);
    	
    	
        //Draws the black border square.    
       	
       	//Top
        g2.drawLine(upperLeft.x+10,upperLeft.y+40,upperRight.x,upperRight.y+40);
        //Right
        g2.drawLine(upperRight.x,upperRight.y+40,lowerRight.x,lowerRight.y+30);
        //Bottom
        g2.drawLine(lowerRight.x,lowerRight.y+30,lowerLeft.x+10,lowerLeft.y+30);
        //Left
        g2.drawLine(lowerLeft.x+10,lowerLeft.y+30,upperLeft.x+10,upperLeft.y+40); 
        
        //Draws the Walls
        
        //Wall on the Left side
        g2.drawLine(lowerWall1.x+10,lowerWall1.y+40,upperWall1.x+10,upperWall1.y+40);
        g2.drawLine(lowerWall1.x+10,lowerWall1.y+30,upperWall1.x+10,upperWall1.y+30);
        g2.drawLine(cap1.x,cap1.y,cap2.x,cap2.y);
        
        //Wall on Top
        g2.drawLine(lowerWall2.x,lowerWall2.y+40,upperWall2.x,upperWall2.y+40);
        g2.drawLine(lowerWall2.x+10,lowerWall2.y+40,upperWall2.x+10,upperWall2.y+40);
        g2.drawLine(cap3.x,cap3.y+40,cap4.x,cap4.y+40);
        
    }
    
    //Method to be used in passing the points from this class into Circle.
    //Causes Null Pointer Exception when used.
    public Point[][] getPoints(){
    
    	Point[][] points = new Point[4][2];
    	
    	points[0][0] = upperLeft;  
    	points[0][1] = upperRight;
    	
    	points[1][0] = lowerRight; 
    	points[1][1] = lowerLeft;
    	
    	points[2][0] = upperLeft;  
    	points[2][1] = lowerLeft;
    	
    	points[3][0] = upperRight; 
    	points[3][1] = lowerRight;
    	
    	return points;	
    }
}
