/**
* TCSS 305 Winter 2016.
* Assignment 5 PowerPaint.
*/

package paintboard;
import java.awt.Point;
import javax.swing.*;



/**
 * Interface for the functionalities of a tool object. 
 * @author Cindy Wang
 * @version 1.0
 *
 */
public interface ITool {

	/**
	 * @return the name of the tool. 
	 */
	String getToolName();

	/**
	 * The function when a tool object is pressed. 
	 * @param panel holds the drawing canvas. 
	 * @param thePoint is the starting point. 
	 */
	void launchMousePressedHandler(JPanel panel, Point thePoint);
	
	/**
	 * The function when a tool object is released. 
	 * @param panel holds the drawing canvas. 
	 * @param thePoint is the end point.
	 */
	void launchMouseReleasedHandler(JPanel panel, Point thePoint);
	
	/**
	 * The function when a tool object is dragged. 
	 * @param panel holds the drawing canvas. 
	 * @param thePoint is the moving point.
	 */
	void launchMouseDraggedHandler(JPanel panel,  Point thePoint);

}
