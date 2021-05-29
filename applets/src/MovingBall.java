import java.awt.*;
import java.applet.Applet;
 
public class MovingBall extends Applet {
    
    //Dimensions of Applet:
    public final int width = 500;
    public final int height = 500;
    //Size and speed of ball:
    public final int diameter = 20;
    public final int speed = 5;
    //Milliseconds to pause between frames:
    public final int pause = 20;
    
    public void paint (Graphics page) {
        setBackground(Color.blue);
    
        
        //Loop forever;
        while (true) {
            
            //Pause between frames:
            try { Thread.sleep(pause) ; } catch (Exception e) { };
        }
    }
}