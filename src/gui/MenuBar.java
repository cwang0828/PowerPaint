/**
* TCSS 305 Winter 2016.
* Assignment 5 PowerPaint.
*/

package gui;

import java.awt.Color;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * @author cinwan12
 *
 */
public class MenuBar {

	/**
	 * An instant JSlider variable for the stroke width of the drawing. 
	 */
	private JSlider mySlider;

	/**
	 * An instant menuItem that holds the "Undo all changes" button. 
	 */
	private JMenuItem myUndo;

	/**
	 * An instant variable storing the icon of the color. 
	 */
	private ColorIcon myColorIcon;


	/**
	 * The canvas that holds the drawing panel. 
	 */
	private Canvas myCanvas; 

	/**
	 * The constructor for the MenuBar class. 
	 * @param theCanvas holds the drawing panel. 
	 */
	public MenuBar (Canvas theCanvas) {
		myCanvas = theCanvas; 
		myCanvas.addMouseListener(new ActivateUndo());
	}


	/**
	 * Create a file menu bar and its menu items. 
	 * @return a JMenu for the File menu. 
	 */
	public JMenu getFileMenu() {
		final JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		myUndo = new JMenuItem("Undo all changes");
		myUndo.addActionListener(new UndoCanvas());

		menu.add(myUndo);
		myUndo.setMnemonic(KeyEvent.VK_U);        
		myUndo.setEnabled(false);

		menu.addSeparator();
		
		final JMenuItem exit = new JMenuItem("Exit");
		exit.setMnemonic(KeyEvent.VK_X);
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				Window window = SwingUtilities.getWindowAncestor(menu);
				window.dispose();
			}
		});
		menu.add(exit);
		return menu;
	}


	
	/**
	 * Create an option menu bar and its menu items. 
	 * @return a JMenu for the Option menu.
	 */
	public JMenu getOptionsMenu() {
		final JMenu menu = new JMenu("Options");
		menu.setMnemonic(KeyEvent.VK_O);

		final JCheckBoxMenuItem squareCircleChecker 
		= new JCheckBoxMenuItem("Square/Circle only", false);
		squareCircleChecker.addItemListener(new CheckSquareCircle());

		menu.add(squareCircleChecker);
		squareCircleChecker.setMnemonic(KeyEvent.VK_S);
		menu.addSeparator();


		// A sub-menu containing a JSlider (from 0 to 20, 
		// with major tick marks of 5, and with minor tick marks of 1).
		final int maxSlider = 20; 
		final int defaultSliderValue = 5; 

		mySlider = new JSlider(JSlider.HORIZONTAL, 0, maxSlider, defaultSliderValue);
		mySlider.addChangeListener(new ChangeThickness());

		// Turn on labels at major tick marks. 
		final int majorTickSpacing = 5; 
		mySlider.setMajorTickSpacing(majorTickSpacing);
		mySlider.setMinorTickSpacing(1);
		mySlider.setPaintTicks(true);
		mySlider.setPaintLabels(true);
		final JMenu thickness = new JMenu("Thickness");
		thickness.add(mySlider);
		menu.add(thickness);
		thickness.setMnemonic(KeyEvent.VK_T);


		menu.addSeparator();
		// A menu item that contains a color chooser dialog. 
		myColorIcon = new ColorIcon(myCanvas.getColor());
		final JMenuItem colorItem = new JMenuItem("Color...", myColorIcon);
		colorItem.addActionListener(new ChooseColor());
		menu.add(colorItem);
		colorItem.setMnemonic(KeyEvent.VK_C);

		return menu;
	}


	/**
	 * * Create a tool menu bar and its menu items. 
	 * @return a JMenu for the Tool menu.
	 */
	public JMenu getToolsMenu(List<ButtonAction> myButtonActions) {
		final JMenu menu = new JMenu("Tools");
		menu.setMnemonic(KeyEvent.VK_T);
		final ButtonGroup buttonGroup = new ButtonGroup();

		for (final ButtonAction button : myButtonActions) {
			final JRadioButtonMenuItem item = new JRadioButtonMenuItem(button);
			buttonGroup.add(item);
			menu.add(item);
		}
		
//		for (final ButtonAction button : myButtonActions) {
//		for(int i = 0; i< myButtonActions.size(); i++) {
////			final JRadioButtonMenuItem item = new JRadioButtonMenuItem(button);
//			
//			final JRadioButtonMenuItem item = new JRadioButtonMenuItem(myButtonActions.get(i));
////			myButtonActions.get(i).myItem = item;
//			buttonGroup.add(item);
//			menu.add(item);
//			
//		}

		return menu; 
	}


	/**
	 * Create a help menu bar and its menu items. 
	 * @return a JMenu for the Help menu.
	 */
	public JMenu getHelpMenu(ImageIcon theLogo) {
		final JMenu menu = new JMenu("Help");
		menu.setMnemonic(KeyEvent.VK_H);
		final JMenuItem menuItem = new JMenuItem("About...");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				JOptionPane.showMessageDialog(myCanvas, 
						"TCSS 305 PowerPaint \n Winter 2016 "
								+ "\n Cindy Wang", "About", 1, theLogo); 
			}
		});
		menu.add(menuItem);
		menuItem.setMnemonic(KeyEvent.VK_A);
		return menu; 
	}


	/**
	 * A menu item which invokes a color chooser dialog. 
	 * @author Cindy Wang
	 * @version 1.0
	 */
	class ChooseColor implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent theEvent) {
			final Color colorChooser = JColorChooser.showDialog(myCanvas, 
					"Choose a Drawing Color",
					myCanvas.getColor());
			if (colorChooser != null) {
				myCanvas.setColor(colorChooser);
				myColorIcon.setColor(colorChooser);
			}
		}
	}


	/**
	 * A menu Item that removes all drawn shapes. 
	 * this menu item should be disabled when there are no shapes to 'undo'. 
	 * @author Cindy Wang
	 * @version 1.0
	 */
	class UndoCanvas implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent theEvent) {
			myCanvas.undoDrawings();
			myUndo.setEnabled(false);
		}
	}


	/**
	 * Enable the "Undo all changes" button in the menu. 
	 * @author Cindy Wang
	 * @version 1.0
	 */
	class ActivateUndo extends MouseAdapter {
		@Override
		public void mouseReleased(final MouseEvent theEvent) {
			if (mySlider.getValue() > 0 && myCanvas.isDrawing()) {
				myUndo.setEnabled(true);
			}
		}
	}
	
	/**
	 * A sub-menu containing a JSlider that changes the thickness
	 * of the future drawn items but not those that are already drawn. 
	 * @author Cindy Wang
	 * @version 1.0
	 */
	class ChangeThickness implements ChangeListener {

		@Override
		public void stateChanged(final ChangeEvent theEvent) {
			final int value = mySlider.getValue();
			if (value >= 0) {
				myCanvas.setStrokeWidth(value);
			}
		}
	}

	/**
	 * When a check box menu item; when checked is checked, 
	 * the rectangle and ellipse tools should produce 
	 * shapes with equal width and height.
	 * @author Cindy Wang
	 * @version 1.0
	 */
	class CheckSquareCircle implements ItemListener {
		@Override
		public void itemStateChanged(final ItemEvent theEvent) {			
			if (theEvent.getStateChange() == 1) {
				myCanvas.setRequireDrawingEqualDimensions(true);
			} else {
				myCanvas.setRequireDrawingEqualDimensions(false);
			}	
		}
	}

}
