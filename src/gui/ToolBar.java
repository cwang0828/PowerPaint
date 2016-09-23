/**
* TCSS 305 Winter 2016.
* Assignment 5 PowerPaint.
*/

package gui;

import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;


/**
 * This class creates and returns a toolBar that contains buttons for all tools.
 * @author Cindy Wang
 * @version 1.0
 */
public class ToolBar {
	

	/**
	 * This method creates a JToolBar and 
	 * returns this toolBar that has buttons for all tools.
	 * @return a toolBar that contains buttons for all tools.
	 */
	public JToolBar creatJToolBar(List<ButtonAction> myButtonActions) {
		final JToolBar toolBar = new JToolBar();
		toolBar.addSeparator();
		final ButtonGroup buttonGroup = new ButtonGroup();

		for (final ButtonAction button : myButtonActions) {
			final JToggleButton tb = new JToggleButton(button);
			buttonGroup.add(tb);
			toolBar.add(tb);
		}
		return toolBar;
	}
	
	
}


