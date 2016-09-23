/**
* TCSS 305 Winter 2016.
* Assignment 5 PowerPaint.
*/

package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

/**
 * This class creates the color icon 
 * that is displayed on the menu item. 
 * @author cinwan12
 * @version 1.0
 */
public class ColorIcon implements Icon{

	/**
	 * An instant variable that stores the 
	 * length/width of the square color icon. 
	 */
	private final static int ICON_WIDTH = 12; 
	
	/**
	 * An instant variable that stores the currently 
	 * drawing color. 
	 */
	private Color myColor; 
	
	/**
	 * The constructor for the ColorIcon class. 
	 * @param theColor is the currently drawing color. 
	 */
	public ColorIcon(final Color theColor) {
		myColor = theColor; 
	}
	
	/**
	 * The setter method that sets the currently 
	 * drawing color. 
	 * @param theColor is the currently drawing color. 
	 */
	public void setColor(final Color theColor) {
		myColor = theColor; 
	}

	/**
	 * A getter method for accessing the height
	 * of the icon. 
	 */
	@Override
	public int getIconHeight() {
		return ICON_WIDTH;
	}

	/**
	 * A getter method for accessing the width
	 * of the icon. 
	 */
	@Override
	public int getIconWidth() {
		return ICON_WIDTH;
	}

	/**
	 * A method that creates a color icon that 
	 * changes the color depending on the currently  
	 * choosing color. 
	 */
	@Override
	public void paintIcon(final Component theComponent, 
			final Graphics theGraphics, 
			final int theX, final int theY) {
		
		theGraphics.setColor(Color.BLACK);
		theGraphics.drawRect(theX, theY, getIconWidth(), getIconHeight());
		
		theGraphics.setColor(myColor);
		theGraphics.fillRect(theX+1, theY+1, getIconHeight()-1, getIconWidth()-1);
		
	}
	
}
