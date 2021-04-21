package piniufly.ui;


import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatAtomOneDarkContrastIJTheme;
import org.apache.commons.lang3.exception.ExceptionUtils;
import piniufly.service.FileStructureHelper;
import piniufly.ui.model.UIModel;
import piniufly.ui.updatemanager.UpdateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.Set;

import static java.awt.event.ItemEvent.DESELECTED;
import static java.awt.event.ItemEvent.SELECTED;
import static java.lang.ClassLoader.getSystemResource;
import static javax.swing.JOptionPane.showMessageDialog;
import static piniufly.service.AudioHelper.*;
import static piniufly.service.FileStructureHelper.listDirsUsingFileWalk;
import static piniufly.util.Param.*;


public class AppWindow extends javax.swing.JFrame {

    private JComboBox<String> airlines;

    private JPanel audiosPanelBorder = new JPanel();

    private JScrollPane myJScrollPane = new JScrollPane(audiosPanelBorder,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    private UIModel model;

    public AppWindow(String[] args) throws Exception {

        UpdateManager.checkForUpdates();
        initUIComponents();

    }

    private void initUIComponents() {

        setIconImage(new ImageIcon(getSystemResource("icons/airplane")).getImage());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setMaximumSize(new java.awt.Dimension(0, 0));
        setMinimumSize(new java.awt.Dimension(0, 0));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));


        this.setUndecorated(true);
        setAlwaysOnTop(true);
        audiosPanelBorder.setLayout(new GridLayout());
        audiosPanelBorder.setAutoscrolls(true);


        try {

            JPanel panelLabel = new MotionPanel(this);
            JLabel exitLabel = new javax.swing.JLabel();
            JLabel helpLabel = new javax.swing.JLabel();
            JLabel title = new javax.swing.JLabel();
            title.setText(APP_NAME + " v." + APP_VERSION);


            helpLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panelLabel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
            helpLabel.setForeground(Color.ORANGE);
            helpLabel.setText(" ? ");
            helpLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    new SimpleAboutDialog(null).show();
                }
            });
            panelLabel.add(title);
            panelLabel.add(helpLabel);


            exitLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            exitLabel.setForeground(Color.RED);
            exitLabel.setText(" X ");
            exitLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    System.exit(0);
                }
            });
            panelLabel.add(exitLabel);

            getContentPane().add(panelLabel);

            loadVolumeControl();

            loadAirlinesDropdown();


            JPanel audiosPanel = new JPanel();
            audiosPanel.setLayout(new javax.swing.BoxLayout(audiosPanel, javax.swing.BoxLayout.PAGE_AXIS));
            audiosPanel.setAutoscrolls(true);
            model = FileStructureHelper.convertToUIModel(AUDIO_FILES_DIR + "/" + airlines.getItemAt(airlines.getSelectedIndex()), audiosPanel, airlines.getItemAt(airlines.getSelectedIndex()));


            audiosPanelBorder.add(audiosPanel);
            setMaximumSize(getSize());
            getContentPane().add(myJScrollPane);


            pack();
            setSize(getWidth(), model.getEntryList().get(airlines.getItemAt(airlines.getSelectedIndex())).size() > MAX_ENTRIES ? MAX_WINDOW_WIDTH_WHEN_MAX_ENTRIES : getHeight());
            putJFrameOnTopRight();

        } catch (Exception ex) {
            ex.printStackTrace();
            showMessageDialog(null, ExceptionUtils.getStackTrace(ex), ex.getClass().getName(), JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }


    }

    private void loadVolumeControl() {


        JPanel panelLabel = new MotionPanel(this);

        JSlider volumeSlider = new javax.swing.JSlider();

        volumeSlider.setMinimum(MIN_GAIN);

        volumeSlider.setMaximum(MAX_GAIN);

        panelLabel.setLayout(new java.awt.FlowLayout(FlowLayout.CENTER));
        panelLabel.add(volumeSlider);
        getContentPane().add(panelLabel);

        volumeSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                CURRENT_GAIN_VALUE = ((JSlider) evt.getSource()).getValue();
                getActiveClips(airlines.getItemAt(airlines.getSelectedIndex())).forEach((clip) -> setGain(clip));

            }
        });
    }


    private void loadAirlinesDropdown() throws IOException {

        JPanel panelDropdown = new JPanel();
        panelDropdown.setLayout(new java.awt.GridLayout());

        airlines = new javax.swing.JComboBox(listDirsUsingFileWalk(AUDIO_FILES_DIR, 1).stream().filter(path -> !path.equals(AUDIO_FILES_DIR)).sorted().toArray());

        airlines.setFont(new java.awt.Font("Tahoma", 0, 9));
        airlines.setBorder(null);

        airlines.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent event) {

                if (event.getStateChange() == DESELECTED) {
                    stopAll((String) event.getItem());
                } else if (event.getStateChange() == SELECTED) {
                    System.out.println("Current  New item: " + event.getItem());
                }
                refreshUI();
            }
        });

        panelDropdown.add(airlines);

        getContentPane().add(panelDropdown);
    }

    private String[] getArray(Set<String> content) {
        return (String[]) content.toArray();
    }

    private void refreshUI() {
        try {
            audiosPanelBorder.removeAll();
            model.getEntryList().clear();
            JPanel audiosPanel = new JPanel();
            audiosPanel.setLayout(new javax.swing.BoxLayout(audiosPanel, javax.swing.BoxLayout.PAGE_AXIS));
            model = FileStructureHelper.convertToUIModel(AUDIO_FILES_DIR + "/" + airlines.getItemAt(airlines.getSelectedIndex()), audiosPanel, airlines.getItemAt(airlines.getSelectedIndex()));
            audiosPanelBorder.add(audiosPanel);

            pack();
            setSize(getWidth(), model.getEntryList().get(airlines.getItemAt(airlines.getSelectedIndex())).size() > MAX_ENTRIES ? MAIN_WINDOW_HEIGHT_WHEN_MAX_ENTRIES : getHeight());

        } catch (Exception ex) {
            ex.printStackTrace();
            showMessageDialog(null, ExceptionUtils.getStackTrace(ex), ex.getClass().getName(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        FlatAtomOneDarkContrastIJTheme.install();

        java.awt.EventQueue.invokeLater(() -> {
            ToolTipManager.sharedInstance().setInitialDelay(0);
            try {
                new AppWindow(args).setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    private void putJFrameOnTopRight() {
        /*PUT FRAME ON TOP RIGHT*/
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = (int) rect.getMaxX() - getWidth();
        int y = 0;
        setLocation(x, y + 30);
    }

}

