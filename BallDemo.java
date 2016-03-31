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
     * Simulate bouncing balls into a box.
     */
    public void boxBounce(int numBall)
    {
        myCanvas.setVisible(true);
        
        //create the square
        myCanvas.drawLine(200, 150, 200,350);
        myCanvas.drawLine(200, 350, 400,350);
        myCanvas.drawLine(400, 150, 400,350);
        myCanvas.drawLine(400, 150, 200,150);


        // crate and show the balls
        Random rdn = new Random(); 

        BoxBall[] balls = new BoxBall[numBall];
        for(int i = 0; i < balls.length; i++){
            balls[i] =  new BoxBall( rdn.nextInt(200)+200, rdn.nextInt(200)+150,
                rdn.nextInt(20)+10,150,350,200,400,myCanvas);
            balls[i].draw();
        }

        // make them bounce
        boolean finished =  false;
        while(!finished) {
            myCanvas.wait(50);           // small delay
            for(int i = 0; i < balls.length; i++){
                balls[i].move();
                // stop once ball has travelled a certain distance on x axis
            }
        }
    
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
            balls[i] =  new BouncingBall( rdn.nextInt(300)+1, rdn.nextInt(250)+1,
                rdn.nextInt(100)+1, new Color(rdn.nextInt(256),rdn.nextInt(256),rdn.nextInt(256)), ground, myCanvas);
            balls[i].draw();
        }

        // make them bounce
        boolean finished =  false;
        while(!finished) {
            myCanvas.wait(50);           // small delay
            for(int i = 0; i < balls.length; i++){
                balls[i].move();
                // stop once ball has travelled a certain distance on x axis
                if(balls[i].getXPosition() >= 550) {
                    finished = true;
                }
            }
        }
    }
}
