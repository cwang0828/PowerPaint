/**
* TCSS 305 Winter 2016.
* Assignment 5 PowerPaint.
*/


package gui;

import java.awt.event.MouseAdapter;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;

import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.List;

import paintboard.*;

/**
 * This class draws shapes on to the canvas. 
 * @author Cindy Wang
 * @version 1.0
 */
public class Canvas extends JPanel {

	/**
	 *  A generated serial version UID for object Serialization.
	 */
	private static final long serialVersionUID = -4059967208215341019L;

	/**
	 * A default UW color. 
	 */
	private static final Color PURPLE = new Color(51, 0, 111);

	/**
	 * A default stroke width. 
	 */
	private static final float DEFAULT_WIDTH = 5; 

	/**
	 * The list of drawings.   
	 */
	private final List<DrawingOptions> myDrawingList = 
			new ArrayList<DrawingOptions>();

	/**
	 * An instant variable storing the shape that is currently drawing. 
	 */
	private Shape myCurrentShape;

	/**
	 * An instant variable storing the stroke width of the drawing. 
	 */
	private float myWidth; 

	/**
	 * An instant variable storing the color of the drawing.
	 */
	private Color myColor; 

	/**
	 * An instant variable storing whether there is a current shape. 
	 */
	private boolean myIsCurrent; 

	/**
	 * An instant variable storing whether there is drawing on the canvas. 
	 */
	private boolean myIsUndo; 

	/**
	 * An instant variable storing whether the SquareCircle option is clicked. 
	 */
	private boolean myEqualDimension;

	/**
	 * An instant variable that stores the currently using tool. 
	 */
	public ITool myCurrentTool;



	/**
	 * The constructor for the PowerPaintCanvas. 
	 */
	public Canvas() {
		super(new GridLayout(1, 1));
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
		myColor = PURPLE;
		myWidth = DEFAULT_WIDTH;
		myIsCurrent = false; 
		myIsUndo = false; 
		this.addMouseListener(new PanelMouseListener());
		this.addMouseMotionListener(new PanelMouseListener());
		myCurrentTool =new TLine();
	}

	/**
	 * @param theGraphic draws all the shapes store in the list. 
	 */
	public void paintComponent(final Graphics theGraphic) {
		final Graphics2D g2D = (Graphics2D) theGraphic;
		theGraphic.drawRect(0, 0, this.getWidth(), this.getHeight());
		theGraphic.setColor(Color.WHITE);
		theGraphic.fillRect(0, 0, this.getWidth(), this.getHeight());



		// Draw all the shapes (with specific color, stroke, and shape)
		// from the list. 
		for (final DrawingOptions drawing : myDrawingList) {
			g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
					RenderingHints.VALUE_ANTIALIAS_ON);
			if(drawing != null) {
				g2D.setColor(drawing.getColor());
				g2D.setStroke(drawing.getStroke());
				g2D.draw(drawing.getShape());
			}
		}   
		
		if (myIsCurrent) {
			g2D.setColor(myColor);
			g2D.setStroke(new BasicStroke(myWidth));
			g2D.draw(myCurrentShape);
		}
	}

	/**
	 * A setter method that sets the current shape. 
	 * @param theCurrentShape sets the currently drawing shape. 
	 */
	public void setCurrentShape(final Shape theCurrentShape) {
		myCurrentShape = theCurrentShape;
		myIsCurrent = true; 
	}

	/**
	 * Remove all the drawn shapes on the canvas. 
	 */
	public void undoDrawings() {
		myDrawingList.clear();
		myIsCurrent = false; 
		myIsUndo = false; 
		setRequireDrawingEqualDimensions(false);
		
		repaint();
	}

	/**
	 * Add the current shape that has a specified shape, stroke width
	 * and color to the drawing list. 
	 * @param theCurrentShape is the most recent drawn shape. 
	 */
	public void setFinishedDrawingList(final Shape theCurrentShape) {
		// set theCurrentDrawing to false when it is being
		// added to the drawing list. 
		myIsCurrent = false; 
		myIsUndo = true; 
		myDrawingList.add(new DrawingOptions(theCurrentShape, 
				myWidth, myColor));
	}

	/**
	 * A setter method to set the color of the drawing. 
	 * @param theColor sets the color of the drawing. 
	 */
	public void setColor(final Color theColor) {
		myColor = theColor; 
	}

	/**
	 * A getter method for accessing 
	 * the color of the drawing. 
	 * @return the color of the drawing. 
	 */
	public Color getColor() {
		return myColor; 
	}
	
	/**
	 * A setter method to set the stroke width.
	 * @param theWidth sets the stroke width. 
	 */
	public void setStrokeWidth(final float theWidth) {
		myWidth = theWidth;
	}


	/**
	 * A getter method for accessing 
	 * the width of the stroke. 
	 * @return the width of the stroke. 
	 */
	public float getStrokeWidth() {
		return myWidth;
	}


	/**
	 * A method that keep check on whether there is 
	 * anything drawn on the canvas. 
	 * @return true if there is drawing on the canvas. 
	 */
	public final boolean isDrawing() {
		return myIsUndo;
	}

	/**
	 * This method checks whether the 
	 * square/circle option button is clicked. 
	 * @return true when the square/circle option is clicked. 
	 */
	public boolean requireDrawingEqualDimensions(){
		return myEqualDimension;
	}


	/**
	 * This setter method is set to true/false depending on
	 * whether the square/circle option button is clicked.
	 * @param theIsSquareCircle returns true 
	 * when the square/circle check box is clicked. 
	 */
	public void setRequireDrawingEqualDimensions(final boolean theIsSquareCircle) {
		myEqualDimension = theIsSquareCircle;
	}


	/**
	 * This setter method sets the properties in the tool interface. 
	 * @param theCurrentTool is a setter for setting the current tool. 
	 */
	public void setCurrentTool(final ITool theCurrentTool) {
		myCurrentTool = theCurrentTool; 
	}

	/**
	 * This getter method provides access to 
	 * different properties in the tool interface. 
	 * @return the currently using tool. 
	 */
	public final ITool getCurrentTool() {
		return myCurrentTool; 
	}


	/**
	 * The mouse listeners for the tools. 
	 * @author Cindy Wang
	 * @version 1.0
	 */
	public class PanelMouseListener extends MouseAdapter implements MouseMotionListener {
		
		/**
		 * Handles the event when the mouse is pressed. 
		 */
		@Override
		public void mousePressed(final MouseEvent theEvent){
			if(myWidth >0) {
				final Point startingPoint = theEvent.getPoint();
				final JPanel panel  = (JPanel) theEvent.getSource();
				getCurrentTool().launchMousePressedHandler(panel, startingPoint);
			}
		}

		/**
		 * Handles the event when the mouse is being dragged. 
		 */
		@Override
		public void mouseDragged(final MouseEvent theEvent){
			if(myWidth >0) {
				final Point currentPoint = theEvent.getPoint();
				getCurrentTool().launchMouseDraggedHandler((JPanel) 
						theEvent.getSource(),currentPoint); 
			}
		}

		/**
		 * Handles the event when the mouse is released. 
		 */
		@Override
		public void mouseReleased(final MouseEvent theEvent){
			if(myWidth >0) {
				final Point currentPoint = theEvent.getPoint();
				getCurrentTool().launchMouseReleasedHandler((JPanel) 
						theEvent.getSource(),currentPoint);
			}
		}   
	}
}
