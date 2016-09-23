/**
* TCSS 305 Winter 2016.
* Assignment 5 PowerPaint.
*/

package paintboard;

import gui.Canvas;

import java.awt.Point;
import java.awt.geom.Line2D;
import javax.swing.JPanel;


/**
 * This class draws an ellipse. 
 * @author Cindy Wang
 * @version 1.0
 */
public class TLine implements ITool {


	/**
	 * The starting point of the drawing. 
	 */
	private Point myStartingPoint; 

	/**
	 * The end point of the drawing. 
	 */
	private Point myEndPoint; 

	/**
	 * Handles the event when the mouse is pressed. 
	 */
	public void launchMousePressedHandler(JPanel thePanel, Point thePoint) {
		myStartingPoint = thePoint;
		myEndPoint = myStartingPoint;


	}

	/**
	 * Handles the event when the mouse is being dragged. 
	 */
	public void launchMouseDraggedHandler(JPanel thePanel, Point thePoint){
		myEndPoint =  thePoint;
		final Line2D line = new Line2D.Double(myStartingPoint, myEndPoint);  
		((Canvas) thePanel).setCurrentShape(line);
		thePanel.repaint();      
	}


	/**
	 * Handles the event when the mouse is released. 
	 */
	public void launchMouseReleasedHandler(JPanel thePanel, Point thePoint){

		myEndPoint = thePoint;
		final Line2D line = new Line2D.Double(myStartingPoint, myEndPoint);   
		        ((Canvas) thePanel).setFinishedDrawingList(line);
		myStartingPoint = myEndPoint;
		thePanel.repaint();

	}

	/**
	 * The name of the tool. 
	 */
	@Override
	public String getToolName() {
		return "Line";
	}

}
