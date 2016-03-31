
import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

/**
 * Class BouncingBall - a graphical ball that observes the effect of gravity. The ball
 * has the ability to move. Details of movement are determined by the ball itself. It
 * will fall downwards, accelerating with time due to the effect of gravity, and bounce
 * upward again when hitting the ground.
 *
 * This movement can be initiated by repeated calls to the "move" method.
 * 
 * @author Michael Kölling (mik)
 * @author David J. Barnes
 * @author Bruce Quig
 *
 * @version 2011.07.31
 */

public class BoxBall
{

    private Random rdn; 
    private Ellipse2D.Double circle;
    private Color color;
    private int diameter;
    private int xPosition;
    private int yPosition;
    private final int groundPositionTop;      // posicion y mas alta
    private final int groundPositionBottom;      // posicion y mas baja    
    private final int lateralPositionLeft;      // posicion x mas a la izquierda
    private final int lateralPositionRight;      // posicion x mas a la derecha
    private Canvas canvas;
    private int xIncrement;
    private int yIncrement;

    /**
     * Constructor for objects of class BouncingBall
     *
     * @param xPos  the horizontal coordinate of the ball
     * @param yPos  the vertical coordinate of the ball
     * @param ballDiameter  the diameter (in pixels) of the ball
     * @param ballColor  the color of the ball
     * @param groundPos  the position of the ground (where the wall will bounce)
     * @param drawingCanvas  the canvas to draw this ball on
     */
    public BoxBall(int xPos, int yPos, int ballDiameter,
                        int groundPosT,int groundPosB, int lPosL,int lPosR, Canvas drawingCanvas)
    {
        rdn = new Random(); 
        xPosition = xPos;
        yPosition = yPos;
        color =  new Color(rdn.nextInt(256),rdn.nextInt(256),rdn.nextInt(256));
        diameter = ballDiameter;
        groundPositionTop = groundPosT;
        groundPositionBottom = groundPosB;        
        lateralPositionLeft = lPosL;
        lateralPositionRight = lPosR;
        canvas = drawingCanvas;
        xIncrement = 0;
        yIncrement = 0;
    }

    /**
     * Draw this ball at its current position onto the canvas.
     **/
    public void draw()
    {
        Random rdn = new Random(); 
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition, yPosition, diameter);
    }

    /**
     * Erase this ball at its current position.
     **/
    public void erase()
    {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }    

    /**
     * Move this ball according to its position and speed and redraw.
     **/
    public void move()
    {
        // remove from canvas at the current position
        erase();
            
        // compute new position
        if(yIncrement == 0){
            if(rdn.nextBoolean()){
                yIncrement = 1;
            }
            else{
                yIncrement= -1;
            }
            if(rdn.nextBoolean()){
                xIncrement = 1;
            }
            else{
                xIncrement= -1;
            }
        }
        yPosition +=yIncrement;
        xPosition +=xIncrement;

        // check if it has hit the ground
        if((yPosition >= (groundPositionBottom - diameter)) || yPosition <= groundPositionTop ) {
            if(yIncrement == 1){
                yIncrement = -1;
            }
            else{
                yIncrement = 1;
            }
        }
        if((xPosition >= (lateralPositionRight - diameter)) || xPosition <= lateralPositionLeft ) {
            if(xIncrement == 1){
                xIncrement = -1;
            }
            else{
                xIncrement = 1;
            }
        }

        // draw again at new position
        draw();
    }    

    /**
     * return the horizontal position of this ball
     */
    public int getXPosition()
    {
        return xPosition;
    }

    /**
     * return the vertical position of this ball
     */
    public int getYPosition()
    {
        return yPosition;
    }
}
