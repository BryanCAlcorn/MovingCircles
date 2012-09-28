import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * This class implements a GUI interface for the DrawingPanel class, 
 * which draws recursively nested shapes (such as nested triangles 
 * or nested diamonds) as selected by the user.
 */

public class MovingCircle extends JFrame implements KeyListener{
    //Drawing and control panel instantiation:
    private DrawingPanel drawingPanel;
    private Circle circle;
    private JTextField textField = new JTextField(0);
    private JPanel controlPanel = new JPanel();
    public int KeyCode;
    private String[] directions = new String[4];
    
    /**
     * Initializes and displays the GUI. 
     */
    public MovingCircle () {
        // Create a default drawing panel:
        drawingPanel = new DrawingPanel();
        circle = new Circle(60,60,10,10);
        display();
    }
    
    /**
     * Builds the GUI. Displays a default shape with default nesting level.
     */
    public void display() {
        
        // Register this object as a listener on text field and drawing panel:
        drawingPanel.addKeyListener(this);
           textField.addKeyListener(this); 
        
        // Build the GUI:
        drawingPanel.setBorder(BorderFactory.createTitledBorder("Drawing Panel"));
        drawingPanel.setEnabled(true);
       
            controlPanel.add(textField);    
        getContentPane().add(controlPanel,"North");
        getContentPane().add(drawingPanel,"Center");
        getContentPane().add(circle,"Center");
        
        this.setSize(drawingPanel.getWidth(), drawingPanel.getHeight()+controlPanel.getHeight());
        this.setVisible(true);
    } 
 
    public void keyReleased(KeyEvent e){
    	//Unused abstract method inherited from KeyListener
    }
    
    public void keyTyped(KeyEvent e){
    	//Unused abstract method inherited from KeyListener
    }
	
	/*This method moves the circle:
	 *1: Gets the key code and determines which arrow key was hit, if one was hit.
	 *2: Checks to see if a collision with a wall will occur.
	 *3: Changes course if a collision does occur.
	 *4: Moves the circle.
	 */
	
	//Key Codes for arrows
 	//Left = 37,Up = 38,Right = 39,Down = 40
    public void keyPressed(KeyEvent e){
    	
    	KeyCode = e.getKeyCode();
    	
    	//Calls the collision detection method and puts invalid directions into an array
    	directions = circle.CheckAll();
    	
    	//Checks the array for the directions that cannot be travelled and changes course.
    	for(int p = 0;p < directions.length;p++){
    		if(directions[p] == "l" && KeyCode == 37){
    			KeyCode = 39;
    		}else if(directions[p] == "u" && KeyCode == 38){
    			KeyCode = 40;
    		}else if(directions[p] == "r" && KeyCode == 39){
    			KeyCode = 37;
    		}else if(directions[p] == "d" && KeyCode == 40){
    			KeyCode = 38;
    		}
    	}
    	
    	//Moves the circle
    	switch(KeyCode){
    		case 37://Left															
    			circle.moveLeft(10);
    			break;
    		case 38://Up
    			circle.moveUp(10);
    			break;
    		case 39://Right
    			circle.moveRight(10);
    			break;
    		case 40://Down
    			circle.moveDown(10);
    			break;
    	}
    	repaint();
    }	
    
    public static void main(String[] args) {
        MovingCircle mc = new MovingCircle();
    }
}
