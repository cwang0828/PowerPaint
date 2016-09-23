/**
 * TCSS 305 Winter 2016.
 * Assignment 5 PowerPaint.
 */


package paintboard;

import java.awt.Point;
import java.awt.geom.Path2D;

import javax.swing.JPanel;

import gui.Canvas;


/**
 * This class draws with a pencil. 
 * @author Cindy Wang
 * @version 1.0
 */
public class TPencil  implements ITool {


	/**
	 * The starting point of the drawing. 
	 */
	private Point myStartingPoint; 

	/**
	 * The end point of the drawing. 
	 */
	private Point myEndPoint; 

	private Path2D myShape; 


	/**
	 * The name of the tool. 
	 */
	@Override
	public String getToolName() {
		return "Pencil";
	}

	/**
	 * Handles the event when the mouse is pressed. 
	 */
	public void launchMousePressedHandler(JPanel panel, Point thePoint){
		myStartingPoint = thePoint;
		myEndPoint = myStartingPoint;
		myShape = new Path2D.Double();
		myShape.moveTo(thePoint.getX(), thePoint.getY());
		panel.repaint();
	}

	/**
	 * Handles the event when the mouse is being dragged. 
	 */
	public void launchMouseDraggedHandler(JPanel panel, Point thePoint){
		myEndPoint = thePoint;
		myShape.lineTo(thePoint.getX(), thePoint.getY());
		((Canvas) panel).setFinishedDrawingList(myShape);
		myStartingPoint = myEndPoint;


		panel.repaint();

	}

	/**
	 * Handles the event when the mouse is released. 
	 */
	public void launchMouseReleasedHandler(JPanel panel, Point thePoint){
		myEndPoint =  thePoint;

		// a temporary variable
		final Path2D line = (Path2D) myShape;
		// starting point
		line.moveTo(myStartingPoint.x, myStartingPoint.y);
		// ending point
		line.lineTo(myEndPoint.x, myEndPoint.y);   
		((Canvas) panel).setFinishedDrawingList(line);
		myStartingPoint = myEndPoint;

		panel.repaint();
	}
}









