import java.awt.Color;
import java.util.Random;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class BallDemo   
{
    private Canvas myCanvas;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce(int numBall)
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.drawLine(50, ground, 550, ground);

        // crate and show the balls
        Random rdn = new Random(); 
        BouncingBall[] balls = new BouncingBall[numBall];
        for(int i = 0; i < balls.length; i++){
            balls[i] =  new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
            balls[i].draw();
        }

        // make them bounce
        boolean finished =  false;
        while(!finished) {
            myCanvas.wait(50);           // small delay
            balls[1].move();
            // stop once ball has travelled a certain distance on x axis
            if(balls[1].getXPosition() >= 550) {
                finished = true;
            }
        }
    }
}
