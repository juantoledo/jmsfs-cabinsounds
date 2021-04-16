package piniufly.ui;


import com.formdev.flatlaf.FlatDarculaLaf;
import org.apache.commons.lang3.exception.ExceptionUtils;
import piniufly.service.FileStructureHelper;

import javax.swing.*;


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

        try {
            FileStructureHelper.convertToUIModel("/Users/jtoledoc/cabinsounds/LATAM", this);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ExceptionUtils.getStackTrace(ex), ex.getClass().getName(), JOptionPane.ERROR_MESSAGE);
        }


        pack();


    }

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

