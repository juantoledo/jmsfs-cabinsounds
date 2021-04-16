package piniufly.ui;


import com.formdev.flatlaf.FlatDarculaLaf;
import piniufly.ui.model.ButtonEntry;
import piniufly.ui.model.TitleEntry;
import piniufly.ui.model.ToggleButtonEntry;

import javax.swing.*;

/**
 * in
 *
 * @author jtoledoc
 */
public class AppWindow extends javax.swing.JFrame {


    public AppWindow() {
        initComponents();
        //ImageIcon img = new ImageIcon("PATH");
        //this.setIconImage(img.getImage());
    }


    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setMaximumSize(new java.awt.Dimension(0, 0));
        setMinimumSize(new java.awt.Dimension(0, 0));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));


        new TitleEntry("Pre Flight", new javax.swing.ImageIcon("C:\\Users\\juant\\Desktop\\human-greeting-proximity.png"), this);

        new ToggleButtonEntry("Security Instructions", new javax.swing.ImageIcon("C:\\Users\\juant\\Desktop\\human-greeting-proximity.png"), "C:\\drop.wav", this);

        new ToggleButtonEntry("Crew Doors in Manual Mode - Cross check / Report", new javax.swing.ImageIcon("C:\\Users\\juant\\Desktop\\human-greeting-proximity.png"), "C:\\short.wav", this);


        pack();

    }// </editor-fold>


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {


       FlatDarculaLaf.install();


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ToolTipManager.sharedInstance().setInitialDelay(0);
                new AppWindow().setVisible(true);
            }
        });
    }


}

