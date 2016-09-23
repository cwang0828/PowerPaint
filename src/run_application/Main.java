/**
* TCSS 305 Winter 2016.
* Assignment 5 PowerPaint.
*/

package run_application;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import gui.GUI;

/**
 * This class creates a drawing canvas that allows the user to 
 * draw using four different tools (pencil, line, rectangle, and ellipse). 
 * @author Cindy Wang
 * @version 1.0
 *
 */
public final class Main {

    /**
     * Private constructor, to prevent instantiation of this class.
     */
    private Main() {
        throw new IllegalStateException();
    }
    
    /**
     * Creates a JFrame using BorderLayout. 
     * @param theArgs Command line arguments, ignored. 
     */
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI().start();
                
            }
        });
        
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (final UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (final IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (final InstantiationException ex) {
            ex.printStackTrace();
        } catch (final ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        
    }

}
