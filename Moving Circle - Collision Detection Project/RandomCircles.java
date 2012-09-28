import java.applet.Applet;
import java.awt.*;
import java.util.Random;
import java.lang.Math;
/**
 * The Clock applet displays the current time. 
 * The applet updates its display every second. 
 * You can scroll the page and perform other tasks while the clock updates. 
 * This is possible because the code that updates 
 * the clock's display runs within its own thread. 
 */
public class RandomCircles extends Applet implements Runnable {
  private Thread clockThread = null;
  private Random rand = new Random();
  private int x = 50,y = 50;
  private int randNum1 = 0;
  private int randNum2 = 0;
  private Point TopLeft,TopRight,BottomRight,BottomLeft;
  private Circle circle1 = new Circle(50,50,10,10);
  private Circle circle2 = new Circle(30,30,10,10);

  /**
   * The applet’s init method is automatically invoked by the browser.
   */
  public void init() {
    setBackground(Color.white);
  }  
  
  /**
   * The applet’s start method is automatically invoked by the browser.
   */
  public void start() {
    if (clockThread == null) {
      /* 
       * The applet’s start method creates an internal thread object, 
       * then plugs itself in the internal thread object, and starts the thread. 
       */
      clockThread = new Thread(this, "Clock");
      TopLeft = new Point(-10,-10);
      BottomLeft = new Point(-10,100);
      TopRight = new Point(100,-10);
      BottomRight = new Point(100,100);
      clockThread.start();
    }
  }
  
  /**
   * The applet’s run method is automatically executed 
   * by the applet's internal clock thread.
   * This internal clock thread is created by the applet's start method.
   */
  public void run() {
    // Get a reference to the current thread:
    Thread myThread = Thread.currentThread(); 
    /* The clockThread is normally the same as the current thread.
     * The clockThread will become null when the user stops the applet.
     */
    while (clockThread == myThread) { 
      // Indirectly trigger the execution of the applet's paint method.
      
      randNum1 = rand.nextInt(4);
      randNum2 = rand.nextInt(4);
      
      switch(randNum1){
      	case 0:
      		if(circle1.detect(TopLeft,TopRight)){
      		}else{
      			circle1.moveUp(5);
      		}
      		break;
      	case 1:
      		if(circle1.detect(TopLeft,BottomLeft)){
      		}else{
      			circle1.moveLeft(5);
      		}
      		break;
      	case 2:
      		if(circle1.detect(BottomLeft,BottomRight)){
      		}else{
      			circle1.moveDown(5);
      		}
      		break;
      	case 3:
      		if(circle1.detect(TopRight,BottomRight)){
      		}else{
      			circle1.moveRight(5);
      		}
      		break;
      }
      
      switch(randNum2){
      	case 0:
      		if(circle2.detect(TopLeft,TopRight)){
      		}else{
      			circle2.moveUp(5);
      		}
      		break;
      	case 1:
      		if(circle2.detect(TopLeft,BottomLeft)){
      		}else{
      			circle2.moveLeft(5);
      		}
      		break;
      	case 2:
      		if(circle2.detect(BottomLeft,BottomRight)){
      		}else{
      			circle2.moveDown(5);
      		}
      		break;
      	case 3:
      		if(circle2.detect(TopRight,BottomRight)){
      		}else{
      			circle2.moveRight(5);
      		}
      		break;
      }
      Collision(circle1,circle2);
      repaint();  
      try {
        Thread.sleep(100);
      } catch (InterruptedException e){ }
    }
  }
  
  /*
   * The applet’s paint method is automatically 
   * invoked to paint and repaint the applet.
   * This happens when the applet is first being shown 
   * or when it needs to be updated.
   */
  public void paint(Graphics g) {
  		
  		Graphics2D g2 = (Graphics2D)g;
  		
		g2.setColor(new Color(100,100,100));
		g2.drawRect(0,0,100,100);
		g2.fillOval(circle1.x,circle1.y,10,10);
		g2.fillOval(circle2.x,circle2.y,10,10);
  }
  
  /**
   * Called by the browser to inform this applet 
   * that it should stop its execution.
   */
  public void stop() {
    clockThread = null;
  }
  
	public void Collision(Circle c1, Circle c2){
		
		int r1 = c1.width / 2;
		int r2 = c2.width / 2;
		
		if(distance(c1,c2) <= r1 + r2){
			if(c1.y > c2.y){
				c1.y += 5;
				c2.y -= 5;
			}else if(c1.x > c2.x){
				c1.x += 5;
				c2.x -= 5;
			}else if(c2.y > c1.y){
				c2.y += 5;
				c1.y -= 5;
			}else if(c2.x > c1.x){
				c2.x += 5;
				c1.x -= 5;
			}
		}
	}
	
	public double distance(Circle c1,Circle c2){
		return Math.sqrt(((c1.x-c2.x)*(c1.x-c2.x))+((c1.y-c2.y)*(c1.y-c2.y)));
	}
}