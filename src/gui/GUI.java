/**
* TCSS 305 Winter 2016.
* Assignment 5 PowerPaint.
*/


package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import paintboard.TEllipse;
import paintboard.TLine;
import paintboard.TPencil;
import paintboard.TRectangle;


/**
 * @author Hsin-Jung Wang
 * @version 1.0
 */
public class GUI extends JFrame {
	/**
	 * The width of the drawing panel. 
	 */
	public static final int WIDTH = 500; 

	/**
	 * The height of the drawing panel. 
	 */
	public static final int HEIGHT = 400; 

	/**
	 * A String representation of the pencil object. 
	 */
	public static final String PENCIL = "Pencil";

	/**
	 * A String representation of the line object. 
	 */
	public static final String LINE = "Line";

	/**
	 * A String representation of the rectangle object. 
	 */
	public static final String RECTANGLE = "Rectangle";

	/**
	 * A String representation of the Ellipse object. 
	 */
	public static final String ELLIPSE = "Ellipse"; 

	/**
	 * A generated serial version UID for object Serialization. 
	 */
	private static final long serialVersionUID = -951011626269647062L;

	/**
	 * The canvas that holds the drawing panel. 
	 */
	private final Canvas myCanvas; 

	/**
	 * The icon that appears on the window title and on the About message.
	 */
	private final ImageIcon myLogo; 

	/**
	 * A list of actions for the buttons. 
	 */
	private List<ButtonAction> myButtonActions; 

	/**
	 * Constructor for the PowerPaintGUI class. 
	 */
	public GUI() {
		super("PowerPaint");
		myLogo = new ImageIcon("images/husky.jpeg");
		setIconImage(myLogo.getImage());
		setBackground(Color.WHITE);
		myCanvas = new Canvas();
		setUpActions();
	}

	/**
	 * Starts the GUI. 
	 */
	public void start() { 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		// Create and add the menu bar
		final JMenuBar menuBar = new JMenuBar();
		MenuBar createMenuBar = new MenuBar(myCanvas);
		menuBar.add(createMenuBar.getFileMenu());
		menuBar.add(createMenuBar.getOptionsMenu());
		menuBar.add(createMenuBar.getToolsMenu(myButtonActions));
		menuBar.add(createMenuBar.getHelpMenu(myLogo));
		
		setJMenuBar(menuBar);

		// add the tool bar
		ToolBar toolBar = new ToolBar();
		add(toolBar.creatJToolBar(myButtonActions), BorderLayout.SOUTH);
		
		myButtonActions.get(0).actionPerformed(null);

		// add and set the drawing canvas
		myCanvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		myCanvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		add(myCanvas, BorderLayout.CENTER);
		pack();
		setVisible(true);
	}

	/**
	 * Sets up all the actions.
	 */
	private void setUpActions() {
		myButtonActions = new ArrayList<ButtonAction>();
		myButtonActions.add(new  ButtonAction(PENCIL, 
				new ImageIcon("images/pencil_bw.gif"), new TPencil(), myCanvas));
		myButtonActions.add(new ButtonAction(LINE, 
				new ImageIcon("images/line_bw.gif"), new TLine(), myCanvas));
		myButtonActions.add(new ButtonAction(RECTANGLE, 
				new ImageIcon("images/rectangle_bw.gif"), new TRectangle(), myCanvas));
		myButtonActions.add(new ButtonAction(ELLIPSE, 
				new ImageIcon("images/ellipse_bw.gif"), new TEllipse(), myCanvas));

	}
}


