/**
* TCSS 305 Winter 2016.
* Assignment 5 PowerPaint.
*/

package paintboard;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Shape;
import java.awt.Stroke;

/**
 * This class performs the function in the option menu item. 
 * @author Cindy Wang
 * @version 1.0
 *
 */
public class DrawingOptions {
    
    /**
     * Is an instant variable storing the shape of  the drawing. 
     */
    private final Shape myShape;
    
    /**
     * Is an instant variable storing the stroke width of the drawing. 
     */
    private final Stroke myStroke;
    
    /**
     * Is an instant variable storing the color of the drawing. 
     */
    private final Color myColor;
     
    /**
     * The constructor of the class. 
     * @param theShape is the shape of the drawing (whether it is square/circle or not).
     * @param theWidth is the stroke width of the drawing.
     * @param theColor is the color of the drawing. 
     */
    public DrawingOptions(final Shape theShape, 
                                    final float theWidth, 
                                    final Color theColor) {
        myShape = theShape;
        myStroke = new BasicStroke(theWidth);
        myColor = theColor; 
    }
    
    /**
     * @return current Shape. 
     */
    public Shape getShape() {
        return myShape; 
    }
    
    /**
     * @return current Stroke. 
     */
    public Stroke getStroke() {
        return myStroke; 
    }
    
    
    /** 
     * @return current color.
     */
    public Color getColor() {
        return myColor; 
    }
    
}
