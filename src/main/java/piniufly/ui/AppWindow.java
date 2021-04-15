package piniufly.ui;


import com.formdev.flatlaf.FlatDarculaLaf;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ToolTipManager;

/**
 *in
 * @author jtoledoc
 */
public class AppWindow extends javax.swing.JFrame {

    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JPanel footer;
    private javax.swing.JLabel connectionLabel;




    public AppWindow() {
        initComponents();
        ImageIcon img = new ImageIcon("PATH");
        this.setIconImage(img.getImage());

    }


    private void initComponents() {

        //WINDOW INIT

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("jmsfs-cabinsounds v0.1.0 preview2");
        setAlwaysOnTop(true);
        setMaximumSize(null);
        setMinimumSize(new java.awt.Dimension(800, 500));
        setPreferredSize(new java.awt.Dimension(800, 500));
        setSize(new java.awt.Dimension(800, 500));
        getContentPane().setLayout(new java.awt.GridLayout(6, 1));

        //MENU

        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        fileMenu.setText("File");

        helpMenu = new javax.swing.JMenu();
        helpMenu.setText("Help");

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);




        //VARIABLES


        javax.swing.JPanel preFlightButtonsPanel;
        javax.swing.JLabel preFlightLabel;
        javax.swing.JPanel preFlightLabelPanel;
        javax.swing.JPanel preFlightMainPanel;

        preFlightMainPanel = new javax.swing.JPanel();
        preFlightLabelPanel = new javax.swing.JPanel();
        preFlightLabel = new javax.swing.JLabel();
        preFlightButtonsPanel = new javax.swing.JPanel();


        preFlightMainPanel.setLayout(new javax.swing.BoxLayout(preFlightMainPanel, javax.swing.BoxLayout.PAGE_AXIS));

        preFlightLabelPanel.setLayout(new java.awt.GridLayout(1, 0));


        preFlightLabel.setFont(new java.awt.Font("Lucida Grande", 0, 18));
        preFlightLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        preFlightLabel.setText("Pre Flight");
        preFlightLabel.setMaximumSize(new java.awt.Dimension(1, 1));
        preFlightLabel.setMinimumSize(new java.awt.Dimension(1, 1));
        preFlightLabel.setMixingCutoutShape(null);
        preFlightLabel.setPreferredSize(new java.awt.Dimension(1, 1));
        preFlightLabel.setSize(new java.awt.Dimension(1, 1));
        preFlightLabelPanel.add(preFlightLabel);

        preFlightMainPanel.add(preFlightLabelPanel);

        preFlightButtonsPanel.setLayout(new java.awt.GridLayout(1, 0));


        //BUTTON
        JButton firstButton = new javax.swing.JButton();
        firstButton.setIcon(new javax.swing.ImageIcon("PATH"));
        firstButton.setText("Boarding Music");
        firstButton.setToolTipText("Boarding Music");
        firstButton.setFocusable(false);
        firstButton.setRequestFocusEnabled(false);
        firstButton.addActionListener((java.awt.event.ActionEvent evt) -> {
            System.out.println("DDDD");
        });
        preFlightButtonsPanel.add(firstButton);
        //---------







        preFlightMainPanel.add(preFlightButtonsPanel);
        getContentPane().add(preFlightMainPanel);


        footer = new javax.swing.JPanel();

        footer.setLayout(new javax.swing.OverlayLayout(footer));
        connectionLabel = new JLabel();
        connectionLabel.setForeground(new java.awt.Color(0, 255, 0));
        connectionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        connectionLabel.setText("Connected to Simulator (coming soon)");
        connectionLabel.setEnabled(false);
        footer.add(connectionLabel);
        getContentPane().add(footer);


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

