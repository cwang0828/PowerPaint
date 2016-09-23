/**
* TCSS 305 Winter 2016.
* Assignment 5 PowerPaint.
*/


package paintboard;

import gui.Canvas;

import java.awt.Point;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;



/**
 * This class draws a rectangle. 
 * @author Cindy Wang
 * @version 1.0
 */
public class TEllipse implements ITool {

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
	public  void  launchMousePressedHandler(JPanel panel, Point thePoint){
		myStartingPoint = thePoint;
		myEndPoint = myStartingPoint;
		panel.repaint();
	}

	/**
	 * Handles the event when the mouse is being dragged. 
	 */
	public void launchMouseDraggedHandler(JPanel panel, Point thePoint){

		myEndPoint = thePoint;

		final Ellipse2D ellipse = new Ellipse2D.Double();
		ellipse.setFrameFromDiagonal(myStartingPoint, myEndPoint);


		// check if the square/circle button is checked

		if (((Canvas) panel).requireDrawingEqualDimensions()) {
			double height = myEndPoint.getY() - myStartingPoint.getY();
			double width = myEndPoint.getX() - myStartingPoint.getX();

			if (Math.abs(height)> Math.abs(width)) {
				double incrementer = width;
				if(height > 0 && width < 0){
					incrementer = -width;
				} else if( height <0 && width > 0) {
					incrementer = - width;
				}
				((Ellipse2D.Double) ellipse).setFrameFromDiagonal(myStartingPoint.getX(), 
						myStartingPoint.getY(), myStartingPoint.getX() + width, myStartingPoint.getY()+incrementer);
			} else {
				double incrementer = height;

				if(height > 0 && width < 0){
					incrementer = -height;
				} else if( height <0 && width > 0) {
					incrementer = -height;
				}

				((Ellipse2D.Double) ellipse).setFrameFromDiagonal(myStartingPoint.getX(), 
						myStartingPoint.getY(), myStartingPoint.getX() + incrementer, myStartingPoint.getY() +height);
			}
		}


		((Canvas) panel).setCurrentShape(ellipse);
		panel.repaint();

	}

	/**
	 * Handles the event when the mouse is released. 
	 */
	public void launchMouseReleasedHandler(JPanel panel, Point thePoint){

		if(!((Canvas) panel).requireDrawingEqualDimensions()){
			myEndPoint =  thePoint;

			final Ellipse2D ellipse = new Ellipse2D.Double();
			ellipse.setFrameFromDiagonal(myStartingPoint, myEndPoint);
			if (((Canvas) panel).requireDrawingEqualDimensions()) {

				double height = myEndPoint.getY() - myStartingPoint.getY();
				double width = myEndPoint.getX() - myStartingPoint.getX();

				if (Math.abs(height)> Math.abs(width)) {
					double incrementer = width;
					if(height > 0 && width < 0){
						incrementer = -width;
					} else if( height <0 && width > 0) {
						incrementer = - width;
					}
					((Ellipse2D.Double) ellipse).setFrameFromDiagonal(myStartingPoint.getX(), 
							myStartingPoint.getY(), myStartingPoint.getX() + width, myStartingPoint.getY()+incrementer);
				} else {
					double incrementer = height;

					if(height > 0 && width < 0){
						incrementer = -height;
					} else if( height <0 && width > 0) {
						incrementer = -height;
					}

					((Ellipse2D.Double) ellipse).setFrameFromDiagonal(myStartingPoint.getX(), 
							myStartingPoint.getY(), myStartingPoint.getX() + incrementer, myStartingPoint.getY() +height);
				}

			}

			((Canvas) panel).setFinishedDrawingList(ellipse);

			myStartingPoint = myEndPoint; 
			panel.repaint();
		}
	}



	/**
	 * The name of the tool. 
	 */
	@Override
	public String getToolName() {
		return "Ellipse";
	}

}
