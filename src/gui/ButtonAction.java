/**
* TCSS 305 Winter 2016.
* Assignment 5 PowerPaint.
*/

package gui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import paintboard.ITool;

/**
 * This class allows the tool buttons to perform
 * specific tool functions.  
 * @author Cindy Wang
 * @version 1.0
 *
 */
public class ButtonAction extends AbstractAction {

	/**
	 * A generated serialization ID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The tool that is currently using.
	 */
	private ITool myCurrentTool;


	
	/**
	 * The canvas that holds the drawing panel. 
	 */
	private Canvas myCanvas; 

	
	/**
	 * Construct an action with the specified name and icon to set the
	 * tools to perform specific functions.
	 * 
	 * @param theText is the name of the tool. 
	 * @param theIcon is the icon of the tool. 
	 * @param theTool is the current tool. 
	 */
	public ButtonAction(final String theText, final ImageIcon theIcon, 
			final ITool theTool, final Canvas theCanvas) {
		super(theText);
		putValue(Action.ACTION_COMMAND_KEY, theText);
		myCanvas = theCanvas;
		final ImageIcon icon = new ImageIcon(theIcon.getImage());
		putValue(Action.LARGE_ICON_KEY, icon);

		myCurrentTool = theTool;
		
		// set a mnemonic on the first character of the name. 
		putValue(Action.MNEMONIC_KEY, KeyEvent.getExtendedKeyCodeForChar
				(theText.charAt(0)));

		// coordinate button selection. 
		putValue(Action.SELECTED_KEY, true);
		
	}

	@Override
	public void actionPerformed(final ActionEvent theEvent) {
		myCanvas.setCurrentTool(myCurrentTool);

	}
	

}
